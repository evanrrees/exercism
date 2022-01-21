package pascals_triangle

object PascalsTriangle {

    private fun rowSequence() = generateSequence(listOf(1)) { prev ->
        List(prev.size + 1) { (prev.getOrNull(it - 1) ?: 0) + (prev.getOrNull(it) ?: 0) }
    }

    fun computeTriangle(rows: Int) = rowSequence().take(rows).toList()

}
