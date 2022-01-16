package transpose

object Transpose {

    fun transpose(rows: List<String>) =
        if (rows.isEmpty()) emptyList()
        else List(rows.maxOf { it.length }) { j ->
            List(rows.size) { i -> rows[i].getOrElse(j) { '_' } }
                .dropLastWhile { it == '_' }
                .joinToString("")
                .replace('_', ' ')
        }

}
