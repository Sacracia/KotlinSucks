import kotlin.math.sqrt

fun askForInput() {
    println("Введите ФИО: ")
    val input = readln().split(' ')
    println("Фамилия: " + input[0])
    println("Имя: " + input[1])
    println("Отчество: " + input[2])
}

fun quadraticRoots(a: Double, b: Double, c: Double): Pair<Double, Double> {
    val d = b * b - 4 * a * c
    val x1 = (-b + sqrt(d)) / (2*a)
    val x2 = (-b - sqrt(d)) / (2*a)
    return x1 to x2
}

fun printSameDigitNumbers() {
    for (i in 100..999) {
        if ((i % 10 == i / 100) and (i / 10 % 10 == i % 10)) {
            println(i)
        }
    }
}

fun main() {
        askForInput()

        val roots = quadraticRoots(-5.0, 4.0, 3.0)
        println("X1 = ${roots.first} ; X2 =${roots.second}")

        printSameDigitNumbers()
}