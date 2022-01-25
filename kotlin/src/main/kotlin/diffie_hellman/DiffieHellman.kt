package diffie_hellman

import java.math.BigInteger
import kotlin.random.Random

internal object DiffieHellman {

    fun privateKey(p: BigInteger): BigInteger = BigInteger.valueOf(Random.nextLong(2, p.longValueExact()))

    fun publicKey(p: BigInteger, g: BigInteger, prv: BigInteger): BigInteger = g.pow(prv.intValueExact()).mod(p)

    fun secret(p: BigInteger, pub: BigInteger, prv: BigInteger): BigInteger = pub.pow(prv.intValueExact()).mod(p)
}
