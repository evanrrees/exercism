#!/usr/bin/env bash

main() {
  local -A plng=([3]=Pling [5]=Plang [7]=Plong)
  for i in $(printf "%s\n" "${!plng[@]}" | sort); do
    [[ $(("$1" % "$i")) == 0 ]] && result+="${plng[$i]}"
  done
  printf "%s\n" "${result:-$1}"
}

main "$@"
