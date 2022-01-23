package binary_search_tree

class BinarySearchTree<T : Comparable<T>> {

    data class Node<T : Comparable<T>>(val data: T, var left: Node<T>? = null, var right: Node<T>? = null): Comparable<Node<T>> {
        override fun compareTo(other: Node<T>) = data.compareTo(other.data)
        override fun toString(): String {
            return buildString {
                append("Node($data) -> ")
                append(left?.data ?: "_")
                append(",")
                append(right?.data ?: "_")
            }
        }
    }

    var root: Node<T>? = null

    fun insert(value: T) = insert(Node(value))

    private fun insert(node: Node<T>) = if (root == null) root = node else insert(node, root!!)

    private fun insert(node: Node<T>, parent: Node<T>) {
        when {
            node < parent ->
                if (parent.left != null) insert(node, parent.left!!)
                else parent.left = node
            node > parent ->
                if (parent.right != null) insert(node, parent.right!!)
                else parent.right = node
            else -> {
                node.left = parent.left
                parent.left = node
            }
        }
    }

    fun asLevelOrderList() = breadthFirstTraversal(mutableListOf(root))

    fun asSortedList(): List<T> = depthFirstTraversal(root)

    private fun depthFirstTraversal(node: Node<T>?): List<T> =
        if (node == null) emptyList()
        else depthFirstTraversal(node.left) + listOf(node.data) + depthFirstTraversal(node.right)

    private tailrec fun breadthFirstTraversal(queue: MutableList<Node<T>?>, list: MutableList<T> = mutableListOf()): MutableList<T> {
        if (queue.isEmpty()) return list
        val node = queue.removeFirst()
        if (node != null) {
            list.add(node.data)
            queue.add(node.left)
            queue.add(node.right)
        }
        return breadthFirstTraversal(queue, list)
    }

}
