#!/usr/bin/env bash

declare -A responses=(
  [sure]="Sure."
  [whoa]="Whoa, chill out!"
  [calm]="Calm down, I know what I'm doing!"
  [fine]="Fine. Be that way!"
  [whatever]="Whatever."
)

is_yelled() { [[ $1 -eq "${1^^}" ]]; echo $?; }

is_question() { [[ $1 =~ \?$ ]]; echo $?; }

main() {
  local cleaned=${1// /}
  [[ ${cleaned: -1} =~ \? ]]; local question=$?

  cleaned=${1//[^A-Za-z0-9]/}
  local upper=${cleaned^^}

  [[ ${1//[^A-Za-z0-9]/} ]]

  [[ "$cleaned" == "$upper" && "$upper" =~ [A-Z] ]]
  local yell=$?

  local response
  if ! (( question || yell )); then
    response="calm"
  elif ! (( yell )); then
    response="whoa"
  elif ! (( question )); then
    response="sure"
  elif [[ -z "$cleaned" ]]; then
    response="fine"
  fi

#  local -r yell=$( is_yelled $1 )
#  local -r question=$( is_question $1 )
#
#
#  if (( ! yell && ! question )); then
#    response="calm"
#  elif (( ! yell )); then
#    response="whoa"
#  elif (( ! question )); then
#    response="sure"
#  elif [[ -z $* ]]; then
#    response="fine"
#  fi

  echo "${responses[${response:-whatever}]}"
}

main "$*"