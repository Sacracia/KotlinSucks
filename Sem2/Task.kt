import kotlin.random.Random
import kotlin.reflect.typeOf

interface Employee {
    var salary: Double
    fun fire()
    fun increasesalary(amount: Double)
}

open class Person(n:String, l:String) {
    protected var name:String = n
    protected var lastname:String = l

    open fun ToString() : String {
        return "$name $lastname"
    }
}

class Professor(n:String, l:String, phone:String, slry:Double) : Person(n, l), Employee {
    private var phoneNumber: String = phone
    private var boss: Stuff? = null
    override var salary: Double = slry;

    override fun fire() {
        println("Преподаватель ${super.ToString()} уволен")
    }

    override fun increasesalary(amount: Double) {
        salary += amount
        println("Зарплата преподавателя ${super.ToString()} увеличена на $amount")
    }

    fun assignBoss(stuff: Stuff) {
        boss = stuff
    }

    fun Output(): String {
        return "Профессор ${super.ToString()}:\n -Телефон $phoneNumber\n -Босс ${boss?.ToString()}\n -Зарплата $salary"
    }
}

class Stuff(n:String, l:String, number: String, sry: Double) : Person(n, l), Employee {
    private var phoneNumber: String = number
    override var salary: Double = sry

    override fun fire() {
        println("Работник $name $lastname уволен")
    }

    override fun increasesalary(amount: Double) {
        salary += amount
        println("Зарплата работника $name $lastname увеличена на $amount")
    }

    fun Output(): String {
        return "Работник ${ToString()}:\n -Телефон $phoneNumber\n -Зарплата $salary"
    }
}

class Student(n:String, l:String) : Person(n,l) {
    private var grades: MutableList<Int>? = null

    fun expell() {
        println("Студент ${ToString()} с оценками ${grades?.joinToString()}")
    }

    fun addGrade(grade: Int) {
        if (grades == null) {
            grades = MutableList(1) {grade}
        }
        else {
            grades?.add(grade)
        }
    }

    fun Output(): String {
        return "Студент ${ToString()}:\n -Оценки ${grades?.joinToString()}"
    }
}

fun main() {
    val array: Array<Person> = arrayOf(Student("Студ1", "С1"),
        Professor("Проф1", "П1", "+7-98-72", 800.0),
        Stuff("Раб1", "Р1", "+7-67-41", 500.0))
    (array[1] as Professor).assignBoss(array[2] as Stuff)
    for (i in 5..10)
        (array[0] as Student).addGrade(i)
    println((array[0] as Student).Output())
    println((array[1] as Professor).Output())
    println((array[2] as Stuff).Output())
    println("\n------------------10-------------------\n\n")
    val n = Random.nextInt(5, 11)
}