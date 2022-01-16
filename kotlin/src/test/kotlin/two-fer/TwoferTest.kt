package `two-fer`

import org.junit.Test
import kotlin.test.assertEquals

class TwoferTest {

    @Test
    fun noNameGiven() {
        assertEquals("One for you, one for me.", twofer())
    }

    @Test
    fun aNameGiven() {
        assertEquals("One for Alice, one for me.", twofer("Alice"))
    }

    @Test
    fun anotherNameGiven() {
        assertEquals("One for bob.Bob, one for me.", twofer("bob.Bob"))
    }

    @Test
    fun emptyStringGiven() {
        assertEquals("One for , one for me.", twofer(""))
    }

}
