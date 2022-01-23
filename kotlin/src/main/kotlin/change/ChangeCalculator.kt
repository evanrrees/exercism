package change

internal class ChangeCalculator(private val coins: List<Int>) {

    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        require(grandTotal >= 0) { "Negative totals are not allowed." }

        var bestPath = emptyList<Int>()
        fun dfs(total: Int, _coins: List<Int> = coins.sortedDescending(), path: List<Int> = emptyList()) {
            if (bestPath.isEmpty() || path.size < bestPath.size)
                for ((index, c) in _coins.withIndex())
                    if (c == total) bestPath = path + c
                    else if (c < total) dfs(total - c, _coins.drop(index), path + c)
        }

        dfs(grandTotal)
        require(grandTotal == 0 || bestPath.isNotEmpty()) { "The total $grandTotal cannot be represented in the given currency." }
        return bestPath
    }
}
