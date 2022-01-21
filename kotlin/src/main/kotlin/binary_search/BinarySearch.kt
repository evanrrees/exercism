package binary_search

internal object BinarySearch {
    tailrec fun search(list: List<Int>, item: Int, left: Int = 0, right: Int = list.lastIndex): Int {
        val mid: Int = left + ((right - left) shr 1)
        return when {
            left > right      -> throw NoSuchElementException()
            item == list[mid] -> mid
            item < list[mid]  -> search(list, item, left, mid - 1)
            else              -> search(list, item, mid + 1, right)
        }
    }
}
