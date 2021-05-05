#!/usr/bin/env bash

main() {
  local want="$1"
  while (( "$#" > 1 )); do
    echo "For want of a $1 the $2 was lost."
    shift
  done
  [[ "$#" == 1 ]] && echo "And all for the want of a $want." || echo ""
}

main "$@"
