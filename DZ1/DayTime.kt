import java.time.LocalDate
import java.time.LocalTime

class DayTime(private val year: Int, private val month: Int,
              private val day: Int, private val hour: Int, private val minute: Int) {

    companion object {
        fun now(): DayTime {
            val localDate: LocalDate = LocalDate.now()
            val localTime: LocalTime = LocalTime.now()
            return DayTime(localDate.year, localDate.monthValue, localDate.dayOfMonth, localTime.hour,
                localTime.minute)
        }
    }

    fun totalDays(): Int = year * 366 + month * 12 + day

    fun totalMins(): Int = totalDays() * 24 * 60 + hour * 60 + minute

    fun isBefore(date2: DayTime): Boolean = totalMins() < date2.totalMins()

    override fun toString(): String {
        return "$hour:$minute $day.$month.$year"
    }
}