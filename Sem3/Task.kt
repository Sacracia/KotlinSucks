import kotlin.random.Random

open class Pet() {
    val name: String = ""

    override fun toString(): String {
        return ""
    }
}

class Cat() : Pet() {
    override fun toString(): String {
        return "MEOW"
    }
}

class Dog() : Pet() {
    override fun toString(): String {
        return "BARK"
    }
}

class PetContainer<T : Pet>() {
    private val pets: MutableList<T> = mutableListOf()

    fun putIntoContainer(smb: T) {
        pets.add(smb)
    }

    fun hearVoice(): T {
        val chosen: T = pets[Random.nextInt(pets.size)]
        println("A pet’s voice came through the container: $chosen. Сan you guess who this is?")
        return chosen
    }
}

fun main() {
    val container: PetContainer<Pet> = PetContainer()
    for (i in 1..Random.nextInt(11)) {
        container.putIntoContainer(Cat()) //Cats
    }
    for (i in 1..Random.nextInt(11)) {
        container.putIntoContainer(Dog()) //Cats
    }
    val rnd: Pet = container.hearVoice()
    print("Чей был голос? 0 - Собака, 1 - Кошка: ")
    val answer: String = readln()
    if (((answer == "0") and (rnd is Dog)) or ((answer == "1") and (rnd is Cat))) {
        println("Поздравляю, вы угадали!")
    }
    else {
        println("Вы не узнали своего питомца!")
    }
}