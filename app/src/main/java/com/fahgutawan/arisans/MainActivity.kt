package com.fahgutawan.arisans

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import com.fahgutawan.arisans.view.LandingArisan
import com.fahgutawan.arisans.view.RegisterNextPage
import com.fahgutawan.arisans.viewmodel.MyViewModel
import com.fahgutawan.arisans.viewmodel.MyViewModelFactory

//Instanciating viewmodel
lateinit var myViewModel: MyViewModel
lateinit var pickImageLauncher: ActivityResultLauncher<String>

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Component start here
        val repo = ArisansApiRepo()
        val factory = MyViewModelFactory(repo)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)

        //Instantiate image picker
        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if(uri!=null) myViewModel.registerNextImagePicked.value = uri
            }

        setContent {
            ArisansTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState = scaffoldState) {
//                        FirstLayerNav(
//                            scope = rememberCoroutineScope(),
//                            scaffoldState = scaffoldState
//                        )
                        LandingArisan()
                    }

                }
            }
        }
    }
}
