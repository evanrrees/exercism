class NucleotideException(message: String): IllegalArgumentException(message)

val complement = mapOf('G' to 'C', 'C' to 'G', 'T' to 'A', 'A' to 'U')

fun transcribeToRna(dna: String) =
    dna.map { complement[it]?: throw NucleotideException("$it") }.joinToString("")