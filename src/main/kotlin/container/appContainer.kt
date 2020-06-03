package container

import react.*
import react.redux.rConnect
import component.*
import data.*
import hoc.withDisplayName

val appContainer =
        rConnect<State, RProps, AppProps_m>(
                { state, _ ->
                    groups = state.groups
                    students = state.students
                    ratings = state.ratings
                    results = state.results
                    color_scheme = state.color_scheme
                    control_date = state.control_date
                },
                {
                    pure = false  // side effect of React Route
                }
        )(
                withDisplayName(
                        "MyApp",
                        fApp_m()
                )
                        .unsafeCast<RClass<AppProps_m>>()
        )