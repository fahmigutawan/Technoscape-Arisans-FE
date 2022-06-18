package com.fahgutawan.arisans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahgutawan.arisans.navigation.FirstLayerNav
import com.fahgutawan.arisans.repo.ArisansApiRepo
import com.fahgutawan.arisans.ui.theme.ArisansTheme
import com.fahgutawan.arisans.view.BasePage
import com.fahgutawan.arisans.viewmodel.MyViewModel
import com.fahgutawan.arisans.viewmodel.MyViewModelFactory

//Instanciating viewmodel
lateinit var myViewModel: MyViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Component start here
        val repo = ArisansApiRepo()
        val factory = MyViewModelFactory(repo)

        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)

        setContent {
            ArisansTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
//                    Scaffold(scaffoldState = scaffoldState) {
//                        FirstLayerNav(
//                            scope = rememberCoroutineScope(),
//                            scaffoldState = scaffoldState
//                        )
//                    }
                    BasePage(scope = rememberCoroutineScope(), scaffoldState = scaffoldState)
                }
            }
        }
    }
}
