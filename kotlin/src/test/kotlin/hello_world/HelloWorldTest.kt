package hello_world

import org.junit.Test
import kotlin.test.assertEquals

internal class HelloWorldTest {

    @Test
    fun helloWorldTest() {
        assertEquals("Hello, World!", hello())
    }

}
