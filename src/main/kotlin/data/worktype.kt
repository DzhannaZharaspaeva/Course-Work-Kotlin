package data

import kotlin.js.Date

data class WorkType(
        val name: String,
        val id: Int,
        val date: Date,
        val maxPoint: Int
) {
    override fun toString(): String = name
}

fun worktypesList() = arrayOf(
        WorkType("Физика",1, Date("2020-01-01"),100),
        WorkType("Численные методы",2,Date("2020-01-02"),100),
        WorkType("Физика",3,Date("2020-03-03"),100),
        WorkType("Численные методы",4,Date("2020-03-04"),100),
        WorkType("Физика",5,Date("2020-05-28"),100),
        WorkType("Численные методы",6,Date("2020-05-26"),100)
)