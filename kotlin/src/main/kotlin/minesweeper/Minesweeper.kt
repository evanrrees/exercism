package minesweeper

internal data class MinesweeperBoard(val board: List<String>) {

    val offsets = listOf(-1, 0, 1)
    fun withNumbers(): List<String> {
        return board.mapIndexed { i, row ->
            row.withIndex().joinToString("") { (j, c) ->
                if (c == '*') "$c"
                else {
                    val n = offsets.sumOf { i2: Int ->
                        offsets.count { j2: Int ->
                            (i2 != 0 || j2 != 0) && board.getOrNull(i + i2)?.getOrNull(j + j2) == '*'
                        }
                    }
                    if (n > 0) "$n"
                    else "$c"
                }
            }
        }
    }

}
