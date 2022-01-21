package meetup

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth


/**
 * Calculates the date of meetups in a given month.
 *
 * @param month the month-of-year to represent, from 1 (January) to 12 (December).
 * @param year the year to represent, from MIN_YEAR to MAX_YEAR.
 * @see MeetupSchedule
 */
internal class Meetup(private val month: Int, private val year: Int) {

    /** Gets a sequence of the dates in this YearMonth. */
    private val daySequence: Sequence<LocalDate>
        get() = YearMonth.of(year, month).run { generateSequence(atDay(1)) { it.plusDays(1L) }.take(lengthOfMonth()) }

    /** Calculates the date of a meetup. */
    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule) = daySequence
        .filter { it.dayOfWeek == dayOfWeek }
        .run(schedule.selector)

}