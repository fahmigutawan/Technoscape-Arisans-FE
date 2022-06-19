package com.fahgutawan.arisans.view

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.coroutineScope
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.BaseNavRoute
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(scope:CoroutineScope,firstLayerNavController: NavController, baseNavController: NavController) {

    if(!myViewModel.isProfileSelected.value){
        myViewModel.resetBotMenuSelectState()
        myViewModel.isProfileSelected.value = true
    }

    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height((scaledHeight + 16).dp))
        ProfileContent(scope, firstLayerNavController)
    }

    ProfileTopBar(baseNavController)
}

@Composable
fun ProfileContent(scope:CoroutineScope,firstLayerNavController: NavController) {
    val width = LocalConfiguration.current.screenWidthDp
    ///Log.e("LINK",myViewModel.linkFoto.value )
    if (!myViewModel.linkFoto.value.equals("...")) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .size((width / 4).dp)
                    .clip(RoundedCornerShape(CornerSize(14.dp))),
                model = myViewModel.linkFoto.value,
                contentDescription = "PP",
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.size((width / 4).dp),
                model = R.drawable.ic_change_profile_background,
                contentDescription = "PP",
                contentScale = ContentScale.Crop
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        textAlign = TextAlign.Start,
        text = "Nama Pengguna",
        color = Color.Black,
        style = Typography.body2
    )
    OutlinedTextField(
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        value = myViewModel.namaLengkap.value,
        onValueChange = {
            myViewModel.namaLengkap.value
        },
        shape = RoundedCornerShape(CornerSize(14.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeDark,
            unfocusedBorderColor = GrayMid,
            textColor = Color.Black,
            disabledTextColor = Color.Black,
            backgroundColor = GrayLight,
            placeholderColor = GrayMid
        )
    )

    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        textAlign = TextAlign.Start,
        text = "Nomor Telepon",
        color = Color.Black,
        style = Typography.body2
    )
    OutlinedTextField(
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        value = myViewModel.noTelp.value,
        onValueChange = {
            myViewModel.noTelp.value

        },
        shape = RoundedCornerShape(CornerSize(14.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeDark,
            unfocusedBorderColor = GrayMid,
            textColor = Color.Black,
            disabledTextColor = Color.Black,
            backgroundColor = GrayLight,
            placeholderColor = GrayMid
        )
    )

    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        textAlign = TextAlign.Start,
        text = "Nomor KTP",
        color = Color.Black,
        style = Typography.body2
    )
    OutlinedTextField(
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        value = myViewModel.noKtp.value,
        onValueChange = {
            myViewModel.noKtp.value

        },
        shape = RoundedCornerShape(CornerSize(14.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = OrangeDark,
            unfocusedBorderColor = GrayMid,
            textColor = Color.Black,
            disabledTextColor = Color.Black,
            backgroundColor = GrayLight,
            placeholderColor = GrayMid
        )
    )

    //BTNKeluar
    val context = LocalContext.current.applicationContext
    Spacer(modifier = Modifier.height(32.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            myViewModel.isLoading.value = true
            scope.launch {
                delay(1000)
                context.myDataStore.edit {
                    it[stringPreferencesKey("token")] = "NULL"
                }
                myViewModel.userToken.value = ""

                firstLayerNavController.navigate(FirstNavRoute.LoginScr.route){
                    popUpTo(FirstNavRoute.BaseScr.route){
                        inclusive = true
                    }
                }
                myViewModel.showSnackbar("Berhasil Keluar")
                myViewModel.isLoading.value = false
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
        shape = RoundedCornerShape(CornerSize(14.dp)),
        contentPadding = PaddingValues(all = 14.dp)
    ) {
        Text(text = "KELUAR", textAlign = TextAlign.Center, color = White)
    }
}

@Composable
fun ProfileTopBar(baseNavController: NavController) {
    MyTopBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    baseNavController.popBackStack(route = BaseNavRoute.ProfilePage.route, inclusive = true)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_registernext_back),
                    contentDescription = "Back",
                    tint = GrayDark
                )
            }

            Text(
                text = "Profil",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}