class User(val name:String, val surname: String) {

    override fun toString(): String {
        return "$surname $name"
    }
}