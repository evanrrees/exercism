class DiamondPrinter {
    fun printToList(c: Char) = ('A'..c)
        .map { "$it".padStart(c - it + 1, ' ').padEnd(c - '@', ' ').run { dropLast(1) + reversed() } }
        .run { dropLast(1) + reversed() }
}
