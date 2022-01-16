package subsequence

import org.junit.jupiter.api.Test

internal class LongestCommonSubstringTest {

    @Test
    fun lcs() {
        val res = LongestCommonSubstring.lcs("ABABC", "BABCA")
        println(res)
    }
}