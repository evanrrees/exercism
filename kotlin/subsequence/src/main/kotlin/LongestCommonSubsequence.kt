object LongestCommonSubsequence {
    fun lcs(x: CharSequence, y: CharSequence) {
        val l = Array(x.length + 1) { Array(y.length + 1) { 0 } }
        for (i in 1..x.length) {
            for (j in 1..y.length) {
                l[i][j] = if (x[i - 1] == y[j - 1]) l[i - 1][j - 1] + 1 else maxOf(l[i][j - 1], l[i - 1][j])
            }
        }
        printMatrix(x, y, l)
        println(backtrace(l, x, y, x.length, y.length))
    }
    fun printMatrix(s1: CharSequence, s2: CharSequence, arr: Array<Array<Int>>) {
        val res = arr
            .mapIndexed { i, a -> a.joinToString(" ", prefix="${if (i == 0) "-" else s1[i - 1]} ") }
            .joinToString("\n", prefix="-".plus(s2).toList().joinToString(" ").run { padStart(length + 2) + "\n" })
        println(res)
    }
    tailrec fun backtrace(arr: Array<Array<Int>>, x: CharSequence, y: CharSequence, i: Int, j: Int, result: String = ""): String {
        return when {
            i == 0 || j == 0 -> result
            x[i - 1] == y[j - 1] -> backtrace(arr, x, y, i - 1, j - 1, x[i - 1] + result)
            arr[i][j - 1] > arr[i - 1][j] -> backtrace(arr, x, y, i, j - 1, result)
            else -> backtrace(arr, x, y, i - 1, j, result)
        }
    }
}

