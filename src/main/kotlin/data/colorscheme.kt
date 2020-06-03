package data

data class ColorScheme (
        val name: String,
        val background: String,
        val font_color: String
){
    override fun toString(): String = name
}

fun colorschemeList()  = arrayOf(
        ColorScheme("Светлая тема", "#FFFFFF","#000000"),
        ColorScheme("Темная тема","#808080","#ffffff"),
        ColorScheme("Синяя тема","#8080ff","#ffffff")
)

fun student_rating_color(index: Int): String {
    when(index){
        0->return "#90EE90"
        1->return "#FFFFFF"
        2->return "#ff9980"
    }
    return "#FFFFFF"
}