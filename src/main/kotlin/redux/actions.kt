package redux

import data.*
import kotlin.js.Date


class ChangeColorScheme(val newColorScheme: ColorScheme) : RAction

class ChangeControlDate(val id: Int, val newControlDate: Date) : RAction

