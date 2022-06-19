package com.fahgutawan.arisans.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.BaseNavRoute
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*

@Composable
fun HistoryPage(baseNavController: NavController) {
    if(!myViewModel.isRiwayatSelected.value){
        myViewModel.resetBotMenuSelectState()
        myViewModel.isRiwayatSelected.value = true
    }

    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val scaledHeight = height / 10
    
    LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){
        item { 
            Spacer(modifier = Modifier.height((scaledHeight + 16).dp))
        }
        items(2){
            Spacer(modifier = Modifier.height(16.dp))
            HistoryCardActive()
        }
        items(5){
            Spacer(modifier = Modifier.height(16.dp))
            HistoryCardInActive()
        }
    }
    HistoryTopBar(baseNavController)
}

@Composable
fun HistoryCardActive() {
    val width = LocalConfiguration.current.screenWidthDp
    val scaledHeight = (width / 16 * 5).dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(scaledHeight),
        elevation = 5.dp,
        shape = RoundedCornerShape(CornerSize(14.dp)),
        color = Color.Unspecified
    ) {
        //Green Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(scaledHeight)
                .clip(RoundedCornerShape(CornerSize(14.dp)))
                .background(color = GreenLight)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = {

                    }
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(scaledHeight)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //FIRST ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Rp 140000",
                        style = Typography.h2,
                        color = GreenDark,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "#123456",
                        style = Typography.h2,
                        color = GreenDark,
                        fontSize = 12.sp
                    )
                }

                //SECOND ROW
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "UserName",
                    style = Typography.h2,
                    color = GrayDark,
                    textAlign = TextAlign.Start
                )

                //THIRD ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                            text = "5/10",
                            style = Typography.h2,
                            color = GreenDark,
                            fontSize = 12.sp
                        )
                    }

                    //Status locked
                    var lockIcon = remember {
                        mutableStateOf(R.drawable.ic_home_unlock)
                    }
                    if (myViewModel.homeIsArisanLocked.value) {
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
        }
    }
}

@Composable
fun HistoryCardInActive() {
    val width = LocalConfiguration.current.screenWidthDp
    val scaledHeight = (width / 16 * 5).dp

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(scaledHeight),
        elevation = 5.dp,
        shape = RoundedCornerShape(CornerSize(14.dp)),
        color = Color.Unspecified
    ) {
        //Green Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(scaledHeight)
                .clip(RoundedCornerShape(CornerSize(14.dp)))
                .background(color = Color(0xFFC0C0C0))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.Black),
                    onClick = {

                    }
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(scaledHeight)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //FIRST ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Rp 140000",
                        style = Typography.h2,
                        color = GreenDark,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "#123456",
                        style = Typography.h2,
                        color = GreenDark,
                        fontSize = 12.sp
                    )
                }

                //SECOND ROW
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "UserName",
                    style = Typography.h2,
                    color = GrayDark,
                    textAlign = TextAlign.Start
                )

                //THIRD ROW
                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                            text = "5/10",
                            style = Typography.h2,
                            color = GreenDark,
                            fontSize = 12.sp
                        )
                    }

                    //Status locked
                    var lockIcon = remember {
                        mutableStateOf(R.drawable.ic_home_unlock)
                    }
                    if (myViewModel.homeIsArisanLocked.value) {
                        lockIcon.value = R.drawable.ic_home_lock
                    } else {
                        lockIcon.value = R.drawable.ic_home_unlock
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(CornerSize(12.dp)))
                            .background(color = GrayMid),
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
        }
    }
}

@Composable
fun HistoryTopBar(baseNavController: NavController) {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    baseNavController.popBackStack(BaseNavRoute.HistoryPage.route, inclusive = true)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_registernext_back),
                    contentDescription = "Back",
                    tint = GrayDark
                )
            }

            Text(
                text = "Riwayat Arisan",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}
