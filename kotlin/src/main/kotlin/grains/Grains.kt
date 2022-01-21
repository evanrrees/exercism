package grains

import java.math.BigInteger

internal object Board {

    fun getGrainCountForSquare(number: Int): BigInteger {
        require(number in 1..64)
        return BigInteger.ONE shl (number - 1)
    }

    fun getTotalGrainCount() = getGrainCountForSquare(64).shl(1).dec()
}
