package com.fahgutawan.arisans.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.*

@Composable
fun ArisanPage() {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 3
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Scrollable Section
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height - (height / 14)).dp)
                    .verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((scaledHeight).dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((scaledHeight).dp),
                        model = R.drawable.ic_banner_arisan,
                        contentDescription = "Banner",
                        contentScale = ContentScale.Crop
                    )
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((scaledHeight).dp)
                            .alpha(alpha = 0.5f)
                    ) {}
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((scaledHeight).dp)
                            .padding(all = 16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_registernext_back),
                                    contentDescription = "Arisan name",
                                    tint = White
                                )

                            }
                            Text(
                                text = myViewModel.arisanName.value,
                                style = Typography.h2,
                                color = White
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = myViewModel.arisanId.value,
                                style = Typography.body2,
                                color = White
                            )
                            Text(
                                text = "Pass = ${myViewModel.arisanPass.value}",
                                style = Typography.body2,
                                color = White
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Dana terkumpul minggu ke - 6",
                        style = Typography.body2,
                        color = GrayDark
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Dana",
                            style = Typography.subtitle1,
                            color = GrayDark
                        )
                        Text(
                            text = "Rp 200.000 / Rp 400.000",
                            style = Typography.subtitle2,
                            color = OrangeDark
                        )
                    }
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    textAlign = TextAlign.Start,
                    text = "Anggota Arisan",
                    style = Typography.body2,
                    color = GrayDark
                )
                //ListItem
                for (i in 0..myViewModel.arisanPesertaArisan.size - 1) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        color = GreenLight,
                        shape = RoundedCornerShape(CornerSize(16.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = myViewModel.arisanPesertaArisan.get(i).name,
                                style = Typography.body2,
                                color = GrayDark
                            )
                            if (myViewModel.arisanPesertaArisan.get(i).isPaid) {
                                Text(
                                    text = "Paid",
                                    style = Typography.body2,
                                    color = GreenDark
                                )
                            } else {
                                Text(
                                    text = "Unpaid",
                                    style = Typography.body2,
                                    color = Color.Red
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            //Buttons section
            Row(modifier = Modifier.fillMaxWidth()) {
                val width = LocalConfiguration.current.screenWidthDp

                Button(
                    modifier = Modifier
                        .width((width / 2).dp)
                        .height((height / 14).dp),
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = OrangeDark)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "KOCOK GELAS",
                        color = White
                    )
                }
                Button(
                    modifier = Modifier
                        .width((width / 2).dp)
                        .height((height / 10).dp),
                    shape = RectangleShape,
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "BAYAR",
                        color = White
                    )
                }
            }
        }
    }
}