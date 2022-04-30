# Score categories.
# Change the values as you see fit.
def YACHT(dice: list[int]) -> int:
    return 50 if dice.count(5) == 5 else 0


def ONES(dice: list[int]) -> int:
    return dice.count(1)


def TWOS(dice: list[int]) -> int:
    return dice.count(2) * 2


def THREES(dice: list[int]) -> int:
    return dice.count(3) * 3


def FOURS(dice: list[int]) -> int:
    return dice.count(4) * 4


def FIVES(dice: list[int]) -> int:
    return dice.count(5) * 5


def SIXES(dice: list[int]) -> int:
    return dice.count(6) * 6


def FULL_HOUSE(dice: list[int]) -> int:
    if len(counts := tabulate(dice)) != 2:
        return 0
    if 2 not in counts.values():
        return 0
    return sum(dice)


def FOUR_OF_A_KIND(dice: list[int]) -> int:
    return sum(key * 4 for key, value in tabulate(dice).items() if value >= 4)


def LITTLE_STRAIGHT(dice: list[int]) -> int:
    return 30 if sorted(dice) == [1, 2, 3, 4, 5] else 0


def BIG_STRAIGHT(dice: list[int]) -> int:
    return 30 if sorted(dice) == [2, 3, 4, 5, 6] else 0


def CHOICE(dice: list[int]) -> int:
    return sum(dice)


def tabulate(dice: list[int]) -> dict[int, int]:
    counts = {}
    for die in dice:
        counts[die] = counts.get(die, 0) + 1
    return counts


def score(dice, category) -> int:
    return category(dice)
