package sublist

internal enum class Relationship { EQUAL, SUBLIST, SUPERLIST, UNEQUAL }

private infix fun <T> List<T>.isSublistOf(other: List<T>) = isEmpty() || other.windowed(size).any { it == this }

internal fun <T> List<T>.relationshipTo(other: List<T>): Relationship = when {
    this == other          -> Relationship.EQUAL
    this isSublistOf other -> Relationship.SUBLIST
    other isSublistOf this -> Relationship.SUPERLIST
    else                   -> Relationship.UNEQUAL
}