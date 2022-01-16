package `phone-number`

class PhoneNumber(number: String) {

    val number: String =
        Regex("^\\D*1?\\D*([2-9]\\d{2})\\D*([2-9]\\d{2})\\D*(\\d{4})\\D*$").matchEntire(number)
            ?.groupValues?.drop(1)?.joinToString("")
            ?: throw IllegalArgumentException()

}