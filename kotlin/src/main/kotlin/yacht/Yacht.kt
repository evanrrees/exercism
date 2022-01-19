package yacht

object Yacht {

    fun solve(category: YachtCategory, vararg dice: Int) = category(dice.sorted())
}
