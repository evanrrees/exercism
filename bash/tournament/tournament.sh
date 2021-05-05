#!/usr/bin/env bash

declare -rA error_message=([usage]="Usage: bash $0 <file.txt>")

exit_error() { echo "${error_message[$1]:-"$@"}" >&2; exit 1; }

read_match_results() {
  # parse new-line separated match results and populate table[] with outcomes
  local team1 team2 outcome
  while IFS=';' read -r team1 team2 outcome; do
    [[ -z $team1 ]] && continue
    [[ "${teams[*]}" =~ $team1 ]] || teams+=( "$team1" )
    [[ "${teams[*]}" =~ $team2 ]] || teams+=( "$team2" )
    (( table[$team1,MP]++, table[$team2,MP]++ ))
    case $outcome in
      "win")
        (( table[$team1,W]++, table[$team2,L]++, table[$team1,P]+=3 ))
        ;;
      "draw")
        (( table[$team1,D]++, table[$team2,D]++, table[$team1,P]++, table[$team2,P]++ ))
        ;;
      "loss")
        (( table[$team1,L]++, table[$team2,W]++, table[$team2,P]+=3 ))
        ;;
    esac
  done < "$1"
}

sort_table() {
  # bubble sort teams[] based on table[$team,P], breaking ties by lexicographic order
  local i swapped team1 team2 team1p team2p
  while : ; do
    swapped=0
    for (( i=1; i<${#teams[@]}; i++ )); do
      team1="${teams[i]}"     team1p="${table[$team1,P]:-0}"
      team2="${teams[i-1]}"   team2p="${table[$team2,P]:-0}"
      if (( team1p > team2p )) || ( (( team1p == team2p )) && [[ "$team1" < "$team2" ]] ); then
        teams[$i]=$team2 teams[$i-1]=$team1
        swapped=1
      fi
    done
    (( swapped )) || break
  done
}

print_table() {
  # print header and table[] after formatting
  local team c
  printf "%-31s|%3s |%3s |%3s |%3s |%3s\n" "Team" "${cols[@]}"
  for team in "${teams[@]}"; do
    printf "%-30s" "$team"
    for c in "${cols[@]/#/$team,}"; do
      printf " |%3s" "${table[$c]:-0}"
    done
    printf "\n"
  done
}

main() {
  local -A table
  local -a teams
  local -ar cols=( MP W D L P )
  if (( $# == 1 )) && [[ -f "$1" ]]; then
    read_match_results "$1"
  elif [[ -s /dev/stdin ]]; then
    read_match_results /dev/stdin
  else
    exit_error usage
  fi
  sort_table
  print_table
}

main "$@"