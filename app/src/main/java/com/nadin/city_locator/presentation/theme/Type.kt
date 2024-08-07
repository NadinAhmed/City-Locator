package com.nadin.city_locator.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nadin.CityLocator.R

val OpenSansFontFamily = FontFamily(
    Font(R.font.opensans_bold, weight = FontWeight.W700),
    Font(R.font.opensans_semibold, weight = FontWeight.W600),
    Font(R.font.opensans_regular, weight = FontWeight.W400),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.W400,
        color = Color(0xFF1D1B20),
    ),

    //title text
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.8.sp,
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.W700,
        color = Primary,
    ),

    //subtitle text
    titleSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        fontFamily = OpenSansFontFamily,
        color = OnPrimary,
    ),

    //text on button
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.W600,
        color = Color.White,
    ),

    //text in search
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = OpenSansFontFamily,
        fontWeight = FontWeight.W400,
        color = OnPrimary,
        letterSpacing = 0.5.sp,
    ),
)
