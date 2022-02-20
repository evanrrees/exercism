package knapsack

data class Item(val weight: Int, val value: Int)

typealias Items = List<Item>

data class Knapsack(val items: Items = emptyList()) {
    val value get() = items.sumOf { it.value }
    val weight get() = items.sumOf { it.weight }
    operator fun plus(item: Item) = Knapsack(items + item)
}

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    if (items.isEmpty()) return 0
    var best = Knapsack()
    fun helper(knapsack: Knapsack = Knapsack(), others: Items = items) {
        val candidates = others.filter { it.weight + knapsack.weight <= maximumWeight }
        if (candidates.isEmpty()) {
            if (knapsack.value > best.value) best = knapsack
        } else
            for (other in candidates) helper(knapsack + other, others - other)
    }
    helper()
    return  best.value
}
