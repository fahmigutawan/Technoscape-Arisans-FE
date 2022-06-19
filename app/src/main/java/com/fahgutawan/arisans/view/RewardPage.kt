package com.fahgutawan.arisans.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*

@Composable
fun RewardPage() {
    LazyColumn() {
        item {
            RewardContent()
        }
        items(5) {
            Spacer(modifier = Modifier.height(16.dp))
            MyVoucher(name = "OVO")
        }
    }

    RewardTopBar()
}

@Composable
fun RewardContent() {
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val scaledHeight = height / 10
    var scrollState = rememberScrollState()

    if(!myViewModel.isRewardSelected.value){
        myViewModel.resetBotMenuSelectState()
        myViewModel.isRewardSelected.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        //Spacer to prevent some compose rendered behind top bar
        Spacer(modifier = Modifier.height((scaledHeight + 16).dp))

        //TOP BANNER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((height / 4).dp)
                .clip(RoundedCornerShape(CornerSize(14.dp)))
                .background(brush = Brush.horizontalGradient(listOf(GrayMid, GreenMid)))
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height / 4).dp)
                    .clip(RoundedCornerShape(CornerSize(14.dp))),
                model = R.drawable.ic_reward_corak,
                contentDescription = "CORAK",
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height / 4).dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AsyncImage(
                    modifier = Modifier.width((width / 3).dp),
                    model = R.drawable.ic_reward_pointing,
                    contentDescription = "Pointing"
                )

                Column(
                    modifier = Modifier.height((height / 4).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Total Poin", style = Typography.h2, color = White)
                    Text(
                        text = "Rp ${myViewModel.rewardNominal.value}",
                        style = Typography.h2,
                        color = OrangeDark,
                        fontSize = 24.sp
                    )
                }
            }
        }

        //Voucher stuffs
        Text(text = "Pilihan Voucher")
    }
}

@Composable
fun RewardTopBar() {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tukar Poin",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}

@Composable
fun MyVoucher(name: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        AsyncImage(model = R.drawable.ic_dummy_voucher, contentDescription = "VOUCHER")
        Text(text = name, style = Typography.h2, fontSize = 42.sp, color = Color.Black)
    }
}