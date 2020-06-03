package data

import kotlin.js.Date

typealias GroupState = Array<Group>

typealias StudentState =  ArrayList<Student>

typealias RatingState = Array<Rating>

typealias ResultState = Array<Result>

typealias ControlDateState = Array<Date>

typealias WorkTypeState = Array<WorkType>

class State(
        val groups: GroupState,
        val students: StudentState,
        val ratings: RatingState,
        val results: ResultState,
        var color_scheme: ColorScheme,
        var work_types: WorkTypeState,
        val control_date: ControlDateState
)

fun initialState() =
        State(
                groupList(),
                studentsList(),
                ratingList(),
                resultList(),
                colorschemeList().first(),
                worktypesList(),
                arrayOf(
                        Date("2020-01-01"),
                        Date("2020-03-03"),
                        Date("2020-05-26")
                )
        )
