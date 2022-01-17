package wordy

import kotlin.math.pow


object Wordy {

    fun answer(input: String): Int {
        val iterator = input.split(" ").iterator()
        require(iterator.next() == "What" && iterator.next() == "is")
        var result: Int = iterator.next().parseInt()
        while (iterator.hasNext())
            result = operations[iterator.next()]!!(iterator, result)
        return result
    }

    val operations = mapOf<String, Iterator<String>.(Int) -> Int> (
        "plus" to { it + next().parseInt() },
        "minus" to { it - next().parseInt() },
        "divided" to {
            require(next() == "by")
            it / next().parseInt()
        },
        "multiplied" to {
            require(next() == "by")
            it * next().parseInt()
        },
        "raised" to {
            require(next() == "to" && next() == "the")
            it.toDouble().pow(next().parseInt()).toInt().also {
                require(next().startsWith("power"))
            }
        }
    )

    fun String.parseInt(): Int = this.trim().dropLastWhile { !it.isDigit() }.toInt()

//    val regex = Regex("(\\p{Digit}+) (plus|minus|divided by|multiplied by) (\\p{Digit}+)")
//    val regex = Regex("What is (-?\\p{Digit}+)( (plus|minus|divided by|multiplied by) (-?\\p{Digit}+))*")

}
