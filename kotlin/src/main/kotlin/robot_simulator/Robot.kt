package robot_simulator

internal class Robot(position: GridPosition = GridPosition(), orientation: Orientation = Orientation.NORTH) {

    val orientation: Orientation get() = Orientation.values()[o]
    val gridPosition: GridPosition get() = GridPosition(x, y)

    private var o = orientation.ordinal
        set(value) { field = value; field %= 4 }
    private var x = position.x
    private var y = position.y

    private val advances = arrayOf({ y++ }, { x++ }, { y-- }, { x-- })
    private val operations = mapOf('R' to { o++ }, 'L' to { o += 3 }, 'A' to { advances[o]() })

    fun simulate(instructions: String) = instructions.forEach { operations.getValue(it)() }
}
