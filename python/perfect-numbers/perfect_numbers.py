def classify(n: int) -> str:
    if n < 1:
        raise ValueError(f"number must be a positive integer: {n}")
    a_sum = sum(x for x in range(1, n // 2 + 1) if not n % x and x < n)
    if a_sum > n:
        return "abundant"
    if a_sum < n:
        return "deficient"
    return "perfect"
