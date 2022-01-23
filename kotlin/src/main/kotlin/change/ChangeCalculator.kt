package change

class ChangeCalculator(coins: List<Int>) {
    val coins = coins.sortedDescending()
    var bestPath = emptyList<Int>()
    fun bfs(total: Int, _coins: List<Int>, path: List<Int> = emptyList()) {
        if (total == 0) {
            if (path.size < bestPath.size || bestPath.isEmpty()) bestPath = path.toList()
        } else if (total > 0) {
            for ((index, c) in _coins.filter { it <= total }.withIndex()) {
                if (total >= c) {
                    bfs(total - c, _coins.drop(index), path + c)
                    if (total == c) break
                } else {
                    bfs(total, _coins.drop(index + 1), path)
                }
            }
        } else {
            return
        }
    }
    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
        bfs(grandTotal, coins)
        return bestPath
    }
}
