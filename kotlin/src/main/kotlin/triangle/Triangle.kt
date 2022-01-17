package triangle

class Triangle<out T : Number>(vararg _sides: T) {

    val sides = _sides.map { it.toDouble() }

    init {
        require(_sides.size == 3)
        require(sides.run { none { sum() - it < it } })
        require(sides.all { it > 0.0 })
    }

    val isEquilateral: Boolean = sides.toSet().size == 1
    val isIsosceles  : Boolean = sides.toSet().size < 3
    val isScalene    : Boolean = !isIsosceles

}
