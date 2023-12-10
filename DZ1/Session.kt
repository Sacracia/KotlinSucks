class Session(val movie: Movie, var time: DayTime) {
    private val places: IntArray = IntArray(100) {0}
    private val markedPlaces: BooleanArray = BooleanArray(100) {false}

    fun markPlaces(places: String) {
        val intarr = places.split(' ').map{it.toInt()}
        for (i in intarr) {
            markedPlaces[i] = true
        }
    }

    fun printMarkedPlaces() {
        println("  1  2  3  4  5  6  7  8  9")
        for (i in 1..9) {
            print(i)
            for (j in 1..9) {
                if (markedPlaces[i * 10 + j ])
                    print(" + ")
                else
                    print(" - ")
            }
            println()
        }
    }

    fun buyPlace(num: Int, user: User) {
        if (places[num] > 0) {
            println("Место $num занято")
            return
        }
        places[num] = user.hashCode()
        println("Место ${num+1} куплено $user")
    }

    fun printPlaces() {
        for (i in 1..9) {
            for (j in 1..9) {
                if (places[i * 10 + j] == 0)
                    print(i * 10 + j)
                else
                    print("--")
                print(' ')
            }
            println()
        }
    }

    fun refund(user: User) {
        if (time.isBefore(DayTime.now())) {
            println("Сеанс уже начался! Деньги не вернуть")
            return
        }
        val i = places.indexOf(user.hashCode())
        if (i < 0)
            println("$user не владеет местом")
        else {
            places[i] = 0
            println("$user вернули деньги за место $i")
            return
        }
    }

    fun changeTime(newtime: DayTime) {
        time = newtime
    }

    override fun toString(): String {
        return "$movie $time"
    }
}