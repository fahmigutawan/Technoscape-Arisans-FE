package com.tahutelor.arisans.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myDataStore
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.fahgutawan.arisans.snackbarHostState
import com.fahgutawan.arisans.ui.theme.Typography
import com.tahutelor.arisans.ui.theme.GreenDark
import com.tahutelor.arisans.ui.theme.GreenMid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@Composable
fun SplashScreen(scope: CoroutineScope, navController: NavController) {
    val context = LocalContext.current.applicationContext

    val tokenFlow: Flow<String> = context.myDataStore.data
        .map { preferences ->
            preferences[stringPreferencesKey("token")] ?: "NULL"
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(GreenDark, GreenMid))),
        contentAlignment = Alignment.Center
    ) {
        var height = LocalConfiguration.current.screenHeightDp

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier.size((height / 8).dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "My Logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Arisans",
                style = Typography.h2,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        }

        scope.launch {
            delay(1500)
            val token = tokenFlow.first()

            if(token != "NULL"){
                navController.navigate(FirstNavRoute.BaseScr.route) {
                    popUpTo(FirstNavRoute.SplashScr.route) {
                        inclusive = true
                    }
                }
            }else{
                navController.navigate(FirstNavRoute.LoginScr.route) {
                    popUpTo(FirstNavRoute.SplashScr.route) {
                        inclusive = true
                    }
                }
            }
            scope.cancel()
        }
    }
}