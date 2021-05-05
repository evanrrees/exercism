#!/usr/bin/env bash

main() {
  local acronym
  for word in ${*//[^A-Za-z\']/ }; do
    acronym+=${word:0:1}
  done
  echo "${acronym^^}"
}

main "$@"