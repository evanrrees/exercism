package yacht


enum class YachtCategory(val score: List<Int>.() -> Int) {
    ONES({ count { it == 1 } }),
    TWOS({ 2 * count { it == 2 } }),
    THREES({ 3 * count { it == 3 } }),
    FOURS({ 4 * count { it == 4 }}),
    FIVES({ 5 * count { it == 5 }}),
    SIXES({ 6 * count { it == 6 }}),
    FULL_HOUSE({ if (groupingBy { it }.eachCount().let { it.size == 2 && it.containsValue(2) }) sum() else 0 }),
    FOUR_OF_A_KIND({ (groupingBy { it }.eachCount().entries.singleOrNull { it.value >= 4 }?.key ?: 0) * 4 }),
    LITTLE_STRAIGHT({ if (this == listOf(1, 2, 3, 4, 5)) 30 else 0 }),
    BIG_STRAIGHT({ if (this == listOf(2, 3, 4, 5, 6)) 30 else 0 }),
    CHOICE({ sum() }),
    YACHT({ if (distinct().size == 1) 50 else 0 });
    operator fun invoke(dice: List<Int>): Int = score(dice)
}