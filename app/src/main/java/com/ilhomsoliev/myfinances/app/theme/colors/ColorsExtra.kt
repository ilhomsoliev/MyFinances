@file:Suppress("DEPRECATION")

package com.ilhomsoliev.myfinances.app.theme.colors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.AlmostDark
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.AlmostRed
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Ash
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Black
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.BlackerWhite
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Blue
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Border
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Cyan
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkBlue
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkCyan
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkOrange
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkPurple
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkRed
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkShadow
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.DarkYellow
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.ExtraLightGray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Gray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Lead
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.LightGray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.LightGreen
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.LightPink
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.NickelGray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Orange
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Pinky
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.PreDark
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Purple
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.RatGray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Red
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.RottenPlum
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Silver
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.SuperDark
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.White
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.WhiteShadow
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Yellow
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.Zircon
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.gripGrayDark
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.halfTransparentlyGray
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.meetingCardBackBackgroundNight
import com.ilhomsoliev.myfinances.app.theme.colors.Colors.textField

data class ExtraColors(
    val searchCardBackground: Color = Unspecified,
    val grayButton: Color = Unspecified,
    val elementsBack: Color = Unspecified,
    val chipGray: Color = Unspecified,
    val policyAgreeColor: Color = Unspecified,
    val borderColor: Color = Unspecified,
    val mainTrackCheckBox: Color = Unspecified,
    val secondaryTrackCheckBox: Color = Unspecified,
    val meetingCardBackBackground: Color = Unspecified,
    val navBarActiveBackground: Color = Unspecified,
    val navBarActive: Color = Unspecified,
    val navBarInactive: Color = Unspecified,
    val navBarAddButton: Color = Unspecified,
    val chatBackground: Color = Unspecified,
    val commentBackground: Color = Unspecified,
    val priceTextFieldText: Color = Unspecified,
    val tabActive: Color = Unspecified,
    val tabActiveOnline: Color = Unspecified,
    val tabInactive: Color = Unspecified,
    val chatBack: Color = Unspecified,
    val meetButtonColors: Color = Unspecified,
    val meetCardShadow: Color = Unspecified,
    val meetCloseCircleColor: Color = Unspecified,
    val meetCloseCrossColor: Color = Unspecified,
    val meetCardPlaceHolder: Color = Unspecified,
    val notificationCloud: Color = Unspecified,
    val meetingTransparencyShape: Color = Unspecified,
    val gripColor: Color = Unspecified,
    val miniCategoriesBackground: Color = Unspecified,
    // Categories Colors
    val sport: Color = Unspecified,
    val business: Color = Unspecified,
    val travel: Color = Unspecified,
    val masterClasses: Color = Unspecified,
    val entertainment: Color = Unspecified,
    val erotic: Color = Unspecified,
    val party: Color = Unspecified,
    val art: Color = Unspecified,
    val galleryCircle: Color = Unspecified,
    // Add new color in scheme = Color.Unspecified
    
    
    //Translations Colors (always the same)
    val white: Color = Unspecified,
    val thirdOpaqueGray: Color = Unspecified,
    val bottomSheetGray: Color = Unspecified,
    val blackSeventy: Color = Unspecified,
    val mainCard: Color = Unspecified,
    val mainNightGreen: Color = Unspecified,
    val mainDayGreen: Color = Unspecified,
    val mainNotActiveGreen: Color = Unspecified,
    val textField: Color = Unspecified,
    val preDarkColor: Color = Unspecified,
    val zirkon: Color = Unspecified,
    val messageBar: Color = Unspecified,
    val black: Color = Unspecified
    )

