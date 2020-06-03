package container

import react.*
import redux.*
import react.redux.rConnect
import component.*
import data.*
import hoc.withDisplayName

//ПРИМЕР ЧТО ОСТАВЛЯТЬ

/*interface StudentEditOwnProps : RProps {
    var student: Pair<Int, Student>
}

val studentEditContainer =
    rConnect<
            RAction,
            WrapperAction,
            StudentEditOwnProps,
            StudentEditProps
            >(
        { dispatch, ownProps ->
            onClick = {
                dispatch(ChangeStudent(ownProps.student.first, it))
            }
        }
    )(
        withDisplayName(
            "StudentEdit",
            fStudentEdit
        ).unsafeCast<RClass<StudentEditProps>>()
    )
*/