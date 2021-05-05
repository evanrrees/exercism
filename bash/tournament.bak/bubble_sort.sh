#!/usr/bin/env bash

set -x

descending_sort() {
  local arr=( "$@" )
  local swapped=0 temp
  while : ; do
    swapped=0
    for (( i=1; i<=${#arr[@]}; i++ )); do
      if (( arr[i] > arr[i-1] )); then
        temp=${arr[$i]}
        arr[$i]=${arr[$i-1]}
        arr[$i-1]=$temp
        swapped=1
      fi
    done
    (( swapped )) || break
  done
  echo "${arr[@]}"
}

descending_sort 1 9 2 5 4 5 6 1