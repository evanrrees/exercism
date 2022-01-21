package robot_simulator

import robot_simulator.Orientation.*

internal class Robot(startPosition: GridPosition = GridPosition(), startOrientation: Orientation = NORTH) {

    var orientation: Orientation = startOrientation
        private set
    val gridPosition: GridPosition get() = GridPosition(x, y)

    private var x = startPosition.x
    private var y = startPosition.y

    private fun rotate(n: Int = 1) {
        orientation = Orientation.values()[(orientation.ordinal + n) % 4]
    }

    private fun advance() {
        when (orientation) {
            NORTH -> y++
            EAST -> x++
            SOUTH -> y--
            WEST -> x--
        }
    }

    fun simulate(instructions: String) {
        instructions.forEach {
            when (it) {
                'R' -> rotate()
                'L' -> rotate(3)
                'A' -> advance()
                else -> throw UnsupportedOperationException("$it")
            }
        }
    }
}
