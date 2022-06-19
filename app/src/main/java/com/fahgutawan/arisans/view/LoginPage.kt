package com.fahgutawan.arisans.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.interfaces.LoginInterface
import com.fahgutawan.arisans.interfaces.UserDataInterface
import com.fahgutawan.arisans.model.LoginPost
import com.fahgutawan.arisans.model.UserData
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.snackbarHostState
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginPage(scope: CoroutineScope, navController: NavController, scaffoldState: ScaffoldState) {
    val height = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current.applicationContext

    Surface(color = White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Image vektor at top
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.size((height / 3).dp),
                    painter = painterResource(id = R.drawable.ic_login_vector),
                    contentDescription = "My Login"
                )
            }

            //All fields stuff
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //No Telp
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Start,
                    text = "Nomor Telepon",
                    color = Black,
                    style = Typography.body2
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    value = myViewModel.loginTelp.value,
                    onValueChange = {
                        myViewModel.loginTelp.value = it

                    },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = OrangeDark,
                        unfocusedBorderColor = GrayMid,
                        textColor = Black,
                        disabledTextColor = Black,
                        backgroundColor = GrayLight,
                        placeholderColor = GrayMid
                    ),
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            text = "Masukkan nomor telepon anda",
                            style = Typography.body2
                        )
                    }
                )

                //Spacer
                Spacer(modifier = Modifier.height(14.dp))

                //Password
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = "Kata Sandi",
                    color = Black,
                    style = Typography.body2
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = myViewModel.loginPass.value,
                    onValueChange = {
                        myViewModel.loginPass.value = it

                    },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = OrangeDark,
                        unfocusedBorderColor = GrayMid,
                        textColor = Black,
                        disabledTextColor = Black,
                        backgroundColor = GrayLight,
                        placeholderColor = GrayMid
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp),
                            textAlign = TextAlign.Start,
                            text = "Masukkan kata sandi",
                            style = Typography.body2
                        )
                    }
                )

                //Spacer
                Spacer(modifier = Modifier.height(14.dp))

                //BTN Masuk
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onClick = {
                        if (myViewModel.loginTelp.value != ""
                            && myViewModel.loginPass.value != ""
                        ) {
                            myViewModel.isLoading.value = true
                            myViewModel.postLogin(
                                LoginPost(
                                    myViewModel.loginTelp.value.trim(),
                                    myViewModel.loginPass.value.trim()
                                ),
                                object : LoginInterface {
                                    override suspend fun onTokenRetrieved(token: String) {
                                        context.myDataStore.edit {
                                            it[stringPreferencesKey("token")] = token
                                        }

                                        //Navigate to homepage
                                        delay(2000)
                                        navController.navigate(FirstNavRoute.BaseScr.route) {
                                            popUpTo(FirstNavRoute.RegisterNextScr.route) {
                                                inclusive = true
                                            }
                                        }
                                        myViewModel.loginTelp.value = ""
                                        myViewModel.loginPass.value = ""

                                        //Loading User data
                                        myViewModel.userToken.value = token
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
                                        myViewModel.showSnackbar("Login berhasil, Selamat datang di Arisans")
                                        myViewModel.isLoading.value = false
                                    }

                                    override suspend fun onFailed() {
                                        myViewModel.isLoading.value = false
                                        myViewModel.showSnackbar("Login gagal, coba lagi nanti!")
                                    }
                                }
                            )
                        } else {
                            myViewModel.showSnackbar("Masukkan semua data dengan benar!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    contentPadding = PaddingValues(all = 14.dp)
                ) {
                    Text(text = "MASUK", textAlign = TextAlign.Center)
                }

                //BTN Lupa Password
                TextButton(onClick = {
                    //navigate to lupa password
                }) {
                    Text(text = "Lupa kata sandi?", style = Typography.subtitle2, color = Black)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Tidak punya akun?", style = Typography.subtitle1, color = GrayMid)
                TextButton(onClick = {
                    navController.navigate(FirstNavRoute.RegisterScr.route) {
                        popUpTo(FirstNavRoute.RegisterScr.route) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Buat Akun", style = Typography.subtitle2, color = Black)
                }
            }
        }
    }
}