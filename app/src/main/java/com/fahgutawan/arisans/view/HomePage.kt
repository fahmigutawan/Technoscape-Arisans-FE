package com.fahgutawan.arisans.view

import android.widget.Space
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.interfaces.LoginInterface
import com.fahgutawan.arisans.interfaces.UserDataInterface
import com.fahgutawan.arisans.model.HomeArisanBody
import com.fahgutawan.arisans.model.LoginPost
import com.fahgutawan.arisans.model.UserData
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tahutelor.arisans.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun HomePage(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    firstLayerNavController: NavController,
    baseNavController: NavController
) {
    if(!myViewModel.isHomeSelected.value){
        myViewModel.resetBotMenuSelectState()
        myViewModel.isHomeSelected.value = true
    }
    if (!myViewModel.homeIsBannerLoaded.value) myViewModel.loadListOfBanner()
    val lazyListState = rememberLazyListState()

    //Loading User data
    if(myViewModel.userToken.value!=""){
        myViewModel.getUserData(object : UserDataInterface {
            override fun onDataRetrieved(user: UserData) {
                myViewModel.namaLengkap.value = user.body.NamaLengkap
                myViewModel.noTelp.value = user.body.NomorTelepon
                myViewModel.noKtp.value = user.body.NomorKTP
                myViewModel.linkFoto.value = user.body.LinkFoto
            }
        })
    }

    //Loading Arisan list
    myViewModel.getListArisan()

    LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp), state = lazyListState) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            ProfileSection()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            SearchSection()
        }
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {
            BannerSection()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Pilih Kelompok Arisan",
                style = Typography.h2,
                color = GrayDark
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(myViewModel.listArisan.size) { index ->
            RandomArisanSection(firstLayerNavController, myViewModel.listArisan.get(index))
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileSection() {
    val height = LocalConfiguration.current.screenHeightDp
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "HALO", style = Typography.body2, color = GreenDark)
            Text(text = myViewModel.namaLengkap.value, style = Typography.h1, color = GreenDark)
        }

        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(CornerSize(16.dp)))
                .size((height / 10).dp),
            painter = rememberAsyncImagePainter(model = myViewModel.linkFoto.value),
            contentDescription = "Profile Picture",
            tint = Color.Unspecified
        )
    }
}

@Composable
fun SearchSection() {
    val width = LocalConfiguration.current.screenWidthDp
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size((width / 12).dp),
                tint = GreenDark,
                painter = painterResource(id = R.drawable.ic_notif_ring),
                contentDescription = "Notif"
            )
        }

        OutlinedTextField(
            value = myViewModel.homeSearchValue.value,
            onValueChange = {
                myViewModel.homeSearchValue.value = it

            },
            maxLines = 1,
            shape = RoundedCornerShape(CornerSize(14.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = OrangeDark,
                unfocusedBorderColor = GrayMid,
                textColor = Color.Black,
                disabledTextColor = Color.Black,
                backgroundColor = GrayLight,
                placeholderColor = GrayMid
            ),
            placeholder = {
                Text(text = "Cari nama arisan atau id arisan")
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = GrayMid
                )
            }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSection() {
    val pagerState = rememberPagerState()
    val width = LocalConfiguration.current.screenWidthDp
    val scaledHeight = width / 16 * 9

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HorizontalPager(
            count = myViewModel.homeListOfBanner.size,
            state = pagerState
        ) { pageIndex ->
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((scaledHeight).dp)
                    .clip(RoundedCornerShape(CornerSize(14.dp))),
                model = myViewModel.homeListOfBanner.get(pageIndex),
                contentDescription = "MyDummy"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalPagerIndicator(
            activeColor = GrayDark,
            inactiveColor = GrayMid,
            indicatorHeight = 8.dp,
            indicatorWidth = 8.dp,
            indicatorShape = CircleShape,
            pagerState = pagerState
        )
    }
}

@Composable
fun RandomArisanSection(firstLayerNavController: NavController, arisan:HomeArisanBody) {
    val width = LocalConfiguration.current.screenWidthDp
    val scaledAnimatedHeight = remember {
        mutableStateOf((width / 16 * 5).dp)
    }
    val isExpanded = remember {
        mutableStateOf(false)
    }
    val scaledHeight = (width / 16 * 5).dp
    val animatedCardDp = animateDpAsState(targetValue = scaledAnimatedHeight.value)

    if (isExpanded.value)
        scaledAnimatedHeight.value = scaledHeight * 3
    else
        scaledAnimatedHeight.value = scaledHeight

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedCardDp.value),
        elevation = 5.dp,
        shape = RoundedCornerShape(CornerSize(14.dp)),
        color = Color.Unspecified
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedCardDp.value)
                .clip(RoundedCornerShape(CornerSize(14.dp))),
            contentAlignment = Alignment.TopCenter
        ) {
            //White Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animatedCardDp.value),
                color = Color.White,
                shape = RoundedCornerShape(CornerSize(14.dp)),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                        .height(animatedCardDp.value)
                ) {
                    Spacer(modifier = Modifier.height(animatedCardDp.value / 3))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(animatedCardDp.value * 2 / 3),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        //No Telp
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp),
                            textAlign = TextAlign.Start,
                            text = "Password Arisan",
                            color = Color.Black,
                            style = Typography.body2
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            value = myViewModel.loginTelp.value,
                            onValueChange = {
                                myViewModel.loginTelp.value = it

                            },
                            shape = RoundedCornerShape(CornerSize(14.dp)),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = OrangeDark,
                                unfocusedBorderColor = GrayMid,
                                textColor = Color.Black,
                                disabledTextColor = Color.Black,
                                backgroundColor = GrayLight,
                                placeholderColor = GrayMid
                            ),
                            placeholder = {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Start,
                                    text = "Masukkan password arisan privat",
                                    style = Typography.body2
                                )
                            },
                            visualTransformation = PasswordVisualTransformation()
                        )
                        //BTN Masuk
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            onClick = {
                                myViewModel.arisanPicked.value = arisan
                                firstLayerNavController.navigate(FirstNavRoute.LandingArisan.route)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                            shape = RoundedCornerShape(CornerSize(14.dp)),
                            contentPadding = PaddingValues(all = 14.dp)
                        ) {
                            Text(text = "LANJUTKAN", textAlign = TextAlign.Center)
                        }
                    }
                }
            }

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
                            if (!arisan.StatusArisanPublik) {
                                isExpanded.value = !isExpanded.value
                            }else{
                                myViewModel.arisanPicked.value = arisan
                                firstLayerNavController.navigate(FirstNavRoute.LandingArisan.route)
                            }
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
                            text = "Rp ${arisan.Nominal.toString()}",
                            style = Typography.h2,
                            color = GreenDark,
                            fontSize = 12.sp
                        )
                        Text(
                            text = arisan.NanoIdArisan,
                            style = Typography.h2,
                            color = GreenDark,
                            fontSize = 12.sp
                        )
                    }

                    //SECOND ROW
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = arisan.NamaArisan,
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
                                text = "0/${arisan.BanyakOrang}",
                                style = Typography.h2,
                                color = GreenDark,
                                fontSize = 12.sp
                            )
                        }

                        //Status locked
                        var lockIcon = remember {
                            mutableStateOf(R.drawable.ic_home_unlock)
                        }
                        if (!arisan.StatusArisanPublik) {
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
}