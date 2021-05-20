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
class Meetup(month: Int, year: Int) {

    /** Represents the year and month of this meetup. */
    private val yearMonth: YearMonth = YearMonth.of(year, month)

    /** Gets a sequence of the dates in this YearMonth. */
    private val daySequence: Sequence<LocalDate>
        get() = yearMonth.run { generateSequence(atDay(1)) { it.plusDays(1L) }.take(lengthOfMonth()) }

    /** Calculates the date of a meetup. */
    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule) = daySequence.run {
        when (schedule) {
            MeetupSchedule.TEENTH -> drop(12).first { it.dayOfWeek == dayOfWeek }
            MeetupSchedule.LAST -> last { it.dayOfWeek == dayOfWeek }
            else -> filter { it.dayOfWeek == dayOfWeek }.elementAt(schedule.ordinal)
        }
    }

}