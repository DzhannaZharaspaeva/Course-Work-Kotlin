package component

import data.*
import react.*
import react.dom.*
import react.router.dom.*
import kotlin.js.Date

interface AppProps_m : RProps {
    var groups: Array<Group>
    var students: ArrayList<Student>
    var ratings: Array<Rating>
    var results: Array<Result>
    var color_scheme: ColorScheme
    var control_date: Array<Date>
}

fun fApp_m() =
        functionalComponent<AppProps_m> { props ->
            header {
                h1 { +"App" }
                nav {
                    ul {
                        li { navLink("/date_edit") { +"Настройка дат контрольных недель" } }
                        li { navLink("/results") { +"Результаты контрольных недель" } }
                        li { navLink("/color_scheme_edit") { +"Настройка цветовой схемы результатов контрольных недель" } }
                    }
                }
            }

            switch {
                route("/date_edit",
                        exact = true,
                        render = { controlDateListContainer { } }
                )
                route("/results",
                        exact = true,
                        render = { resultsListContainer {} }
                )
                route("/color_scheme_edit",
                        exact = true,
                        render = { colorSchemeListContainer {} }
                )
            }
        }

