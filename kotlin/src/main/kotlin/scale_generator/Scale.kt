package scale_generator

import rna_transcription.complement


enum class Tone {

    A, AB, B, C, CD, D, DE, E, F, FG, G, GA;

    val ambiguous get() = name.length == 2
    val sharp     get() = if (ambiguous) "${name.first()}#" else null
    val flat      get() = if (ambiguous) "${name.last()}b" else null

    fun toString(flat: Boolean) = when {
        !ambiguous -> name
        flat -> this.flat!!
        else -> this.sharp!!
    }

    companion object {
        fun getValue(string: String): Tone {
            complement
            return when (string.last()) {
                '#' -> values().first { it.sharp == string.take(1) }
                'b' -> values().first { it.flat == string.take(1) }
                else -> values().first { it.name == string.take(1) }
            }
        }
    }
}

data class RealTone(val tone: Tone, val flat: Boolean = false) {
    override fun toString(): String {
        return tone.toString(flat)
    }
}

class Scale(tonic: String) {

    private val _tonic = tonic.first().uppercase() + tonic.drop(1)

    private val usesFlats = _tonic.endsWith('b')

    val chromatic2 = Tone.values().run {

        map { RealTone(it, usesFlats) }
    }

    private val chromatic = listOf(
        "A",      if (usesFlats) "Bb" else "A#",
        "B", "C", if (usesFlats) "Db" else "C#",
        "D",      if (usesFlats) "Eb" else "D#",
        "E", "F", if (usesFlats) "Gb" else "F#",
        "G",      if (usesFlats) "Ab" else "G#"
    ).run {
        val offset = indexOf(_tonic)
        List(size) { this[(offset + it) % size] }
    }

    fun chromatic(): List<String> = chromatic

    fun interval(intervals: String): List<String> {
        val result = mutableListOf<String>()
        var n = 0
        for (m in intervals) {
            result += chromatic[n]
            n += when (m) { 'A' -> 3; 'M' -> 2; else -> 1 }
            n %= 12
        }
        return result
    }

}
