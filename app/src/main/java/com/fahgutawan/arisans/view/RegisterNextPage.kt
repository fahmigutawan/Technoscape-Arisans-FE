package com.fahgutawan.arisans.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.interfaces.RegisterInterface
import com.fahgutawan.arisans.model.RegisterPost
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.pickImageLauncher
import com.fahgutawan.arisans.ui.theme.Typography
import com.fahgutawan.arisans.util.MyTopBar
import com.tahutelor.arisans.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterNextPage(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val height = LocalConfiguration.current.screenHeightDp
    val scaledHeight = height / 10
    val context = LocalContext.current.applicationContext

    //We make this page as stack. So we should code from the lowest layer first
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //To prevent some compose rendered behind our top bar
            Spacer(modifier = Modifier.height((scaledHeight).dp))

            //Our contents
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val image = remember {
                        mutableStateOf(R.drawable.ic_registernext_addphoto)
                    }
                    val scaledPickImage = height / 4
                    if (myViewModel.registerNextImagePicked.value == null) {
                        IconButton(
                            modifier = Modifier.size((scaledPickImage).dp),
                            onClick = { pickImageLauncher.launch("image/*") }) {
                            Icon(
                                modifier = Modifier.size((scaledPickImage).dp),
                                painter = rememberAsyncImagePainter(model = image.value),
                                contentDescription = "Image",
                                tint = Color.Black

                            )
                        }
                    } else {
                        AsyncImage(
                            modifier = Modifier
                                .size((scaledPickImage).dp)
                                .clip(RoundedCornerShape(CornerSize(16.dp)))
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        bounded = true,
                                        color = Color.Black
                                    ),
                                    onClick = {
                                        pickImageLauncher.launch("image/*")
                                    }
                                ),
                            model = myViewModel.registerNextImagePicked.value,
                            contentDescription = "PP",
                            contentScale = ContentScale.Crop
                        )
                    }

                    //Nama pengguna
                    Spacer(modifier = Modifier.height(16.dp))
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
                        modifier = Modifier.fillMaxWidth(),
                        value = myViewModel.registerNextUsername.value,
                        onValueChange = {
                            myViewModel.registerNextUsername.value = it
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
                                text = "Masukkan nama pengguna baru",
                                style = Typography.subtitle2
                            )
                        }
                    )

                    //Tanggal lahir
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        textAlign = TextAlign.Start,
                        text = "Tanggal Lahir",
                        color = Color.Black,
                        style = Typography.body2
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = myViewModel.registerNextTTL.value,
                        onValueChange = {
                            myViewModel.registerNextTTL.value = it
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
                                text = "Masukkan tanggal lahir anda",
                                style = Typography.subtitle2
                            )
                        }
                    )

                    //NIK
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        textAlign = TextAlign.Start,
                        text = "NIK",
                        color = Color.Black,
                        style = Typography.body2
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = myViewModel.registerNextNIK.value,
                        onValueChange = {
                            myViewModel.registerNextNIK.value = it
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
                                text = "Masukkan NIK anda",
                                style = Typography.subtitle2
                            )
                        }
                    )
                }

                //BTN Masuk
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    onClick = {
                        if (!myViewModel.registerNextNIK.value.isEmpty()
                            && !myViewModel.registerNextNIK.value.isEmpty()
                            && !myViewModel.registerNextNIK.value.isEmpty()
                        ) {
                            myViewModel.isLoading.value = true
                            myViewModel.postRegister(
                                RegisterPost(
                                    myViewModel.registerNextUsername.value,
                                    myViewModel.registerTelp.value,
                                    myViewModel.registerNextTTL.value,
                                    myViewModel.registerPass.value,
                                    myViewModel.registerNextNIK.value
                                ),
                                object : RegisterInterface {
                                    override suspend fun onTokenRetrieved(token: String) {
                                        context.myDataStore.edit {
                                            it[stringPreferencesKey("token")] = token
                                        }


                                        delay(2000)
                                        navController.navigate(FirstNavRoute.BaseScr.route) {
                                            popUpTo(FirstNavRoute.RegisterNextScr.route) {
                                                inclusive = true
                                            }
                                        }

                                        //remove data from viewmodel
                                        myViewModel.registerNextNIK.value = ""
                                        myViewModel.registerNextTTL.value = ""
                                        myViewModel.registerNextUsername.value = ""
                                        myViewModel.registerTelp.value = ""
                                        myViewModel.registerPass.value = ""

                                        myViewModel.showSnackbar("Registrasi berhasil, Selamat datang di Arisans")
                                        myViewModel.isLoading.value = false
                                    }

                                    override suspend fun onFailed() {
                                        myViewModel.showSnackbar("Registrasi gagal, coba lagi nanti!")
                                        myViewModel.isLoading.value = false
                                    }
                                }
                            )
                        }else{
                            myViewModel.showSnackbar("Masukkan semua data dengan benar!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    contentPadding = PaddingValues(all = 14.dp)
                ) {
                    Text(text = "VERIFIKASI", textAlign = TextAlign.Center)
                }
            }
        }
    }

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
                text = "Lengkapi Profil",
                style = Typography.h1,
                textAlign = TextAlign.Center,
                color = GrayDark
            )
        }
    }
}