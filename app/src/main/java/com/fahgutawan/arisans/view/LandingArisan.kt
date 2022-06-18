package com.fahgutawan.arisans.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*

@Composable
fun LandingArisan() {
    LandingContent()
    LandingTopBar()
}

@Composable
fun LandingContent() {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 10

    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        var scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = scrollState, orientation = Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //To prevent some compose rendered behind our top bar
            Spacer(modifier = Modifier.height((scaledHeight + 16).dp))

            //Card and Term
            val width = LocalConfiguration.current.screenWidthDp
            val scaledCardHeight = width / 16 * 9
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(scaledCardHeight.dp)
                        .clip(RoundedCornerShape(CornerSize(14.dp)))
                        .background(color = GreenLight)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true, color = Color.Black),
                            onClick = {
                                //IMPLEMENT HERE
                            }
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .clip(
                                    RoundedCornerShape(CornerSize(8.dp))
                                ),
                            color = OrangeDark,
                            elevation = 2.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 8.dp)
                                    .padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Dapatkan Total Get Rp ${myViewModel.landArisanTotalGet.value}",
                                    color = GrayDark,
                                    style = Typography.body2,
                                )

                                //Status locked
                                var lockIcon = remember {
                                    mutableStateOf(R.drawable.ic_home_unlock)
                                }
                                if (myViewModel.landArisanIsLocked.value) {
                                    lockIcon.value = R.drawable.ic_home_lock
                                } else {
                                    lockIcon.value = R.drawable.ic_home_unlock
                                }
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(CornerSize(12.dp)))
                                        .background(color = OrangeDark),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = lockIcon.value),
                                        contentDescription = "Lock Status",
                                        tint = GrayDark
                                    )
                                }
                            }
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp),
                            color = GrayDark,
                            thickness = 2.dp
                        )
                        Text(
                            text = myViewModel.landArisanNama.value,
                            color = GrayDark,
                            style = Typography.h2,
                        )
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Jumlah orang
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_home_people),
                                    contentDescription = "People",
                                    tint = GreenDark
                                )
                                Text(
                                    text = myViewModel.landTotalOrang.value,
                                    style = Typography.h2,
                                    color = GreenDark,
                                    fontSize = 12.sp
                                )
                            }

                            //Jumlah uang
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_money),
                                    contentDescription = "People",
                                    tint = GreenDark
                                )
                                Text(
                                    text = myViewModel.landArisanUang.value,
                                    style = Typography.h2,
                                    color = GreenDark,
                                    fontSize = 12.sp
                                )
                            }

                            //Kode
                            Text(
                                text = myViewModel.landArisanKode.value,
                                style = Typography.h2,
                                color = GreenDark,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                ) {
                    Text(text = "Persyaratan", style = Typography.body2, color = GrayDark)
                    Text(
                        text = myViewModel.landArisanSyarat.value,
                        style = Typography.subtitle1,
                        color = GrayDark
                    )
                }

            }

            //Checklist and Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Checkbox(
                        checked = myViewModel.landArisanIsAcceptTerm.value,
                        onCheckedChange = {
                            myViewModel.landArisanIsAcceptTerm.value = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = OrangeDark,
                            uncheckedColor = GrayMid,
                            checkmarkColor = GrayDark
                        )
                    )
                    Text(
                        text = "Saya menyetujui persyaratan dan ketentuan",
                        style = Typography.subtitle1,
                        color = GrayDark
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    contentPadding = PaddingValues(all = 14.dp)
                ) {
                    Text(text = "IKUTI ARISAN", textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun LandingTopBar() {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_registernext_back),
                    contentDescription = "Back",
                    tint = GrayDark
                )
            }

            Text(
                text = "Syarat dan Ketentuan",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}