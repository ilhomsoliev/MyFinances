package com.ilhomsoliev.myfinances.app.theme.shapes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class ExtraShapes(
    val zero: Shape = RectangleShape,
    val smallCardShape: Shape = RectangleShape,
    val largeTopRoundedShape: Shape = RectangleShape,
    val largeBottomRoundedShape: Shape = RectangleShape,
    val mediumTopRoundedShape: Shape = RectangleShape,
    val mediumBottomRoundedShape: Shape = RectangleShape,
    val mediumStartRoundedShape: Shape = RectangleShape,
    val mediumEndRoundedShape: Shape = RectangleShape,
    val extraLargeStartRoundedShape: Shape = RectangleShape,
    val extraLargeEndRoundedShape: Shape = RectangleShape,
    val ultraTopRoundedShape: Shape = RectangleShape,
    val chatRoundedShape: Shape = RectangleShape,
    val ultraRoundedShape: Shape = RectangleShape,
    val bigShapes: Shape = RectangleShape,
    val bigTopShapes: Shape = RectangleShape,
    // Add new form in scheme = RectangleShape
)

@Deprecated(
    "Надо использовать тему",
    ReplaceWith(
        "ThemeExtra.shapes",
        "ru.rikmasters.gilty.presentation.ui.theme.base.ThemeExtra"
    )
)

val DefaultExtraShapes = ExtraShapes(
    largeTopRoundedShape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
    largeBottomRoundedShape = RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp),
    mediumTopRoundedShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
    mediumBottomRoundedShape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
    mediumStartRoundedShape = RoundedCornerShape(bottomStart = 12.dp, topStart = 12.dp),
    mediumEndRoundedShape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp),
    ultraTopRoundedShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    bigShapes = RoundedCornerShape(24.dp),
    bigTopShapes = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ultraRoundedShape = RoundedCornerShape(50.dp),
    extraLargeStartRoundedShape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp),
    extraLargeEndRoundedShape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
    chatRoundedShape = RoundedCornerShape(30.dp),
    zero = RoundedCornerShape(0.dp)
// Add form in scheme
)