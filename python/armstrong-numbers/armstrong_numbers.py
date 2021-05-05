def is_armstrong_number(number: int) -> bool:
    x = len(str(number))
    return sum(int(d) ** x for d in str(number)) == number
