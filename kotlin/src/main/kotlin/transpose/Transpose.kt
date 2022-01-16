package transpose

object Transpose {

    fun transpose2(rows: List<String>): List<String> {
        val result: MutableList<String> = mutableListOf()
        rows.forEachIndexed { j, col ->
            col.forEachIndexed { i, c ->
                if (result.size <= i) result.add(i, "".padEnd(j, ' ') + c)
                else result[i] = result[i].padEnd(j, ' ') + c
            }
        }
        return result
    }

    fun transpose(rows: List<String>): List<String> {
        if (rows.isEmpty()) return emptyList()
        val columns = Array(rows.size) { rows[it].length }
            .apply { for (i in lastIndex - 1 downTo 0) set(i, maxOf(get(i), get(i + 1))) }
        return List(columns[0]) { j ->
            rows.mapIndexedNotNull { i, s -> s.getOrElse(j) { ' ' }.takeIf { j < columns[i] } }.joinToString("")
        }
    }
}
