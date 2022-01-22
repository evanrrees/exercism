package spiral_matrix

object SpiralMatrix {

    fun ofSize(size: Int): Array<IntArray> {
        val matrix = Array(size) { IntArray(size) }
        var limit = 0
        var i = 0
        var j = 0
        for (n in 1 .. size * size) {
            matrix[i][j] = n
            when {
                i == limit -> if (j < size - limit - 1) j++ else i++
                j == size - limit - 1 -> if (i < size - limit - 1) i++ else j--
                i == size - limit - 1 -> if (j > limit) j-- else i--
                j == limit -> if (i > limit + 1) i-- else { limit++; j++ }
            }
        }
        return matrix
    }
}
