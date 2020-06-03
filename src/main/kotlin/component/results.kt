package component

import data.*
import hoc.withDisplayName
import kotlinext.js.js
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.dom.span
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction
import kotlin.browser.document
import kotlin.js.Date

interface ResultListProps : RProps {
    val groups: Array<Group>
    val students: ArrayList<Student>
    val ratings: Array<Rating>
    val results: Array<Result>
    var color_scheme: ColorScheme
    val control_dates: Array<Date>

    var onClick: (Event) -> Unit
}

val fresultListContainer = functionalComponent<ResultListProps> {
    span(
    ) {
        attrs.onClickFunction = it.onClick
    }
}

fun RBuilder.resultList(
        dates: Array<Date>,
        cssClass: String,
        onClick: (Event) -> Unit
) = child(
        withDisplayName("Results", fresultListContainer)
) {
    //attrs.control_dates = dates
    //todo добавить присвоения
    attrs.onClick = onClick
}

interface resultStateProps : RProps {
    var groups: Array<Group>
    var students: ArrayList<Student>
    var ratings: Array<Rating>
    var results: Array<Result>
    var color_scheme: ColorScheme
    var control_dates: Array<Date>
    var work_types: Array<WorkType>
}

interface resultDispatchProps : RProps {
    var click: (Int) -> Unit
}

interface resultListProps : RProps {
    var groups: Array<Group>
    var students: ArrayList<Student>
    var ratings: Array<Rating>
    var results: Array<Result>
    var color_scheme: ColorScheme
    var control_dates: Array<Date>
    var work_types: Array<WorkType>
    var click: (Int) -> Unit
}

fun fresultList() =
        functionalComponent<resultListProps> { props ->
            val root = document.getElementById("root") as HTMLDivElement
            val body = root.parentElement as HTMLBodyElement
            body.bgColor = props.color_scheme.background
                h2 {
                    +"Даты контрольных недель"
                }
                props.groups.map { curr_group ->
                    h3 {
                        +"Группа ${curr_group.name}"
                        attrs["align"] = "center"
                        attrs.style  = js{
                            color = props.color_scheme.font_color
                        }
                    }
                    props.control_dates.mapIndexed { index_week, control_week ->
                        h4 {
                            val ind = index_week + 1
                            +"Неделя $ind"
                            attrs.style  = js{
                                color = props.color_scheme.font_color
                            }
                        }
                        val li = get_desciplines(props, control_week, curr_group)
                        if (li.isEmpty()) {
                            h5 {
                                +"Нет сведений."
                                attrs.style  = js{
                                    color = props.color_scheme.font_color
                                }
                            }
                        } else {
                            table {
                                //attrs["style"] = "color:#0000ff"
                                attrs.style  = js{
                                    color = props.color_scheme.font_color
                                }
                                //attrs["style"] = "color:$props.color_scheme.font_color"
                                thead {
                                    //ttrs["align"] = "center"
                                    tr {
                                        td { +"Студент" }
                                        li.map {
                                            td {
                                                +it.name
                                            }
                                        }
                                    }
                                }
                                val studentsOfGroup = props.students.filter { it.id_group == curr_group.id }
                                tbody {
                                    attrs["color"] = "red"
                                    studentsOfGroup.forEach { student ->
                                        tr {
                                            td {
                                                +"${student.surname} ${student.firstname} ${student.patronymic}"
                                                val rating = props.ratings.filter { it.id_student == student.id }
                                                if (!rating.isEmpty() && rating.first().id_color!=1)
                                                    attrs["bgcolor"] = student_rating_color(rating.first().id_color)
                                            }
                                            li.forEach { workType ->
                                                td {
                                                    var res = props.results.filter {
                                                        it.id_student == student.id
                                                                && it.id_worktype == workType.id
                                                    }
                                                    if (res.isEmpty())
                                                        +"н/я"
                                                    else
                                                        +res.first().point.toString()
                                                }
                                            }
                                        }
                                    }
                                }
                                //td{
                                //}
                            }

                        }
                    }
                }
            }

fun get_desciplines(resultList: resultListProps, start_control_week: Date, curr_group: Group)
        : List<WorkType>{
    var ret = emptyList<WorkType>()

    val end_control_week = Date(start_control_week.getTime() + 7*86400000)

    val studentsOfGroup = resultList.students.filter { it.id_group==curr_group.id }
    val workTypes = resultList.work_types.filter { it.date.getTime() >= start_control_week.getTime()
            && it.date.getTime() <= end_control_week.getTime() }

    studentsOfGroup.forEach { student ->
        val st_res = resultList.results.filter { it.id_student == student.id }
        st_res.forEach { res ->
            val wt = workTypes.find { it.id == res.id_worktype }
            if (wt != null)
                ret += wt
        }
    }

    return ret.distinct()
}

val resultsListContainer =
        rConnect<
                State,
                RAction,
                WrapperAction,
                resultStateProps,
                resultStateProps,
                resultDispatchProps,
                resultListProps
                >({ state, _ ->
                    groups = state.groups
                    students = state.students
                    ratings = state.ratings
                    results = state.results
                    color_scheme = state.color_scheme
                    control_dates = state.control_date
                    work_types = state.work_types
                },
                { dispatch, ownProps ->
                    click = {}
                }
        )(
                withDisplayName(
                        "Result",
                        fresultList()

                ).unsafeCast<RClass<resultListProps>>()
        )




