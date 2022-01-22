package clock

import kotlin.math.floor

class Clock constructor(hours: Int, minutes: Int) {

    var minutes: Int = 0
        set(value) {
            if (value < 0) {
                hours += floor((value - field) / 60.0).toInt()
            }
            if (value > 0) {
                hours += floor(value / 60.0).toInt()
            }
//            if (value != 0) hours += floor((value - field) / 60.0).toInt()
            field = value.mod(60).plus(60).mod(60)
        }

    var hours: Int = 0
        set(value) {
            field = value.mod(24).plus(24).mod(24)
        }

    init {
        this.minutes += minutes
        this.hours += hours
    }

    fun add(minutes: Int) { this.minutes += minutes }

    fun subtract(minutes: Int) { this.minutes -= minutes }

    override fun toString() = "%02d:%02d".format(hours, minutes)

    override fun equals(other: Any?) =
        this === other || (other is Clock && hours == other.hours && minutes == other.minutes)

    override fun hashCode() = 31 * hours + minutes

//    companion object {
//        operator fun invoke(hours: Int, minutes: Int): Clock {
//            var m = minutes
//            var h = hours
//
//            h += floor(m / 60.0).toInt()
//
//            m %= 60
//            m += 60
//            m %= 60
//
//            h %= 24
//            h += 24
//            h %= 24
//
//            return Clock(h, m)
//        }
//    }

}
