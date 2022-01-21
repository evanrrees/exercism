package collatz_conjecture

internal object CollatzCalculator {


    fun computeStepCount(start: Int): Int {
        require(start > 0)
        return _computeStepCount(start)
    }

    private tailrec fun _computeStepCount(start: Int, count: Int = 0): Int = when {
        start == 1     -> count
        start % 2 == 0 -> _computeStepCount(start shr 1, count + 1)
        else           -> _computeStepCount(start * 3 + 1, count + 1)
    }

}
