package scale_generator


class Scale(tonic: String) {

    private val _tonic = tonic.first().uppercase() + tonic.drop(1)

    private val usesFlats = _tonic.endsWith('b')

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
