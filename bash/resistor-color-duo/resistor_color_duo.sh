#!/usr/bin/env bash

declare -A colors=([black]=0 [brown]=1 [red]=2 [orange]=3 [yellow]=4 [green]=5 [blue]=6 [violet]=7 [grey]=8 [white]=9)

get_color_index() {
  [[ -v "colors[$1]" ]] && printf "%s" "${colors[$1]}"
}

exit_invalid() {
  echo "invalid color: $1"
  exit 1
}

main() {
  for color in "$1" "$2"; do
    get_color_index "$color" || exit_invalid "$color"
  done
  printf "\n"
}

main "$@"
