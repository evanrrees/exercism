#!/usr/bin/env bash

declare -A error_message=([usage]="usage: dnd_character.sh modifier n\nusage: dnd_character.sh generate")

exit_error() { echo -e "${error_message[$1]:-$1}" >&2; exit 1; }

roll_die() {
  echo $((RANDOM % 6 + 1))
}

best_three_of_four() {
  local roll sum min
  (( roll=$(roll_die), sum=roll, min=roll ))
  for (( _=0; _<3; _++ )); do
    (( roll=$(roll_die), sum+=roll, min=(roll < min ? roll : min) ))
  done
  echo $(( sum - min ))
}

modifier() {
  echo $(( $1 / 2 - 5 ))
}

roll_abilities() {
  local -ra abilities=( strength dexterity constitution intelligence wisdom charisma )
  local con roll abl
  for abl in "${abilities[@]}"; do
    roll=$(best_three_of_four)
    [[ $abl == "constitution" ]] && con=$roll
    echo "$abl $roll"
  done
  echo "hitpoints $(( 10 + $(modifier "$con") ))"
}

main() {
  (( $# <= 2 )) || exit_error usage
  case "$1" in
    "modifier") modifier "$2";;
    "generate") roll_abilities;;
    *)          exit_error usage;;
  esac
}

main "$@"