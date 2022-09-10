def transform(legacy_data: dict[int, list[str]]) -> dict[str, int]:
    return {item.lower(): key for key, value in legacy_data.items() for item in value}
