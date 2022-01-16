package matrix

/** Provides access to the rows and columns of a matrix passed as a string. */
class Matrix(matrixAsString: String) {

    /** Internal representation of matrix parsed to integers. */
    private val data: List<List<Int>> =
        matrixAsString.split("\n").map { it.split(Regex("\\s+")).mapNotNull(String::toIntOrNull) }

    /** Returns the number of rows in this matrix. */
    val numRows = data.size
    /** Returns the number of columns in this matrix. */
    val numCols = data.first().size

    init {
        require(data.all { it.size == numCols }) { "All columns must be of equal width: $matrixAsString" }
    }

    /** Returns a list of elements at the 1-based column of this matrix. */
    fun column(colNr: Int) = List(numRows) { data[it][colNr - 1] }

    /** Returns a list of elements at the 1-based row of this matrix. */
    fun row(rowNr: Int) = List(numCols) { data[rowNr - 1][it] }
}
