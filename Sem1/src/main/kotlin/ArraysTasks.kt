
fun makeArray(): Array<Int> {
    print("N: ")
    val n: Int = readln().toInt()
    println("Elements in column: ")
    val arr : Array<Int> = Array<Int>(n) {0}
    for (i in 0..<n) {
        arr[i] = readln().toInt()
    }
    println(arr.joinToString())
    return arr
}

fun sortArrayDesc(arr: Array<Int>) {
    var arr2 = arr.toIntArray()
    arr2 = arr2.sortedArrayDescending()
    for (i in arr.indices) {
        arr[i] = arr2[i]
    }
    println(arr.joinToString())
}

fun main() {
    val arr = makeArray()
    sortArrayDesc(arr)
}