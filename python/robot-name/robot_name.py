from string import ascii_uppercase, digits
from random import choices


past_names: set[str] = set()
max_names: int = 26**2 * 10**3


class Robot:

    name: str

    def __init__(self) -> None:
        self.reset()

    def reset(self) -> None:
        assert len(past_names) < max_names
        while (new := self._generate_name()) in past_names:
            pass
        past_names.add(new)
        self.name = new

    @staticmethod
    def _generate_name() -> str:
        return ''.join(choices(ascii_uppercase, k=2) + choices(digits, k=3))
