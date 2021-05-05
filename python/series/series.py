def slices(series: str, n: int) -> list[str]:
    """Output all contiguous substrings of length n"""
    if not series:
        raise ValueError("Series may not be empty")
    if n > len(series) or n < 1:
        raise ValueError("n must be in range [1, len(series)]")
    return [series[i:i + n] for i in range(len(series) - n + 1)]
