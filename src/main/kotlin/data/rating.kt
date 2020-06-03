package data

import kotlin.js.Date

data class Rating(
        val id_student: Int,
        val id_color: Int //индекс цвета
)

//так как расчет рейтинга не является частью индивидуального задания,
//список содержит случайные индексы цветов
fun ratingList() = arrayOf(
        Rating(1,0),
        Rating(2,1),
        Rating(3,2),
        Rating(4,0),
        Rating(5,1),
        Rating(6,2),
        Rating(7,0),
        Rating(8,0),
        Rating(9,1),
        Rating(10,2),
        Rating(11,2),
        Rating(12,0),
        Rating(13,0),
        Rating(14,0),
        Rating(15,1),
        Rating(16,0),
        Rating(17,0),
        Rating(18,1),
        Rating(19,0),
        Rating(20,0),
        Rating(21,1),
        Rating(22,0),
        Rating(23,1),
        Rating(24,2),
        Rating(25,0),
        Rating(26,1)
)
