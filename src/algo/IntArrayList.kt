package algo

class IntArrayList(capacity: Int = 2) {
    private var a = IntArray(capacity)

    var size: Int = 0
        private set

    fun add(x: Int) {
        if (size >= a.size) a = a.copyOf(size * 2)
        a[size++] = x
    }

    operator fun iterator(): IntIterator = object : IntIterator() {
        var i = 0
        override fun hasNext(): Boolean = i < size
        override fun nextInt(): Int = a[i++]
    }
}