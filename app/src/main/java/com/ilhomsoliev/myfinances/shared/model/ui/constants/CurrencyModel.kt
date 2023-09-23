package com.ilhomsoliev.myfinances.shared.model.ui.constants

//@Entity(tableName = "goal")
data class CurrencyModel(
//    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val singleName: String,
    val pluralName: String,
    val symbol: Char,
    //  val icon: Int,

)

fun getCurrencies(): List<CurrencyModel> = listOf(
    CurrencyModel(
        id = 1,
        singleName = "Рубль",
        pluralName = "Рубли",
        symbol = '₽',
        //  icon = R.drawable,,
    ),
    CurrencyModel(
        id = 2,
        singleName = "Доллар",
        pluralName = "Доллары",
        symbol = '$',
        //  icon = R.drawable,,
    ),
    CurrencyModel(
        id = 3,
        singleName = "Евро",
        pluralName = "Евро",
        symbol = '€',
        //  icon = R.drawable,,
    ),
)

fun getCurrency(id: Int): CurrencyModel = getCurrencies().first { it.id == id }