package beer_song

import java.util.*

internal object BeerSong {

    fun verse(n: Int): String {
        fun numBottles(n: Int) = if (n == 0) "no more" else if (n < 0) "99" else "$n"
        fun bottles(n: Int) = "bottle" + if (n != 1) "s" else ""
        fun whatsThere(n: Int) = "${numBottles(n)} ${bottles(n)} of beer"
        fun whatToDo(n: Int) = if (n < 0) "Go to the store and buy some more"
        else "Take ${if (n == 0) "it" else "one"} down and pass it around"
        whatsThere(n).replaceFirstChar(Char::titlecase)
        return "${whatsThere(n).replaceFirstChar(Char::titlecase)} on the wall, ${whatsThere(n)}.\n" +
                "${whatToDo(n - 1)}, ${whatsThere(n - 1)} on the wall.\n"
    }

    fun verses(startBottles: Int, takeDown: Int): String {
        require(startBottles in 0..99 && takeDown in 0..99)
        return (startBottles downTo takeDown).joinToString("\n") { verse(it) }
    }
}
