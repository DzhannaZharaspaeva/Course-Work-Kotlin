package component

import data.ColorScheme
import data.State
import data.colorschemeList
import hoc.withDisplayName
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLBodyElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.redux.rConnect
import redux.ChangeColorScheme
import redux.RAction
import redux.WrapperAction
import kotlin.browser.document

interface ColorSchemeListProps : RProps {
    var color_schemes: Array<ColorScheme>
    var selected_color_scheme: ColorScheme
    var onClick: (Event) -> Unit

}

val fcolorSchemeListContainer = functionalComponent<ColorSchemeListProps> {
    span(
    ) {
        attrs.onClickFunction = it.onClick
    }
}

/*fun RBuilder.colorSchemeList(
        color_scheme: ColorScheme,
        cssClass: String,
        onClick: (Event) -> Unit
) = child(
        withDisplayName("ColorSchemes", fcolorSchemeListContainer)
) {
    attrs.selected_color_scheme = color_scheme
    attrs.color_schemes = colorschemeList()
    attrs.onClick = onClick
}*/

interface colorSchemeStateProps : RProps {
    var color_scheme: ColorScheme
    var color_schemes: Array<ColorScheme>
}

interface colorSchemeDispatchProps : RProps {
    var save: (ColorScheme) -> Unit
}

interface colorSchemeListProps : RProps {
    var color_schemes: Array<ColorScheme>
    var color_scheme: ColorScheme
    var save: (ColorScheme) -> Unit

}

fun fcolorSchemeList(
        //rObj: RBuilder.(ColorScheme, String, (Event) -> Unit) -> ReactElement
) =
        functionalComponent<colorSchemeListProps> { props ->
            val root = document.getElementById("root") as HTMLDivElement
            val body = root.parentElement as HTMLBodyElement
            //body.bgColor = "white"
            h2 { +"Цветовая схема результатов контрольных недель" }
            select {
                props.color_schemes.mapIndexed { index, colorscheme ->
                    option {
                        attrs.value = "$index"
                        attrs.label = colorscheme.toString()
                        val i1 = colorscheme.toString()
                        val i2 =  props.color_scheme.toString()
                        if(colorscheme.toString() == props.color_scheme.toString())
                            attrs.selected = true
                    }
                }

                attrs.onChangeFunction = {
                    val i = it.target as HTMLSelectElement
                    props.save(colorschemeList()[i.value.toInt()])
                }
            }
        }

val colorSchemeListContainer =
        rConnect<
                State,
                RAction,
                WrapperAction,
                colorSchemeStateProps,
                colorSchemeStateProps,
                colorSchemeDispatchProps,
                colorSchemeListProps
                >(
                { state, _ ->
                    color_scheme = state.color_scheme
                    color_schemes = colorschemeList()
                },
                { dispatch, ownProps ->
                    save = {
                        dispatch(ChangeColorScheme(it))
                    }
                }
        )(
                withDisplayName(
                        "ControlDateList",
                        fcolorSchemeList()

                ).unsafeCast<RClass<colorSchemeListProps>>()
        )