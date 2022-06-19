package com.fahgutawan.arisans.util

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import coil.compose.AsyncImage
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.interfaces.LoginInterface
import com.fahgutawan.arisans.model.LoginPost
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.GrayDark
import com.tahutelor.arisans.ui.theme.GreenDark
import com.tahutelor.arisans.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun MySuccessTransfer() {
    Surface(color = White, shape = RoundedCornerShape(CornerSize(14.dp))) {
        val width = LocalConfiguration.current.screenWidthDp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = R.drawable.ic_berhasil_transfer,
                contentDescription = "Transfer Succeeded",
                modifier = Modifier.height((width / 3).dp)
            )

            Text(
                text = "Yeaayy!!! Pembayaran Berhasil Dilakukan",
                style = Typography.h2,
                color = GrayDark
            )

            //BTN Masuk
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = GreenDark),
                shape = RoundedCornerShape(CornerSize(14.dp)),
                contentPadding = PaddingValues(all = 14.dp)
            ) {
                Text(text = "KEMBALI", textAlign = TextAlign.Center)
            }
        }
    }
}