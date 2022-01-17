package linked_list

class Deque<T> {

    val list = mutableListOf<T>()

    fun push(value: T) = list.add(value)

    fun pop(): T? = list.removeLast()

    fun shift(): T? = list.removeFirst()

    fun unshift(value: T) = list.add(0, value)

}
