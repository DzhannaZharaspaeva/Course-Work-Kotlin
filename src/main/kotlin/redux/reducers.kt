package redux

import data.*

fun rootReducer_m(state: State, action: RAction): State{
    when (action) {
        is ChangeColorScheme -> {
            state.color_scheme = action.newColorScheme
        }
        is ChangeControlDate -> {
            state.control_date[action.id] = action.newControlDate
        }
    }
    return state
}

