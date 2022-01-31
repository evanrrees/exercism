package zebra_puzzle

import kotlin.math.abs

data class Resident(
    val nationality: String,
    val cigarette: String,
    val drink: String,
    val houseColor: String,
    val pet: String,
    val houseNumber: String
) {
    constructor(list: List<String>): this(
        nationality = list[0],
        cigarette = list[1],
        drink = list[2],
        houseColor = list[3],
        pet = list[4],
        houseNumber = list[5]
    )
    companion object {
        val categories = mapOf(
            "nationalities" to listOf("Englishman", "Spaniard", "Japanese", "Norwegian", "Ukrainian"),
            "cigarettes" to listOf("Old Gold", "Kools", "Parliament", "Lucky Strike", "Chesterfield"),
            "drinks" to listOf("Milk", "Coffee", "Tea", "Orange Juice", "Water"),
            "houseColors" to listOf("Blue", "Red", "Yellow", "Green", "Ivory"),
            "pets" to listOf("Snails", "Dog", "Zebra", "Horse", "Fox"),
            "houseNumbers" to listOf("1", "2", "3", "4", "5"),
        )
    }
    fun clashesWith(other: Resident) = nationality == other.nationality
            || cigarette == other.cigarette
            || drink == other.drink
            || houseColor == other.houseColor
            || pet == other.pet
            || houseNumber == other.houseNumber
}

fun allTuples(
    categories: Map<String, List<String>> = Resident.categories,
    tuples: List<List<String>> = emptyList()
): List<List<String>> =
    if (categories.isEmpty()) tuples
    else {
        val (category, values) = categories.entries.first()
        allTuples(
            categories - category,
            tuples.flatMap { list -> values.map { list + it } }.ifEmpty { values.map(::listOf) }
        )
    }


val constraints = listOf(
    andConstraint({ nationality }, "Englishman", { houseColor }, "Red"),
    andConstraint({ nationality }, "Spaniard", { pet }, "Dog"),
    andConstraint({ drink }, "Coffee", { houseColor }, "Green"),
    andConstraint({ nationality }, "Ukrainian", { drink }, "Tea"),
    andConstraint({ cigarette }, "Old Gold", { pet }, "Snails"),
    andConstraint({ cigarette }, "Kools", { houseColor }, "Yellow"),
    andConstraint({ drink }, "Milk", { houseNumber }, "3"),
    andConstraint({ nationality }, "Norwegian", { houseNumber }, "1"),
    andConstraint({ cigarette }, "Lucky Strike", { drink }, "Orange Juice"),
    andConstraint({ nationality }, "Japanese", { cigarette }, "Parliament")
)

fun interface Constraint<T>: (T) -> Boolean
fun interface UnaryConstraint: Constraint<Resident>
fun interface SetConstraint: Constraint<Set<Resident>>

fun andConstraint(
    category1: Resident.() -> String, value1: String, category2: Resident.() -> String, value2: String
) = UnaryConstraint {
    it.category1() != value1 && it.category2() != value2 || it.category1() == value1 && it.category2() == value2
}

fun relationalConstraint(
    category1: Resident.() -> String, value1: String, category2: Resident.() -> String, value2: String,
    predicate: (Resident, Resident) -> Boolean
) = SetConstraint { list ->
    predicate(list.single { it.category1() == value1 }, list.single { it.category2() == value2 })
}

fun adjacentConstraint(
    category1: Resident.() -> String,
    value1: String,
    category2: Resident.() -> String,
    value2: String
): SetConstraint =
    relationalConstraint(category1, value1, category2, value2) { a, b ->
        abs(a.houseNumber.toInt() - b.houseNumber.toInt()) == 1
    }

fun distinctConstraint(category: Resident.() -> String) =
    SetConstraint { list -> list.distinctBy(category).size == list.size }

val setConstraints = listOf(
    distinctConstraint { nationality },
    distinctConstraint { drink },
    distinctConstraint { pet },
    distinctConstraint { cigarette },
    distinctConstraint { houseNumber },
    distinctConstraint { houseColor },
    relationalConstraint({ houseColor }, "Green", { houseColor }, "Ivory") { a, b ->
        a.houseNumber.toInt() == b.houseNumber.toInt() + 1
    },
    adjacentConstraint({ cigarette }, "Chesterfield", { pet }, "Fox"),
    adjacentConstraint({ cigarette }, "Kools", { pet }, "Horse"),
    adjacentConstraint({ nationality }, "Norwegian", { houseColor }, "Blue")
)

val residents = allTuples().map(::Resident).filter { resident -> constraints.all { it(resident) } }

val solution = listOf(
    Resident("Norwegian", "Kools", "Water", "Yellow", "Fox", "1"),
    Resident("Ukrainian", "Chesterfield", "Tea", "Blue", "Horse", "2"),
    Resident("Englishman", "Old Gold", "Milk", "Red", "Snails", "3"),
    Resident("Spaniard", "Lucky Strike", "Orange Juice", "Ivory", "Dog", "4"),
    Resident("Japanese", "Parliament", "Coffee", "Green", "Zebra", "5")
)

fun findSets(path: Set<Resident> = emptySet(), others: List<Resident> = residents) {
    if (path.size == 5) {
        if (setConstraints.all { it(path) }) paths += path
    } else {
        for (other in others) if (path.none { it.clashesWith(other) }) findSets(path + other, others - other)
    }
}

val paths = mutableSetOf<Set<Resident>>()

fun runMeToSolveThisDamnableProblemFunctionallyButReallyIHateIt() {
    findSets()
    paths.single().joinToString("\n").let(::println)
}
