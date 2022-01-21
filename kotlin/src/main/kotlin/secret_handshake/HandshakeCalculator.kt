package secret_handshake

import secret_handshake.Signal

internal object HandshakeCalculator {
    fun calculateHandshake(number: Int) = Signal.values()
        .filter { number shr it.ordinal and 1 == 1 }
        .let { if (number shr 4 and 1 == 1) it.reversed() else it }
}
