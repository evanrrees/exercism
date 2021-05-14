def square_of_sum(n: int) -> int:
    """Sum numbers [1..n] and return the square."""
    return (n * (n + 1) // 2) ** 2


def sum_of_squares(n: int) -> int:
    """Square numbers [1..n] and return the sum."""
    return n * (n + 1) * (2 * n + 1) // 6


def difference_of_squares(n: int) -> int:
    """Calculate the difference between the sum of squares and squared sum of [1..n]."""
    return (3 * n ** 2 + 2 * n) * (n ** 2 - 1) // 12
