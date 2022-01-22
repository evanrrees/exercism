package all_your_base

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

internal class BaseConverterTest {

    /*
     * See https://github.com/junit-team/junit4/wiki/Rules for information on JUnit Rules in general and
     * ExpectedExceptions in particular.
     */
    @Rule @JvmField
    var expectedException: ExpectedException = ExpectedException.none()

    @Test
    fun testSingleBitOneToDecimal() {
        assertConversionEquals(base = 2, digits = intArrayOf(1), newBase = 10, expected = intArrayOf(1))
    }

    @Test
    fun testBinaryToSingleDecimal() {
        assertConversionEquals(base = 2, digits = intArrayOf(1, 0, 1), newBase = 10, expected = intArrayOf(5))
    }

    @Test
    fun testSingleDecimalToBinary() {
        assertConversionEquals(base = 10, digits = intArrayOf(5), newBase = 2, expected = intArrayOf(1, 0, 1))
    }

    @Test
    fun testBinaryToMultipleDecimal() {
        assertConversionEquals(
            base = 2,
            digits = intArrayOf(1, 0, 1, 0, 1, 0),
            newBase = 10,
            expected = intArrayOf(4, 2)
        )
    }

    @Test
    fun testDecimalToBinary() {
        assertConversionEquals(
            base = 10,
            digits = intArrayOf(4, 2),
            newBase = 2,
            expected = intArrayOf(1, 0, 1, 0, 1, 0)
        )
    }

    @Test
    fun testTrinaryToHexadecimal() {
        assertConversionEquals(base = 3, digits = intArrayOf(1, 1, 2, 0), newBase = 16, expected = intArrayOf(2, 10))
    }

    @Test
    fun testHexadecimalToTrinary() {
        assertConversionEquals(base = 16, digits = intArrayOf(2, 10), newBase = 3, expected = intArrayOf(1, 1, 2, 0))
    }

    @Test
    fun test15BitInteger() {
        assertConversionEquals(
            base = 97,
            digits = intArrayOf(3, 46, 60),
            newBase = 73,
            expected = intArrayOf(6, 10, 45)
        )
    }

    @Test
    fun testEmptyDigits() {
        assertThrows(IllegalArgumentException::class.java) { BaseConverter(2, intArrayOf()) }

        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("You must supply at least one digit.")

        BaseConverter(2, intArrayOf())
    }

    @Test
    fun testSingleZero() {
        assertConversionEquals(base = 10, digits = intArrayOf(0), newBase = 2, expected = intArrayOf(0))
    }

    @Test
    fun testMultipleZeros() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Digits may not contain leading zeros.")

        BaseConverter(10, intArrayOf(0, 0, 0))
    }

    @Test
    fun testLeadingZeros() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Digits may not contain leading zeros.")

        BaseConverter(7, intArrayOf(0, 6, 0))
    }

    @Test
    fun testFirstBaseIsOne() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        BaseConverter(1, intArrayOf(0))
    }

    @Test
    fun testFirstBaseIsZero() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        BaseConverter(0, intArrayOf())
    }

    @Test
    fun testFirstBaseIsNegative() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        BaseConverter(-2, intArrayOf(1))
    }

    @Test
    fun testNegativeDigit() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Digits may not be negative.")

        BaseConverter(2, intArrayOf(1, -1, 1, 0, 1, 0))
    }

    @Test
    fun testInvalidPositiveDigit() {
        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("All digits must be strictly less than the base.")

        BaseConverter(2, intArrayOf(1, 2, 1, 0, 1, 0))
    }

    @Test
    fun testSecondBaseIsOne() {
        val baseConverter = BaseConverter(2, intArrayOf(1, 0, 1, 0, 1, 0))

        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        baseConverter.convertToBase(1)
    }

    @Test
    fun testSecondBaseIsZero() {
        val baseConverter = BaseConverter(2, intArrayOf(1, 0, 1, 0, 1, 0))

        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        baseConverter.convertToBase(0)
    }

    @Test
    fun testSecondBaseIsNegative() {
        val baseConverter = BaseConverter(2, intArrayOf(1))

        expectedException.expect(IllegalArgumentException::class.java)
        expectedException.expectMessage("Bases must be at least 2.")

        baseConverter.convertToBase(-7)
    }

    private fun assertConversionEquals(base: Int, digits: IntArray, newBase: Int, expected: IntArray) {
        val baseConverter = BaseConverter(base, digits)
        val actualDigits = baseConverter.convertToBase(newBase)
        assertArrayEquals(
            "Expected digits: ${expected.asList()} but found digits: ${actualDigits.asList()}",
            expected,
            actualDigits
        )
    }
}
