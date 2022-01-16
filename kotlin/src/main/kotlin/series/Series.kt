package series

object Series {

    fun slices(n: Int, s: String): List<List<Int>> {
        require(n <= s.length && s.isNotBlank())
        return s.windowed(n) { it.map { c -> "$c".toInt() } }
    }
}
