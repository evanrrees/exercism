#!/usr/bin/env bash

main() {
  local -r lower="${*,,}"
  local c
  for c in {a..z}; do
      [[ $lower =~ $c ]] || { echo "false"; return; }
  done
  echo "true"
}

main "$@"