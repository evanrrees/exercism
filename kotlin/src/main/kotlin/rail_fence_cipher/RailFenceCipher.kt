package rail_fence_cipher

internal class RailFenceCipher(private val rails: Int) {

    private val stride = 2 * (rails - 1)

    fun getEncryptedData(input: String) = encryptionSequence(input.length).map(input::get).joinToString("")

    fun getDecryptedData(input: String) = decryptionSequence(input.length).map(input::get).joinToString("")

    private fun encryptionSequence(length: Int) = sequence {
        for (rail in 0 until rails)
            for (pos in rail until length step stride) {
                yield(pos)
                if (rail > 0 && rail < rails - 1) yield(pos + stride - 2 * rail)
            }
    }.filter { it < length }

    private fun decryptionSequence(length: Int) =
        encryptionSequence(length).withIndex().sortedBy { it.value }.map { it.index }
}
