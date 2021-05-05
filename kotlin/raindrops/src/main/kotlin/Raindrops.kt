object Raindrops {

    fun convert(n: Int) = sequenceOf(3 to 'i', 5 to 'a', 7 to 'o')
        .filter { n % it.first == 0 }
        .joinToString("") { "Pl${it.second}ng" }
        .ifEmpty { "$n" }

}
