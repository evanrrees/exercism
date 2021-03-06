package nucleotide_count

internal class Dna(seq: String) {

    init {
        require(!seq.contains(Regex("[^acgtACGT]")))
    }

    val nucleotideCounts: Map<Char, Int> = seq.uppercase().groupingBy { it }.eachCount()
        get() {
            return field.toMap(mutableMapOf('A' to 0, 'C' to 0, 'G' to 0, 'T' to 0))
        }

}
