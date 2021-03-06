package scrabble_score

internal object ScrabbleScore {

    private fun scoreLetter(c: Char) = when (c.uppercaseChar()) {
        'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'    -> 1
        'D', 'G'                                            -> 2
        'B', 'C', 'M', 'P'                                  -> 3
        'F', 'H', 'V', 'W', 'Y'                             -> 4
        'K'                                                 -> 5
        'J', 'X'                                            -> 8
        'Q', 'Z'                                            -> 10
        else -> throw IllegalArgumentException("Unrecognized character: $c")
    }

    fun scoreWord(word: String) = word.sumOf(ScrabbleScore::scoreLetter)
}
