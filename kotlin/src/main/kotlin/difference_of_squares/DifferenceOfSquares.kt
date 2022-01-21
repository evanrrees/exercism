package difference_of_squares

internal class Squares(val n: Long) {

    fun sumOfSquares() = (2 * n + 1) * n * (n + 1) / 6

    fun squareOfSum() = (n * (n + 1) / 2).let { it * it }

    fun difference() = squareOfSum() - sumOfSquares()
}
