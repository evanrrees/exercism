package saddle_points

internal data class MatrixCoordinate(val row: Int, val col: Int)

internal data class Matrix(val data: List<List<Int>>) {
    val saddlePoints: Set<MatrixCoordinate> = mutableSetOf()
    init {
        saddlePoints as MutableSet
        for ((i, row) in data.withIndex())
            for ((j, int) in row.withIndex())
                if (row.none { it > int } && data.none { it[j] < int })
                    saddlePoints += MatrixCoordinate(i + 1, j + 1)
    }
}