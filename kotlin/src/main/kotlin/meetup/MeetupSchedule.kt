package meetup

import java.time.LocalDate

/** Describes a weekday ordinal for a given month.
 *
 * [TEENTH] indicates that the English ordinal of the day ends in 'teenth', i.e. the thirteenth through nineteenth.
 */
internal enum class MeetupSchedule {

    FIRST, SECOND, THIRD, FOURTH, LAST, TEENTH;

    /** Select the appropriate element from a sequence of local dates. */
    val selector: Sequence<LocalDate>.() -> LocalDate
        get() = when (this) {
            TEENTH -> { -> first { it.dayOfMonth > 12 } }
            LAST   -> { -> last() }
            else   -> { -> elementAt(ordinal) }
        }

}
