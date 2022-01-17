package robot_name

class Robot {

    val name: String get() = _name
    private var _name: String = generateName()

    fun reset() {
        var new: String = generateName()
        while (new != _name) new = generateName()
        cache.remove(_name)
        _name = generateName()
    }

    companion object {
        private val cache = mutableSetOf<String>()
        private val LETTERS = 'A'..'Z'
        private val DIGITS = 0..9
        private const val CACHE_MAX_SIZE = 26 * 26 * 10 * 10 * 10
        tailrec fun generateName(): String {
            check(cache.size < CACHE_MAX_SIZE) { "Max number of unique names has been generated." }
            val name = buildString {
                repeat(2) { append(LETTERS.random()) }
                repeat(3) { append(DIGITS.random()) }
            }
            return if (cache.add(name)) name else generateName()
        }
    }

}
