#!/usr/bin/env bash

main() {
  local n=${#1} sum=0 i
  for (( i = 0; i < n; i++ )); do
    (( sum += ${1:i:1} ** n ))
  done
  if (( sum == $1 )); then
    echo "true"
  else
    echo "false"
  fi
}

main "$@"