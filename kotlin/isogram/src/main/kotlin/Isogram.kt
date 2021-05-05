object Isogram {
    fun isIsogram(input: String) =
        input.filter(Char::isLetter).groupingBy(Char::toLowerCase).eachCount().all { it.value == 1 }
}
