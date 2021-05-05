#!/usr/bin/env bash

set -e

usage() {
  echo "Usage: leap.sh <year>"
  exit "$1"
}

checkArgs() {
  [[ "$#" == 1 ]] && [[ "$1" =~ ^[0-9]+$ ]]
}

divisibleBy() {
  [[ $(( "$1" % "$2" )) == 0 ]]
}

checkArgs "$@" || usage 1 \
  && divisibleBy "$1" 4 \
  && ! divisibleBy "$1" 100 \
  || divisibleBy "$1" 400 \
  && echo "true" || echo "false"
