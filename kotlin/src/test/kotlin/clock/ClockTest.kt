package clock

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal object ClockTest {

    class ClockEqualTest {

        @Test
        fun `same time`() = assertEquals(Clock(15, 37), Clock(15, 37))

        @Test
        fun `clocks a minute apart`() = assertNotEquals(Clock(15, 36), Clock(15, 37))

        @Test
        fun `clocks an hour apart`() = assertNotEquals(Clock(14, 37), Clock(15, 37))

        @Test
        fun `hour overflow`() = assertEquals(Clock(10, 37), Clock(34, 37))

        @Test
        fun `hour overflow by several days`() = assertEquals(Clock(3, 11), Clock(99, 11))

        @Test
        fun `negative hour`() = assertEquals(Clock(22, 40), Clock(-2, 40))

        @Test
        fun `negative hour that wraps`() = assertEquals(Clock(17, 3), Clock(-31, 3))

        @Test
        fun `negative hour that wraps multiple times`() = assertEquals(Clock(13, 49), Clock(-83, 49))

        @Test
        fun `minute overflow`() = assertEquals(Clock(0, 1), Clock(0, 1441))

        @Test
        fun `minute overflow by several days`() = assertEquals(Clock(2, 2), Clock(2, 4322))

        @Test
        fun `negative minute`() = assertEquals(Clock(2, 40), Clock(3, -20))

        @Test
        fun `negative minute that wraps`() = assertEquals(Clock(4, 10), Clock(5, -1490))

        @Test
        fun `negative minute that wraps multiple times`() = assertEquals(Clock(6, 15), Clock(6, -4305))

        @Test
        fun `negative hours and minutes`() = assertEquals(Clock(7, 32), Clock(-12, -268))

        @Test
        fun `negative hours and minutes that wrap`() = assertEquals(Clock(18, 7), Clock(-54, -11513))

        @Test
        fun `full clock and zeroed clock`() = assertEquals(Clock(24, 0), Clock(0, 0))

    }

    class ClockCreationTest {

        @Test
        fun `on the hour`() = assertEquals("08:00", Clock(8, 0).toString())

        @Test
        fun `past the hour`() = assertEquals("11:09", Clock(11, 9).toString())

        @Test
        fun `midnight is zero hours`() = assertEquals("00:00", Clock(24, 0).toString())

        @Test
        fun `hour rolls over`() = assertEquals("01:00", Clock(25, 0).toString())

        @Test
        fun `hour rolls over continuously`() = assertEquals("04:00", Clock(100, 0).toString())

        @Test
        fun `sixty minutes is next hour`() = assertEquals("02:00", Clock(1, 60).toString())

        @Test
        fun `minutes roll over`() = assertEquals("02:40", Clock(0, 160).toString())

        @Test
        fun `minutes roll over continuously`() = assertEquals("04:43", Clock(0, 1723).toString())

        @Test
        fun `hour and minutes roll over`() = assertEquals("03:40", Clock(25, 160).toString())

        @Test
        fun `hour and minutes roll over continuously`() = assertEquals("11:01", Clock(201, 3001).toString())

        @Test
        fun `hour and minutes roll over to exactly midnight`() = assertEquals("00:00", Clock(72, 8640).toString())

        @Test
        fun `negative hour`() = assertEquals("23:15", Clock(-1, 15).toString())

        @Test
        fun `negative hour rolls over`() = assertEquals("23:00", Clock(-25, 0).toString())

        @Test
        fun `negative hour rolls over continuously`() = assertEquals("05:00", Clock(-91, 0).toString())

        @Test
        fun `negative minutes`() = assertEquals("00:20", Clock(1, -40).toString())

        @Test
        fun `negative minutes roll over`() = assertEquals("22:20", Clock(1, -160).toString())

        @Test
        fun `negative minutes roll over continuously`() = assertEquals("16:40", Clock(1, -4820).toString())

        @Test
        fun `negative sixty minutes is previous hour`() = assertEquals("01:00", Clock(2, -60).toString())

        @Test
        fun `negative hour and minutes both roll over`() = assertEquals("20:20", Clock(-25, -160).toString())

        @Test
        fun `negative hour and minutes both roll over continuously`() =
            assertEquals("22:10", Clock(-121, -5810).toString())

    }

    class ClockSubtractTest {

        @Test
        fun `subtract minutes`() =
            Clock(10, 3)
                .minusMinutes(3)
                .shouldBe("10:00")

        @Test
        fun `subtract to previous hour`() =
            Clock(10, 3)
                .minusMinutes(30)
                .shouldBe("09:33")

        @Test
        fun `subtract more than an hour`() =
            Clock(10, 3)
                .minusMinutes(70)
                .shouldBe("08:53")

        @Test
        fun `subtract across midnight`() =
            Clock(0, 3)
                .minusMinutes(4)
                .shouldBe("23:59")

        @Test
        fun `subtract more than two hours`() =
            Clock(0, 0)
                .minusMinutes(160)
                .shouldBe("21:20")

        @Test
        fun `subtract more than two hours with borrow`() =
            Clock(6, 15)
                .minusMinutes(160)
                .shouldBe("03:35")

        @Test
        fun `subtract more than one day`() =
            Clock(5, 32)
                .minusMinutes(1500)
                .shouldBe("04:32")

        @Test
        fun `subtract more than two days`() =
            Clock(2, 20)
                .minusMinutes(3000)
                .shouldBe("00:20")
    }

    class ClockAddTest {

        @Test
        fun `add minutes`() =
            Clock(10, 0)
                .plusMinutes(3)
                .shouldBe("10:03")

        @Test
        fun `add no minutes`() =
            Clock(6, 41)
                .plusMinutes(0)
                .shouldBe("06:41")

        @Test
        fun `add to next hour`() =
            Clock(0, 45)
                .plusMinutes(40)
                .shouldBe("01:25")


        @Test
        fun `add more than one hour`() =
            Clock(10, 0)
                .plusMinutes(61)
                .shouldBe("11:01")


        @Test
        fun `add more than two hours with carry`() =
            Clock(0, 45)
                .plusMinutes(160)
                .shouldBe("03:25")


        @Test
        fun `add across midnight`() =
            Clock(23, 59)
                .plusMinutes(2)
                .shouldBe("00:01")


        @Test
        fun `add more than one day`() =
            Clock(5, 32)
                .plusMinutes(1500)
                .shouldBe("06:32")


        @Test
        fun `add more than two days`() =
            Clock(1, 1)
                .plusMinutes(3500)
                .shouldBe("11:21")

    }

    private fun Clock.minusMinutes(minutes: Int): Clock {
        subtract(minutes)
        return this
    }

    private fun Clock.plusMinutes(minutes: Int): Clock {
        add(minutes)
        return this
    }

    private fun Clock.shouldBe(expectation: String) = assertEquals(expectation, toString())

}
