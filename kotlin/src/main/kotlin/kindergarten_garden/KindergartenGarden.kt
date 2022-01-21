package kindergarten_garden

internal class KindergartenGarden(diagram: String) {

    private val plants = mapOf('G' to "grass", 'C' to "clover", 'R' to "radishes", 'V' to "violets")

    private val diagram = diagram.split("\n")
        .map { it.map(plants::getValue).chunked(2) }
        .let { (top, bottom) -> top.zip(bottom) { a, b -> a + b } }

    fun getPlantsOfStudent(student: String) = diagram[student[0] - 'A']

}
