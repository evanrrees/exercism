gifts: list[str] = ["a Partridge in a Pear Tree.", "two Turtle Doves", "three French Hens", "four Calling Birds",
                    "five Gold Rings", "six Geese-a-Laying", "seven Swans-a-Swimming", "eight Maids-a-Milking",
                    "nine Ladies Dancing", "ten Lords-a-Leaping", "eleven Pipers Piping", "twelve Drummers Drumming"]

ordinals: list[str] = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth",
                       "eleventh", "twelfth"]


def verse(index: int) -> str:
    """Recite the (index + 1)th verse."""
    return f"On the {ordinals[index]} day of Christmas my true love gave to me: {', '.join(gifts[index:0:-1])}" \
        f"{', and ' if index else ''}{gifts[0]}"


def recite(start_verse: int, end_verse: int) -> list[str]:
    """Recite the Twelve Days of Christmas from start_verse to end_verse."""
    return [verse(i - 1) for i in range(start_verse, end_verse + 1)]
