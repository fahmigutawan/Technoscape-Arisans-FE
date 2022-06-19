package com.fahgutawan.arisans.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.snackbarHostState
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RegisterPage(
    scope: CoroutineScope,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    Surface(modifier = Modifier.fillMaxSize(), color = White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .padding(top = 32.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            //Title Text
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = "Belum Punya Akun?",
                    style = Typography.h1,
                    fontSize = 28.sp,
                    color = OrangeDark
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = "Buat Akun Terlebih Dahulu!",
                    style = Typography.h1,
                    fontSize = 28.sp,
                    color = GreenDark
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
                    color = Color.Black,
                    style = Typography.body2
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    value = myViewModel.registerTelp.value,
                    onValueChange = {
                        myViewModel.registerTelp.value = it

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
                    color = Color.Black,
                    style = Typography.body2
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = myViewModel.registerPass.value,
                    onValueChange = {
                        myViewModel.registerPass.value = it

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
                    visualTransformation = PasswordVisualTransformation(),
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
            }

            //Bottom items
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Sudah punya akun?", style = Typography.subtitle1, color = GrayMid)
                    TextButton(onClick = {
                        navController.navigate(FirstNavRoute.LoginScr.route) {
                            popUpTo(FirstNavRoute.LoginScr.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Text(text = "Masuk", style = Typography.subtitle2, color = Color.Black)
                    }
                }
                //BTN Masuk
                Button(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    onClick = {

                        if (!myViewModel.registerTelp.value.isEmpty() && !myViewModel.registerPass.value.isEmpty()) {
                            myViewModel.isLoading.value = true
                            navController.navigate(FirstNavRoute.RegisterNextScr.route)
                            myViewModel.isLoading.value = false
                        } else {
                            myViewModel.showSnackbar("Masukkan semua data dengan benar!")
                        }

                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    contentPadding = PaddingValues(all = 14.dp)
                ) {
                    Text(text = "BUAT AKUN", textAlign = TextAlign.Center)
                }
            }
        }
    }
}