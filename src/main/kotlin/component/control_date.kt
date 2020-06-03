package component

import data.State
import hoc.withDisplayName
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.redux.rConnect
import redux.*
import kotlin.browser.document
import kotlin.js.Date


interface ControlDateListProps : RProps{
    var control_dates: Array<Date>
    var onClick: (Event) -> Unit
}

val fcontrolDateListContainer = functionalComponent<ControlDateListProps> {
    span(
    ) {
        attrs.onClickFunction = it.onClick
    }
}

interface controlDateStateProps : RProps {
    var dates: Array<Date>
}

interface controlDateDispatchProps : RProps {
    var save: (Pair<Int, Date>) -> Unit
}

interface controlDateListProps : RProps {
    var dates: Array<Date>
    var save: (Pair<Int, Date>) -> Unit
}

fun fcontrolDateList(
        //rObj: RBuilder.(Array<Date>, String, (Event) -> Unit) -> ReactElement
) =
        functionalComponent<controlDateListProps> { props ->
            val root = document.getElementById("root") as HTMLDivElement
            val body = root.parentElement as HTMLBodyElement
            body.bgColor = "white"
            h2 { +"Даты контрольных недель" }
            table {
                props.dates.mapIndexed { index, date ->
                    tr {
                        td {
                            val ind = index + 1
                            + "Неделя $ind"
                        }
                        td {
                            input {
                                attrs.id = "datefirst${index}"
                                attrs.set("type", "date")
                                val iso = date.toISOString()
                                attrs.defaultValue = iso.substring(startIndex = 0, endIndex = 10)
                                attrs.onChangeFunction = {
                                    val datesecond = document
                                            .getElementById("datesecond${index}")
                                            as HTMLInputElement
                                    val datefirst = it.currentTarget as HTMLInputElement
                                    val end_control_week = Date(Date(datefirst.value).getTime()
                                            + 7*86400000)
                                    val iso = end_control_week.toISOString()
                                    datesecond.value = iso.substring(startIndex = 0, endIndex = 10)
                                }
                            }
                        }
                        td {
                            input {
                                attrs.id = "datesecond${index}"
                                attrs.set("type", "date")
                                val end_control_week = Date(date.getTime() + 7*86400000)
                                val iso = end_control_week.toISOString()
                                attrs.defaultValue = iso.substring(startIndex = 0, endIndex = 10)
                                attrs.readonly = true
                            }
                        }
                        button {
                            +"Сохранить"
                            attrs.onClickFunction = {
                                val inputElement = document
                                        .getElementById("datefirst${index}")
                                        as HTMLInputElement
                                props.save(Pair(index, Date(inputElement.value)))
                                //window.history.back()
                            }
                        }
                    }
                }
            }
        }

val controlDateListContainer =
        rConnect<
                State,
                RAction,
                WrapperAction,
                controlDateStateProps,
                controlDateStateProps,
                controlDateDispatchProps,
                controlDateListProps
                >(
                { state, _ ->
                    dates = state.control_date
                },
                { dispatch, ownProps ->
                    save = {
                        dispatch(ChangeControlDate(it.first, it.second))
                    }
                }
        )(
                withDisplayName(
                        "ControlDateList",
                        fcontrolDateList()

                ).unsafeCast<RClass<controlDateListProps>>()
        )
