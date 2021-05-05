#!/usr/bin/env bash

declare -rA error_message=(
  [usage]="Usage: grep.sh [-nlixv] PATTERN FILE..."
)

error_exit() {
  echo "${error_message[$1]:-$1}" >&2
  exit 1
}

parse_arguments() {
  (( $# > 1 )) || error_exit "usage"
  local flag
  local -a match_fmt=('' '' '' "\$line") conditional_fmt=('' '' '' '' '')
  while getopts lnvix flag; do
    case $flag in
      l) match_fmt[0]="echo \$file; break;";;
      n) match_fmt[2]="\$i:";;
      v) conditional_fmt[0]='!';;
      i) conditional_fmt[1]='^^'; conditional_fmt[3]='^^';;
      x) conditional_fmt[2]='^'; conditional_fmt[4]='$';;
      *) error_exit "Unknown option: $flag";;
    esac
  done
  shift $(( OPTIND - 1 ))
  pattern="$1"
  shift
  files=( "$@" )
  (( $# > 1 )) && match_fmt[1]="\$file:"
  printf -v match_action "{ %s echo %s%s%s; }" "${match_fmt[@]}"
  printf -v match_conditional "%s [[ \${line%s} =~ %s\${pattern%s}%s ]]\n" "${conditional_fmt[@]}"
}

grep() {
  local line file i
  for file in "${files[@]}"; do
      i=1
      while read -r line; do
        eval "$match_conditional" && eval "$match_action"
        ((i++))
      done < "$file"
  done
}

main() {
  local -a files
  local match_action match_conditional pattern
  parse_arguments "$@"
  grep
}

main "$@"