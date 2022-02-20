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

    fun helper2(knapsack: Item = Item(0, 0), others: List<Item> = items) {
        var foundOther = false
        for (item in others) {
            if (item.weight + knapsack.weight <= maximumWeight) {
                foundOther = true
                helper2(knapsack + item, others - item)
            }
        }
        if (!foundOther && knapsack.value > best.value) best = knapsack
    }

    fun helper(knapsack: Item = Item(0, 0), others: List<Item> = items) {
        val candidates = others.filter { it.weight + knapsack.weight <= maximumWeight }
        if (candidates.isEmpty()) {
            if (knapsack.value > best.value) best = knapsack
        } else
            for (other in candidates) helper(knapsack + other, others - other)
    }
    helper2()
    return  best.value
}
