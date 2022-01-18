package dominoes

internal class ChainNotFoundException(msg: String) : RuntimeException(msg)

internal data class Domino(val left: Int, val right: Int) {

    fun flipped() = Domino(right, left)

    fun matchWith(other: Domino): Domino? = when (right) {
        other.left -> other
        other.right -> other.flipped()
        else -> null
    }

}

internal object Dominoes {

    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        if (inputDominoes.isEmpty())
            return inputDominoes
        for (start in inputDominoes) {
            var chain = dfs(inputDominoes - start, start, listOf(start))
            if (chain.isEmpty())
                chain = dfs(inputDominoes - start, start.flipped(), listOf(start.flipped()))
            if (chain.isNotEmpty())
                return chain.takeIf { it.first().left == it.last().right }
                    ?: throw ChainNotFoundException("")
        }
        throw ChainNotFoundException("")
    }

    fun dfs(dominoes: List<Domino>, current: Domino, chain: List<Domino>): List<Domino> {
        if (dominoes.isEmpty()) return chain
        else {
            for (next in dominoes) {
                val matched = current.matchWith(next)
                if (matched != null) {
                    val newChain = dfs(dominoes - next, matched, chain + matched)
                    if (newChain.isNotEmpty()) return newChain
                }
            }
            return emptyList()
        }
    }

    fun formChain(vararg dominoes: Domino) = formChain(dominoes.toList())

}
