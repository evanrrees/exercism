"""Solution to Ellen's Alien Game exercise."""


class Alien:
    """Create an Alien object with location x_coordinate and y_coordinate.

    Attributes
    ----------
    (class)total_aliens_created: int
    x_coordinate: int - Position on the x-axis.
    y_coordinate: int - Position on the y-axis.
    health: int - Amount of health points.

    Methods
    -------
    hit(): Decrement Alien health by one point.
    is_alive(): Return a boolean for if Alien is alive (if health is > 0).
    teleport(new_x_coordinate, new_y_coordinate): Move Alien object to new coordinates.
    collision_detection(other): Implementation TBD.
    """
    x_coordinate: int
    y_coordinate: int
    health: int = 3
    total_aliens_created: int = 0

    def __init__(self, x_coordinate: int, y_coordinate: int):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate
        Alien.total_aliens_created += 1

    def hit(self) -> None:
        self.health -= 1

    def is_alive(self) -> bool:
        return self.health > 0

    def teleport(self, new_x_coordinate: int, new_y_coordinate: int) -> None:
        self.x_coordinate = new_x_coordinate
        self.y_coordinate = new_y_coordinate

    def collision_detection(self, other):
        pass


def new_aliens_collection(position_data: list[tuple[int, int]]) -> list[Alien]:
    return [Alien(x, y) for x, y in position_data]
