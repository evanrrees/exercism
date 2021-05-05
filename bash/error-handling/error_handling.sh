#!/usr/bin/env bash

usage() {
  echo "Usage: error_handling.sh <person>"
  exit "$1"
}

main() {
  if (( $# == 1 )); then echo "Hello, $1"; else usage 1; fi
}

main "$@"
