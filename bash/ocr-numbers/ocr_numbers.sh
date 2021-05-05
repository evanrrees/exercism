#!/usr/bin/env bash

declare -a ZERO ONE TWO THREE FOUR FIVE SIX SEVEN EIGHT NINE
# 0: space; 1: underscore; 2: pipe
# ... or 00=space, 01=underscore; 10=pipe;
# 3^3 = 27 possible lines...
# but bash can do base 3 with e.g. (( 3#210 )), which evaluates to 21 (of course.......)
# so, we can encode each line as an integer between 0..26
# but is this even worthwhile???
# could hard-code all 10 integers but that's boring af