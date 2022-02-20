package knapsack

typealias Item = Pair<Int, Int>
val Item.weight get() = first
val Item.value get() = second
operator fun Item.plus(other: Item) = Item(weight + other.weight, value + other.value)

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    val seen = BooleanArray(items.size)
    var bestValue = 0
    fun helper(weight: Int = 0, value: Int = 0) {
        for ((index, item) in items.withIndex()) {
            if (!seen[index] && item.weight + weight <= maximumWeight) {
                seen[index] = true
                helper(weight + item.weight, value + item.value)
                seen[index] = false
            }
        }
        bestValue = maxOf(value, bestValue)
    }
    helper()
    return bestValue
}
