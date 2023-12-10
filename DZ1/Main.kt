import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

fun main() {
    val db = DataBase()
    val m = Movie("Ku Fu")
    val s = Session(m, DayTime(2022, 12, 8, 13, 30))
    db.movies.add(m)
    db.sessions.add(s)
    val sm = ScreenManager(db)
    val json = Json.encodeToString(db)
    println(json)
    //sm.regScreen()
}