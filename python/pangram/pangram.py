def is_pangram(sentence: str) -> bool:
    return len(set(c.lower() for c in sentence if c.isalpha())) == 26
