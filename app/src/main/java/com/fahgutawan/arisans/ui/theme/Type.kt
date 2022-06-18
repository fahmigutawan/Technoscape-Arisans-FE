package com.fahgutawan.arisans.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fahgutawan.arisans.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_reguler)),
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_reguler)),
        fontSize = 12.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 14.sp
    )

)