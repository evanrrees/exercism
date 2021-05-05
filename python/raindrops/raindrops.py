def convert(number: int) -> str:
    mods: list[tuple[int, str]] = [(3, 'Pling'), (5, 'Plang'), (7, 'Plong')]
    res: str = ''.join(c for n, c in mods if not number % n)
    return res or str(number)
