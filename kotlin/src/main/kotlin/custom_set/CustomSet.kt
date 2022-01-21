package custom_set

internal class CustomSet(vararg elements: Int) : HashSet<Int>(elements.asList()) {

    fun isSubset(other: CustomSet) = all { it in other }

    fun intersection(other: CustomSet) = intersect(other).toCollection(CustomSet())

    fun isDisjoint(other: CustomSet) = none { it in other }

}
