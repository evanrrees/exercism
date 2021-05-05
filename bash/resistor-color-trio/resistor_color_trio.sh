#!/usr/bin/env bash

declare -rA COLORS=([black]=0 [brown]=1 [red]=2 [orange]=3 [yellow]=4 [green]=5 [blue]=6 [violet]=7 [grey]=8 [white]=9)
declare -ra UNIT=(ohms kiloohms megaohms gigaohms teraohms petaohms exaohms)
declare -A error_message=(
  [usage]="Usage: $0 <color> <color> <color>"
  [colors]="Color not found, try one of:\n  ${!COLORS[@]}"
)

exit_error() {
  echo -e "${error_message[$1]:-$1}" >&2; exit 1
}

parse_colors() {
  local i=0 n=$(( (COLORS[$1] * 10 + COLORS[$2]) * (10 ** COLORS[$3]) ))
  while ! (( n % 1000 )) && (( n )); do
    (( n /= 1000, i++ ))
  done
  echo "$n ${UNIT[i]}"
}

main() {
  (( $# < 3 )) && exit_error usage
  [[ -v COLORS[$1] && -v COLORS[$2] && -v COLORS[$3] ]] || exit_error colors
  parse_colors "$1" "$2" "$3"
}

main "$@"