package com.ilhomsoliev.myfinances.shared.model.ui

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.ilhomsoliev.myfinances.R

data class IconUi(
    val id: Int,
    val image: Int,
    val color1: Int,
    val color2: Int? = null,
    val color3: Int? = null,
) {
    fun getListOfColors(): List<Int> {
        var colors = mutableListOf(color1)
        color2?.let {
            colors.add(it)
        }
        color3?.let {
            colors.add(it)
        }
        if (colors.size < 2) colors = (colors + colors) as MutableList<Int>
        return colors
    }

    @Stable
    fun getColorsGraphic(): List<Color> {
        return getListOfColors().map { Color(value = it.toULong() shl 32) }
    }
}

fun getIconById(id: Int) = getIcons().first { it.id == id }

fun getIcons() = listOf(
    IconUi(
        id = 1,
        image = R.drawable.icon_education,
        color1 = Color(0xFFFFF282).toArgb(),
        color2 = Color(0xFFFC7E24).toArgb(),
    ),
    IconUi(
        id = 2,
        image = R.drawable.icon_transport,
        color1 = Color(0xFFE9FFAC).toArgb(),
        color2 = Color(0xFF6B8FE9).toArgb(),
    ),
    IconUi(
        id = 3,
        image = R.drawable.icon_products,
        color1 = Color(0xFF80E0FF).toArgb(),
        color2 = Color(0xFF2F5DFD).toArgb(),
    ),
    IconUi(
        id = 4,
        image = R.drawable.icon_clothes,
        color1 = Color(0xFFFFF282).toArgb(),
        color2 = Color(0xFFFC7E24).toArgb(),
    ),
    IconUi(
        id = 5,
        image = R.drawable.icon_subscription,
        color1 = Color(0xFF9B9CF8).toArgb(),
    ),
    IconUi(
        id = 6,
        image = R.drawable.icon_charity,
        color1 = Color(0xFF5278C7).toArgb(),
        color2 = Color(0xFF233F78).toArgb(),
    ),
    IconUi(
        id = 7,
        image = R.drawable.icon_food,
        color1 = Color(0xFFE9FFAC).toArgb(),
        color2 = Color(0xFF6B8FE9).toArgb(),
    ),
    IconUi(
        id = 8,
        image = R.drawable.icon_vocation,
        color1 = Color(0xFF5278C7).toArgb(),
        color2 = Color(0xFF233F78).toArgb(),
    ),
    IconUi(
        id = 9,
        image = R.drawable.icon_renovation,
        color1 = Color(0xFFFFF282).toArgb(),
        color2 = Color(0xFFFC7E24).toArgb(),
    ),
    IconUi(
        id = 10,
        image = R.drawable.icon_beauty,
        color1 = Color(0xFF9B9CF8).toArgb(),
    ),
    IconUi(
        id = 11,
        image = R.drawable.icon_other,
        color1 = Color(0xFFE9FFAC).toArgb(),
        color2 = Color(0xFF6B8FE9).toArgb(),
    ),
)