
open class Point(val X: Double, val Y: Double) {

    var area: Double = 0.0

    fun display() {
        println("S = $area, X = $X, Y = $Y")
    }
}

class Square(X: Double, Y: Double, var side: Double) : Point(X, Y) {
    init {
        area = side * side
    }
}

class Circle(X: Double, Y: Double, val r: Double) : Point(X, Y) {
    init {
        area = Math.PI * r * r
    }
}

fun main() {
    val p = Point(34.0, 56.0)
    val c = Circle(46.0, 34.0, 7.0)
    val s = Square(46.0, 34.0, 8.0)
    p.display()
    c.display()
    s.display()
}