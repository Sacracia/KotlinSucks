fun replaceElements(array: List<String>): List<String> {
    val res = array.toTypedArray()
    for (i in res.indices) {
        if (res.indexOf(array[i]) != i) {
            res[i] = "blahblah"
        }
    }
    return res.toList()
}

fun uniqueWords(text: String): Int {
    val splitted = text.split(' ')
    var res:Int = 0
    for (i in splitted) {
        if (splitted.count{s -> s == i} == 1)
            ++res
    }
    return res
}

fun main() {
    val text = "a a b c d b e a f e k z x k"
    println(uniqueWords(text))

    println(replaceElements(text.split(' ')).joinToString())
}