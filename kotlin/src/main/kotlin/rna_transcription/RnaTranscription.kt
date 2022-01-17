package rna_transcription

internal class NucleotideException(message: String): IllegalArgumentException(message)

internal val complement = mapOf('G' to 'C', 'C' to 'G', 'T' to 'A', 'A' to 'U')

internal fun transcribeToRna(dna: String) =
    dna.map { complement[it]?: throw NucleotideException("$it") }.joinToString("")