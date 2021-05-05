def is_isogram(phrase: str) -> bool:
    """Logical check if string is isogram"""
    chars = [x for x in phrase.lower() if x.isalpha()]
    return len(chars) == len(set(chars))
