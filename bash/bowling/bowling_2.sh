#!/usr/bin/env bash

declare -A error_message=(
  [incomplete]="Score cannot be taken until the end of the game"
  [negative]="Negative roll is invalid"
  [exceeds]="Pin count exceeds pins on the lane"
  [game_over]="Cannot roll after game is over"
)

exit_error() {
  echo "${error_message[$1]:-$1}" >&2; exit 1
}

validate_rolls() {
  for roll in "$@"; do
    [[ -z "$roll" ]] && exit_error incomplete
    (( roll < 0 )) && exit_error negative
    (( roll > 10 )) && exit_error exceeds
  done
}

main() {
  # 0000; last_frame + strike + open
  local frame
  for frame in {1..10}; do

  done
}