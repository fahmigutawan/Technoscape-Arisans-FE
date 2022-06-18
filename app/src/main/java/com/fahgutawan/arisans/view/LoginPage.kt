package com.fahgutawan.arisans.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cisu.cisusplash.util.YellowDarker
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.GrayDark
import com.tahutelor.arisans.ui.theme.GrayLight
import com.tahutelor.arisans.ui.theme.GrayMid
import com.tahutelor.arisans.ui.theme.GreenDark
import kotlinx.coroutines.CoroutineScope

@Composable
fun LoginPage(scope: CoroutineScope, navController: NavController,scaffoldState: ScaffoldState) {
    val height = LocalConfiguration.current.screenHeightDp

    Surface(color = White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Image vektor at top
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier.size((height/3).dp),
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
                Text(modifier = Modifier.fillMaxWidth().padding(start = 8.dp), textAlign = TextAlign.Start, text = "Nomor Telepon", color = Black, style = Typography.body2)
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    value = myViewModel.loginTelp.value,
                    onValueChange = {
                        myViewModel.loginTelp.value = it

                    },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = YellowDarker,
                        unfocusedBorderColor = GrayMid,
                        textColor = Black,
                        disabledTextColor = Black,
                        backgroundColor = GrayLight
                    )
                )

                //Spacer
                Spacer(modifier = Modifier.height(14.dp))

                //Password
                Text(modifier = Modifier.fillMaxWidth().padding(start = 8.dp), textAlign = TextAlign.Start,text = "Kata Sandi", color = Black, style = Typography.body2)
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = myViewModel.loginPass.value,
                    onValueChange = {
                        myViewModel.loginPass.value = it

                    },
                    shape = RoundedCornerShape(CornerSize(14.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = YellowDarker,
                        unfocusedBorderColor = GrayMid,
                        textColor = Black,
                        disabledTextColor = Black,
                        backgroundColor = GrayLight
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                //Spacer
                Spacer(modifier = Modifier.height(14.dp))

                //BTN Masuk
                Button(
                    modifier = Modifier.fillMaxWidth().padding(all = 8.dp),
                    onClick = {
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
                    navController.navigate(FirstNavRoute.RegisterScr.route){
                        popUpTo(FirstNavRoute.RegisterScr.route){
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