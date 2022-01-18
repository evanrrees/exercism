package dominoes

internal class ChainNotFoundException(msg: String) : RuntimeException(msg)

internal data class Domino(val left: Int, val right: Int) {
    fun flipped() = Domino(right, left)
}

internal object Dominoes {

    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        if (inputDominoes.isEmpty()) return inputDominoes
        for (start in inputDominoes) {
            val chain = dfs(inputDominoes - start, listOf(start))
            if (chain.isNotEmpty()) return chain
        }
        throw ChainNotFoundException("")
    }

    fun dfs(dominoes: List<Domino>, chain: List<Domino>): List<Domino> {
        if (dominoes.isEmpty() && chain.first().left == chain.last().right) return chain
        else {
            for (next in dominoes) {
                val new = when {
                    next.left == chain.last().right -> dfs(dominoes - next, chain + next)
                    next.right == chain.first().left -> dfs(dominoes - next, listOf(next) + chain)
                    next.right == chain.last().right -> dfs(dominoes - next, chain + next.flipped())
                    next.left == chain.first().left -> dfs(dominoes - next, listOf(next.flipped()) + chain)
                    else -> emptyList()
                }
                if (new.isNotEmpty()) return new
            }
            return emptyList()
        }
    }

    fun formChain(vararg dominoes: Domino) = formChain(dominoes.toList())

}
