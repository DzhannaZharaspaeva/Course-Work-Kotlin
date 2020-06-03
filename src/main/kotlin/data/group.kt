package data

data class Group(
    val name: String,
    val id: Int
) {
    override fun toString(): String = name
}

fun groupList() = arrayOf(
    Group("28з", 1),
    Group("46ж",2),
    Group("19а",3)
)