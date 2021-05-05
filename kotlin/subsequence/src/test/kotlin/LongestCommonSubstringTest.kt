import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LongestCommonSubstringTest {

    @Test
    fun lcs() {
        val res = LongestCommonSubstring.lcs("ABABC", "BABCA")
        println(res)
    }
}