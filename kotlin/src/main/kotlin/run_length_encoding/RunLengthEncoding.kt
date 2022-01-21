package run_length_encoding

internal fun encode(input: String) = Regex("(.)\\1*")
    .findAll(input).joinToString("") { it.groupValues.let { (a, b) -> if (a.length > 1) "${a.length}$b" else b } }

internal fun decode(input: String) = Regex("(\\p{Digit}*)(\\p{Alnum}|\\p{Space})")
    .findAll(input).joinToString("") { it.destructured.let { (a, b) -> b.repeat(a.toIntOrNull() ?: 1) } }