@Deprecated(
    "Надо использовать тему",
    ReplaceWith(
        "ThemeExtra.colors",
        "ru.rikmasters.gilty.presentation.ui.theme.base.ThemeExtra"
    )
)
val LightExtraColors = ExtraColors(
    elementsBack = White,
    chipGray = Gray,
    grayButton = Gray,
    searchCardBackground = LightGray,
    policyAgreeColor = Silver,
    mainTrackCheckBox = Ash,
    secondaryTrackCheckBox = Border,
    borderColor = White,
    meetingCardBackBackground = White,
    navBarActiveBackground = LightPink,
    navBarActive = RottenPlum,
    navBarInactive = Silver,
    navBarAddButton = AlmostDark,
    chatBackground = Pinky,
    commentBackground = RottenPlum,
    priceTextFieldText = AlmostRed,
    tabActive = LightPink,
    tabInactive = LightGray,
    tabActiveOnline = LightGreen,
    chatBack = BlackerWhite,
    meetButtonColors = Gray,
    meetCardShadow = WhiteShadow,
    meetCloseCircleColor = White,
    meetCloseCrossColor = AlmostDark,
    meetCardPlaceHolder = NickelGray,
    notificationCloud = Gray,
    meetingTransparencyShape = halfTransparentlyGray,
    gripColor = Ash,
    miniCategoriesBackground = LightGray,
    galleryCircle = ExtraLightGray,
    
    // Categories Colors
    sport = Orange,
    business = Blue,
    travel = Cyan,
    masterClasses = Yellow,
    entertainment = AlmostRed,
    erotic = Red,
    party = Purple,
    art = Orange,
    // Add colors in scheme from Colors-file
    
    //Translations Colors (always the same)
    white = White,
    thirdOpaqueGray = Colors.thirdOpaqueGray,
    bottomSheetGray = Silver,
    blackSeventy = Colors.blackSeventy,
    mainCard = AlmostDark,
    mainNightGreen = Colors.Green,
    mainDayGreen = Colors.Green,
    mainNotActiveGreen = Colors.DimGrin,
    textField = RottenPlum,
    preDarkColor = PreDark,
    zirkon = Zircon,
    messageBar = textField,
    black = Black
)

@Deprecated(
    "Надо использовать тему",
    ReplaceWith(
        "ThemeExtra.colors",
        "ru.rikmasters.gilty.presentation.ui.theme.base.ThemeExtra"
    )
)
val DarkExtraColors = ExtraColors(
    elementsBack = PreDark,
    chipGray = RatGray,
    grayButton = RatGray,
    searchCardBackground = SuperDark,
    policyAgreeColor = Silver,
    mainTrackCheckBox = Border,
    secondaryTrackCheckBox = Ash,
    borderColor = meetingCardBackBackgroundNight,
    meetingCardBackBackground = meetingCardBackBackgroundNight,
    navBarActiveBackground = LightPink,
    navBarActive = White,
    navBarInactive = Zircon,
    navBarAddButton = White,
    chatBackground = RottenPlum,
    commentBackground = RottenPlum,
    priceTextFieldText = White,
    tabActive = LightPink,
    tabInactive = Black,
    tabActiveOnline = LightGreen,
    chatBack = Black,
    meetButtonColors = Lead,
    meetCardShadow = DarkShadow,
    meetCloseCircleColor = AlmostDark,
    meetCloseCrossColor = RottenPlum,
    meetCardPlaceHolder = Gray,
    notificationCloud = RatGray,
    meetingTransparencyShape = halfTransparentlyGray,
    gripColor = gripGrayDark,
    miniCategoriesBackground = Lead,
    galleryCircle = Black,
    
    // Categories Colors
    sport = DarkOrange,
    business = DarkBlue,
    travel = DarkCyan,
    masterClasses = DarkYellow,
    entertainment = AlmostRed,
    erotic = DarkRed,
    party = DarkPurple,
    art = DarkOrange,
    // Add colors in scheme from Colors-file
    
    
    //Translations Colors (always the same)
    white = White,
    thirdOpaqueGray = Colors.thirdOpaqueGray,
    bottomSheetGray = Silver,
    blackSeventy = Colors.blackSeventy,
    mainCard = AlmostDark,
    mainNightGreen = Colors.Green,
    mainDayGreen = Colors.Green,
    mainNotActiveGreen = Colors.DimGrin,
    textField = RottenPlum,
    preDarkColor = PreDark,
    zirkon = Zircon,
    messageBar = textField,
    black = Black
)
