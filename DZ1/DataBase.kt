import kotlinx.serialization.Serializable
import java.security.MessageDigest

@Serializable
class DataBase{
    val sessions: MutableList<Session> = mutableListOf()
    val movies: MutableList<Movie> = mutableListOf()
    val users: MutableList<User> = mutableListOf()
    private val logins: MutableList<String> = mutableListOf()
    private val passwords: MutableList<String> = mutableListOf()

    fun hash(s:String): String {
        val bytes = s.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun addAccess(login:String, password:String) {
        logins.add(login)
        passwords.add(hash(password))
    }

    fun allowAccess(login: String, p:String) : Boolean {
        val h = hash(p)
        for (i in 0..<logins.size) {
            if ((logins[i] == login) and (passwords[i] == h))
                return true
        }
        return false
    }
}