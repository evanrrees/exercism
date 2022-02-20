package react


internal class Reactor<T> {

    interface Subscription {
        fun cancel(): Boolean
    }

    fun interface Callback<T>: (T) -> Unit

    abstract class Cell<T> {
        abstract val value: T
        val watchers: MutableSet<Cell<T>> = mutableSetOf()
        fun notifyWatchers() = watchers.forEach { it.value }
        fun watch(other: Cell<T>) = other.watchers.add(this)
    }

    inner class InputCell(value: T): Cell<T>() {
        override var value = value
            set(value) {
                if (field != value) {
                    field = value
                    notifyWatchers()
                }
            }
    }

    inner class ComputeCell(vararg val watches: Cell<T>, val compute: (List<T>) -> T): Cell<T>() {
        init {
            watches.forEach(::watch)
        }
        private val inputs: List<T> get() = watches.map(Cell<T>::value)
        private val callbacks = mutableSetOf<Callback<T>>()
        override var value: T = compute(inputs)
            set(value) {
                if (field != value) {
                    field = value
                    callbacks.forEach { it(value) }
                    notifyWatchers()
                }
            }
            get() {
                value = compute(inputs)
                return field
            }
        fun addCallback(callback: Callback<T>) = object : Subscription {
            init {
                callbacks.add(callback)
            }
            override fun cancel() = callbacks.remove(callback)
        }
    }
}

