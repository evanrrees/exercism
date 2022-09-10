def sum_of_multiples(limit: int, multiples: list[int]) -> int:
    _multiples = set()
    for num in multiples:
        if num == 0:
            continue
        for multiple in range(num, limit, num):
            _multiples.add(multiple)
    return sum(_multiples)
