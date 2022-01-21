package robot_simulator

internal class Robot(position: GridPosition = GridPosition(), orientation: Orientation = Orientation.NORTH) {

    val gridPosition: GridPosition get() = GridPosition(x, y)
    val orientation: Orientation get() = Orientation.values()[o]

    private var x = position.x
    private var y = position.y
    private var o = orientation.ordinal
        set(value) { field = value; field %= 4 }

    private val advances = arrayOf({ y++ }, { x++ }, { y-- }, { x-- })
    private val operations = mapOf('R' to { o++ }, 'L' to { o += 3 }, 'A' to { advances[o]() })

    fun simulate(instructions: String) = instructions.forEach { operations.getValue(it)() }
}
