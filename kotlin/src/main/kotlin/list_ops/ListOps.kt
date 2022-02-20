package list_ops

internal fun <T> List<T>.customAppend(list: List<T>): List<T> {
    val newList = ArrayList<T>(customSize + list.customSize)
    for (element in this) newList.add(element)
    for (element in list) newList.add(element)
    return newList
}

internal fun List<Any>.customConcat(): List<Any> {
    val newList = ArrayList<Any>()
    val queue = ArrayList<Any>(this)
    var index = 0
    while (true) {
        try {
            val next = queue[index++]
            if (next is Iterable<*>) for (item in next) queue.add(item!!) else newList.add(next)
        } catch (e: IndexOutOfBoundsException) {
            if (index > 0) break else throw e
        }
    }
    return newList
}

internal fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val newList = ArrayList<T>(customSize)
    for (element in this) if (predicate(element)) newList.add(element)
    return newList
}

internal val <T> List<T>.customSize: Int get() {
    var size = 0
    for (element in this) size++
    return size
}

internal fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    val size = customSize
    val newList = ArrayList<U>(size)
    for (index in 0 until size) newList.add(transform(this[index]))
    return newList
}

internal fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var value = initial
    for (index in 0 until customSize) value = f(value, this[index])
    return value
}

internal fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var value = initial
    for (index in customSize - 1 downTo 0) value = f(this[index], value)
    return value
}

internal fun <T> List<T>.customReverse(): List<T> {
    val size = customSize
    val newList = ArrayList<T>(size)
    for (index in size - 1 downTo 0) newList.add(this[index])
    return newList
}
