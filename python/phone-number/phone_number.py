from re import fullmatch


class PhoneNumber:
    """Store a NANP-formatted phone number."""
    area_code: str
    exchange_code: str
    subscriber_number: str

    def __init__(self, number: str):
        res = fullmatch('[\\D]*1?[\\D]*([2-9]\\d{2})[\\D]*([2-9]\\d{2})[\\D]*(\\d{4})[\\D]*', number)
        if res is None:
            raise ValueError(f"Invalid phone format: {number}")
        self.area_code, self.exchange_code, self.subscriber_number = res.groups()

    @property
    def number(self) -> str:
        """Return number as 9-digit string."""
        return f"{self.area_code}{self.exchange_code}{self.subscriber_number}"

    def pretty(self) -> str:
        """Return formatted number for pretty printing."""
        return f"({self.area_code})-{self.exchange_code}-{self.subscriber_number}"
