object LongestPalindromicSubstring {
    fun lps(s: String): Int {
        val sp = if (s.length % 2 == 0) s.split("").joinToString("|") else s
        val radii = Array(sp.length) { 0 }
        var center = 0
        while (center < sp.length) {
            var radius = 0
            while (center - radius - 1 >= 0 && center + radius + 1 < sp.length && sp[center - radius - 1] == sp[center + radius + 1]) {
                radius++
            }
            radii[center++] = radius
        }
        val lp = 2 * (radii.maxOrNull()?: 0) + 1
        val l = (lp - 1) / 2
        return l
    }
}

object LongestCommonSubstring {
    fun lcs(s: String, t: String): List<String> {
        val l = Array(s.length) { Array(t.length) { 0 } }
        var z = 0
        val ret = mutableSetOf<Int>()
        for (i in s.indices) {
            for (j in t.indices) {
                if (s[i] == t[j]) {
                    if (i == 0 || j == 0) l[i][j] = 1   // start at one
                    else l[i][j] = l[i - 1][j - 1] + 1  // else add one to prev diagonal
                    if (l[i][j] > z) {                  // if longer than current lcs
                        z = l[i][j]
                        ret.clear()
                        ret += i - z
                    }
                    else if (l[i][j] == z) ret += i - z // if equal to current lcs
                } else l[i][j] = 0                      // otherwise set cell to 0
            }
        }
        printMatrix(s, t, l)
        return ret.map { s.substring(it until it + z) }
    }
    fun printMatrix(s1: CharSequence, s2: CharSequence, arr: Array<Array<Int>>) {
        val res = arr
            .mapIndexed { i, a -> a.joinToString(" ", prefix="${s1[i]} ") }
            .joinToString("\n", prefix=s2.toList().joinToString(" ").run { padStart(length + 2) + "\n" })
        println(res)
    }
}

