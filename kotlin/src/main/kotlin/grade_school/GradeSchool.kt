package grade_school

import java.util.TreeMap
import java.util.TreeSet

internal class School {

    private val roster = TreeMap(mutableMapOf<Int, TreeSet<String>>())

    fun add(student: String, grade: Int) = roster.getOrPut(grade) { TreeSet() }.add(student)

    fun grade(grade: Int): List<String> = roster[grade]?.toList().orEmpty()

    fun roster() = roster.values.flatten()

}
