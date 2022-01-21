package gigasecond

import java.time.LocalDate
import java.time.LocalDateTime

internal class Gigasecond(localDateTime: LocalDateTime) {

    val date: LocalDateTime = localDateTime
        get() = field.plusSeconds(1_000_000_000)

    constructor(localDate: LocalDate): this(localDate.atStartOfDay())

}
