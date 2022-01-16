package pangram

object Pangram {

    fun isPangram(input: String) = input.toLowerCase().run { ('a'..'z').all { it in this } }
}
