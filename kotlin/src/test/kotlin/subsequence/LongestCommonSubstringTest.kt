package subsequence

import org.junit.Test

internal class LongestCommonSubstringTest {

    @Test
    fun lcs() {
        val res = LongestCommonSubstring.lcs("ABABC", "BABCA")
        println(res)
    }
}