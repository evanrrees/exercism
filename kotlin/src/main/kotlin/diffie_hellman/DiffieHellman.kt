package diffie_hellman

import java.math.BigInteger
import kotlin.random.Random

internal object DiffieHellman {

    fun privateKey(p: BigInteger): BigInteger = BigInteger.valueOf(Random.nextLong(2, p.longValueExact()))

    fun publicKey(p: BigInteger, g: BigInteger, prv: BigInteger): BigInteger = g.modPow(prv, p)

    fun secret(p: BigInteger, pub: BigInteger, prv: BigInteger): BigInteger = pub.modPow(prv, p)
}
