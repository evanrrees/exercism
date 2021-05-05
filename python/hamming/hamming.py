def distance(strand_a: str, strand_b: str) -> int:
    """Return hamming distance between two strings."""
    if len(strand_a) != len(strand_b):
        raise ValueError("strand_a and strand_b must be of equal length.")
    return sum(a != b for a, b in zip(strand_a, strand_b))
