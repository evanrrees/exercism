package knapsack

//data class Item(val weight: Int, val value: Int) {
//    operator fun plus(other: Item) = Item(weight + other.weight, value + other.value)
//}

typealias Item = Pair<Int, Int>
val Item.weight get() = first
val Item.value get() = second
operator fun Item.plus(other: Item) = Item(weight + other.weight, value + other.value)

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    if (items.isEmpty()) return 0
    var best = Item(0, 0)
    val seen = BooleanArray(items.size)
    fun helper2(knapsack: Item = Item(0, 0)) {
        var checkBest = true
        for (index in items.indices) {
            if (!seen[index] && items[index].weight + knapsack.weight <= maximumWeight) {
                checkBest = false
                seen[index] = true
                helper2(knapsack + items[index])
                seen[index] = false
            }
        }
        if (checkBest && knapsack.value > best.value)
            best = knapsack
    }
    helper2()
    return  best.value
}
