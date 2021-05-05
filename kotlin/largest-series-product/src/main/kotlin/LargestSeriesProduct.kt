class Series(s: String) {

    init {
        require(s.all(Char::isDigit) || s.isEmpty())
    }

    private val digits = s.map { "$it".toLong() }

    fun getLargestProduct(span: Int): Long {
        require(span <= digits.size)
        if (span == 0) return 1
        return digits.windowed(span).map { it.reduce(Long::times) }.max()?: 1L
    }
}
