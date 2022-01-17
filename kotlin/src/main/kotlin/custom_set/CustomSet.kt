package custom_set

class CustomSet(vararg values: Int) : LinkedHashSet<Int>(values.size) {

    init {
        for (element in values) add(element)
    }

    fun isSubset(other: CustomSet): Boolean = intersection(other) == this

    fun isDisjoint(other: CustomSet): Boolean = intersection(other).isEmpty()

    fun intersection(other: CustomSet): CustomSet = intersect(other).toCollection(CustomSet())

}
