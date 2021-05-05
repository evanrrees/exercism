#!/usr/bin/env bash

main() {
  local -A closing=(['[']=']' ['{']='}' ['(']=')' )
  local -a stack
  local bracket
  for ((i=0; i<"${#1}"; i++)); do
    bracket="${1:$i:1}"
    case "$bracket" in
      '[' | '{' | '(')
        stack+=( "${closing[$bracket]}" )
        ;;
      ']' | '}' | ')')
        if [[ "${#stack[@]}" != 0 && "${stack[-1]}" == "$bracket" ]]; then
          unset stack[-1]
        else
          echo "false"
          return
        fi
        ;;
      *)
        continue
        ;;
    esac
  done
  [[ "${#stack[@]}" == 0 ]] && echo "true" || echo "false"
}

main "$@"
