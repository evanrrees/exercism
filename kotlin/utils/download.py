import os
from os.path import basename
from shutil import rmtree
from sys import argv
import subprocess as sp
from subprocess import CompletedProcess
import json
from locale import getpreferredencoding as preferred_encoding
from glob import glob


def load_config() -> dict:
    fp: str = '/Users/err87/.config/exercism/user.json'
    with open(fp, 'r') as f:
        result = json.load(f)
    return result


def download_exercise(name: str) -> CompletedProcess:
    process: CompletedProcess = sp.run(
        ['exercism', 'download', f'--exercise={name}', f'--track=kotlin'],
        capture_output=True,
        encoding=preferred_encoding()
    )
    return process


def load_meta(download_dir: str) -> tuple[dict, dict]:
    with open(f'{download_dir}/.exercism/config.json', 'r') as f:
        ex_cfg: dict = json.load(f)
    with open(f'{download_dir}/.exercism/metadata.json', 'r') as f:
        ex_meta: dict = json.load(f)
    return ex_cfg, ex_meta


def write_with_header(orig_kt: str, dest_kt: str, nice_name: str):
    is_test: bool = orig_kt.endswith('Test.kt')
    with open(orig_kt, 'r') as fi, open(dest_kt, 'w') as fo:
        fo.write(f'package {nice_name}\n\n')
        for line in fi:
            if is_test and line.endswith('Ignore\n'):
                continue
            fo.write(f'{line}')


def make_file_hierarchy(workspace: str, nice_name: str) -> list[str]:
    created_dirs: list[str] = []
    for dir1 in 'main', 'test':
        for dir2 in 'resources', 'kotlin':
            new_dir: str = f'{workspace}/kotlin/src/{dir1}/{dir2}/{nice_name}'
            if dir1 == 'main' and dir2 == 'kotlin':
                new_dir = f'{new_dir}/.exercism'
            os.makedirs(new_dir)
            created_dirs.append(new_dir)
    return created_dirs


def relocate_kt(workspace: str, nice_name: str, download_dir: str) -> list[str]:
    created_files: list[str] = []
    for src in 'main', 'test':
        for orig_kt in glob(f'{download_dir}/src/{src}/kotlin/*.kt'):
            filename: str = basename(orig_kt)
            dest_kt = f'{workspace}/kotlin/src/{src}/kotlin/{nice_name}/{filename}'
            created_files.append(dest_kt)
            write_with_header(orig_kt, dest_kt, nice_name)
    return created_files


def relocate_md(workspace: str, nice_name: str, download_dir: str) -> list[str]:
    created_files: list[str] = []
    for orig_md in glob(f'{download_dir}/*.md'):
        base_md: str = basename(orig_md)
        if base_md == 'HELP.md':
            continue
        dest_md: str = f'{workspace}/kotlin/src/main/resources/{nice_name}/{base_md}'
        os.rename(orig_md, dest_md)
        created_files.append(dest_md)
    return created_files


def relocate_json(workspace: str, nice_name: str, ex_cfg: dict, ex_meta: dict) -> list[str]:
    ex_meta['exercise'] = nice_name
    base_dir: str = f'{workspace}/kotlin/src/main/kotlin/{nice_name}/.exercism'
    with open(f'{base_dir}/config.json', 'w') as fo:
        json.dump(ex_cfg, fo, indent=2)
    with open(f'{base_dir}/metadata.json', 'w') as fo:
        json.dump(ex_meta, fo, indent=2)
    return [f'{base_dir}/{x}.json' for x in ('config', 'metadata')]


def list_results(created_files: list[str], created_dirs: list[str]) -> list[str]:
    result: list[str] = list(created_dirs)
    for d in created_dirs:
        for f in created_files:
            if f.startswith(d):
                result.remove(d)
                break
    return sorted(result + created_files)


# def git_add(created_files: list[str]):
#     for f in created_files:
#         sp.run(['git', 'add', f])
#     pass


def main(args: list[str]):

    exercise: str = args[1]
    print('Loading config file:')
    user_cfg: dict = load_config()
    workspace: str = user_cfg['workspace']

    print(f'Downloading exercise {exercise}')
    completed_process: CompletedProcess = download_exercise(exercise)

    if completed_process.returncode != 0:
        print(f'Download exited with non-zero status {completed_process.returncode}:')
        print(completed_process.stderr)
        exit(completed_process.returncode)

    download_dir: str = completed_process.stdout.strip()
    print('Loading exercise metadata')
    ex_cfg, ex_meta = load_meta(download_dir)
    assert ex_meta['exercise'] == exercise
    nice_name: str = exercise.replace('-', '_')

    print(f'Relocating files to package {nice_name}')
    created_dirs: list[str] = make_file_hierarchy(workspace, nice_name)
    created_files: list[str] = []
    created_files += relocate_kt(workspace, nice_name, download_dir)
    created_files += relocate_md(workspace, nice_name, download_dir)
    created_files += relocate_json(workspace, nice_name, ex_cfg, ex_meta)

    print(f'Removing original directory: {download_dir}')
    rmtree(download_dir)

    all_created: list[str] = list_results(created_files, created_dirs)
    print("Created files/dirs:\n\t")
    print('\t\n'.join(all_created))


if __name__ == '__main__':
    main(argv)
