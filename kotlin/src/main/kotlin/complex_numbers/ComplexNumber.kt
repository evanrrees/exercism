package complex_numbers

import kotlin.math.*

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {
    val a get() = real
    val b get() = imag

    operator fun plus(other: ComplexNumber) = ComplexNumber(a + other.a, b + other.b)
    operator fun minus(other: ComplexNumber) = ComplexNumber(a - other.a, b - other.b)
    operator fun times(other: ComplexNumber) = other.let { (c, d) -> ComplexNumber((a * c - b * d), (b * c + a * d)) }
    operator fun div(other: ComplexNumber) = other
        .let { (c, d) -> ComplexNumber((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d)) }

    fun conjugate() = ComplexNumber(a, -b)

    val abs: Double get() = sqrt(a * a + b * b)

    val exponential: ComplexNumber get() = E.pow(a).let { ComplexNumber(it * cos(b), it * sin(b)) }

}

fun exponential(number: ComplexNumber) = number.exponential

