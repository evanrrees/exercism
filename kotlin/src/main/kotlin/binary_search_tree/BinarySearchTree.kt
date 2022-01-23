package binary_search_tree

internal class BinarySearchTree<T : Comparable<T>> {

    data class Node<T : Comparable<T>>(val data: T, var left: Node<T>? = null, var right: Node<T>? = null) {

        override fun toString() = "Node($data) -> (${left?.data ?: " "}, ${right?.data ?: " "})"

        internal fun traverseDF(): List<T> = (left?.traverseDF() ?: emptyList()) + listOf(data) + (right?.traverseDF() ?: emptyList())

        internal fun traverseBF(list: MutableList<T> = mutableListOf(), queue: MutableList<Node<T>> = mutableListOf()): List<T> {
            list.add(data)
            left?.let(queue::add)
            right?.let(queue::add)
            return if (queue.isEmpty()) list
            else queue.removeFirst().traverseBF(list, queue)
        }

        internal fun insert(node: Node<T>) {
            when {
                node.data < data -> left?.insert(node) ?: apply { left = node }
                node.data > data -> right?.insert(node) ?: apply { right = node }
                else -> apply { node.left = left; left = node }
            }
        }

    }

    var root: Node<T>? = null

    fun insert(value: T) = root?.insert(Node(value)) ?: apply { root = Node(value) }

    fun asLevelOrderList() = root?.traverseBF() ?: emptyList()

    fun asSortedList(): List<T> = root?.traverseDF() ?: emptyList()

}
