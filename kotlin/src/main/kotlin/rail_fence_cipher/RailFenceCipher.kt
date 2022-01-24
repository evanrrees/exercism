package rail_fence_cipher

internal class RailFenceCipher(private val rails: Int) {

    private val stride = 2 * (rails - 1)

    fun getEncryptedData(input: String) = buildString(input.length) {
        for (rail in 0 until rails) {
            for (pos in input.indices step stride) {
                input.getOrNull(pos + rail)?.let(::append)
                if (rail in 1 until rails - 1)
                    input.getOrNull(pos + stride - rail)?.let(::append)
            }
        }
    }

    fun getDecryptedData(input: String): String {
        val result = Array(input.length) { '.' }
        val iterator = input.iterator()
        for (rail in 0 until rails) {
            for (pos in input.indices step stride) {
                if (pos + rail in input.indices) result[pos + rail] = iterator.next()
                if (rail in 1 until rails - 1)
                    if (pos + stride - rail in input.indices)
                        result[pos + stride - rail] = iterator.next()
            }
        }
        return result.joinToString("")
    }
}
