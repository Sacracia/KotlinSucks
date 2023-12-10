
class ScreenManager(val db: DataBase) {
    fun regScreen() {
        while (true) {
            println("\n\n")
            print("1.Авторизация\n2.Регистрация\n3.Выход\n>>")
            val input = readln().toInt()
            when {
                input == 1 -> {
                    print("Логин: ")
                    val log = readln()
                    print("Пароль: ")
                    val pas = readln()
                    if (db.allowAccess(log, pas)) {
                        break
                    }
                    println("Неверный логин или пароль")
                }
                input == 2 -> {
                    print("Логин: ")
                    val log = readln()
                    print("Пароль: ")
                    val pas = readln()
                    db.addAccess(log, pas)
                    println("Регистрация завершена")
                }
                input > 2 -> return
            }
        }
        mainScreen()
    }

    fun mainScreen() {
        while (true) {
            println("\n\n")
            print("Управление:\n1.Фильмами\n2.Сеансами\n3.Выход\n>>")
            val input = readln().toInt()
            if (input == 1) {
                moviesScreen()
            } else if (input == 2) {
                sessionsScreen()
            } else {
                return
            }
        }
    }

    fun moviesScreen() {
        while (true) {
            println("\n\n")
            println("Фильмы\nСписок фильмов:")
            if (db.movies.size == 0)
                println(" ->Фильмов Нет")
            else {
                for ((i, movie) in db.movies.withIndex()) {
                    println(" ${i+1}->$movie")
                }
            }
            println("1) Добавить фильм")
            if (db.movies.size > 0)
                println("2) Удалить фильм\n3) Изменить название")
            print("4) Назад\n>>")
            val input = readln().toInt()
            when {
                input == 1 -> {
                    print("Название: ")
                    db.movies.add(Movie(readln()))
                }
                input == 2 -> {
                    print("Номер: ")
                    db.movies.removeAt(readln().toInt() - 1)
                }
                input == 3 -> {
                    print("Номер: ")
                    val num = readln().toInt() - 1
                    print("Новое название: ")
                    db.movies[num].title = readln()
                }
                input > 3 -> return
            }
        }
    }

    fun sessionsScreen() {
        while (true) {
            println("\n\n")
            println("Сеансы\nСписок сеансов:")
            if (db.sessions.size == 0)
                println(" ->Сеансов нет")
            else {
                for ((i, session) in db.sessions.withIndex())
                    println(" ${i+1}->$session")
                println("1) Изменить время\n2) Продать билет\n3) Вернуть билет\n4) Показать свободные места\n5) Отметить посетителей\n6) Вывести занятые места")
            }
            if (db.movies.size > 0) {
                println("7) Добавить сеанс ")
            }
            print("8) Назад\n>> ")
            val input = readln().toInt()
            when {
                input == 1 -> {
                    print("Номер сеанса: ")
                    val k = readln().toInt()
                    print("Дата показа\nГод: ")
                    val y = readln().toInt()
                    print("Месяц: ")
                    val m = readln().toInt()
                    print("День: ")
                    val d = readln().toInt()
                    print("Час: ")
                    val h = readln().toInt()
                    print("Минуты: ")
                    val min = readln().toInt()
                    db.sessions[k-1].time = DayTime(y, m, d, h, min)
                }
                input == 2 -> sellTicketScreen()
                input == 3 -> refundTicket()
                input == 4 -> {
                    print("Номер сеанса: ")
                    db.sessions[readln().toInt() - 1].printPlaces()
                }
                input == 5 -> {
                    print("Номер сеанса: ")
                    val num = readln().toInt()
                    print("Номера мест(11-99) через пробел: ")
                    db.sessions[num-1].markPlaces(readln())
                }
                input == 6 -> {
                    print("Номер сеанса: ")
                    db.sessions[readln().toInt() - 1].printMarkedPlaces()
                }
                input == 7 -> addSessionScreen()
                input > 7 -> return;
            }
        }
    }

    fun addSessionScreen() {
        println("\n\n")
        println("Фильмы\nСписок фильмов:")
        if (db.movies.size == 0)
            println(" ->Фильмов Нет")
        else {
            for ((i, movie) in db.movies.withIndex()) {
                println(" ${i+1}->$movie")
            }
        }
        print("Номер фильма: ")
        val i = readln().toInt()
        print("Дата показа\nГод: ")
        val y = readln().toInt()
        print("Месяц: ")
        val m = readln().toInt()
        print("День: ")
        val d = readln().toInt()
        print("Час: ")
        val h = readln().toInt()
        print("Минуты: ")
        val min = readln().toInt()
        db.sessions.add(Session(db.movies[i-1], DayTime(y, m, d, h, min)))
    }

    fun refundTicket() {
        print("Номер сеанса: ")
        val session = db.sessions[readln().toInt() - 1]
        print("Фамилия: ")
        val surname = readln()
        print("Имя: ")
        val name = readln()
        val user = db.users.find { it->(it.name == name) and (it.surname == surname) }
        if (user != null) {
            session.refund(user)
        } else {
            println("$surname $name не владеет местом")
        }
    }

    fun sellTicketScreen() {
        print("Фамилия: ")
        val surname:String = readln()
        print("Имя: ")
        val name:String = readln()
        print("Номер сеанса: ")
        val sessionNum:Int = readln().toInt()
        print("Место (11-99): ")
        val place:Int = readln().toInt()
        var user: User? = db.users.find { it->(it.name == name) and (it.surname == surname) }
        if (user == null) {
            user = User(name, surname)
            db.users.add(user)
        }
        db.sessions[sessionNum - 1].buyPlace(place, user)
    }
}