package com.fahgutawan.arisans

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fahgutawan.arisans.interfaces.UserDataInterface
import com.fahgutawan.arisans.model.UserData
import com.fahgutawan.arisans.navigation.FirstLayerNav
import com.fahgutawan.arisans.repo.ArisansApiRepo
import com.fahgutawan.arisans.ui.theme.ArisansTheme
import com.fahgutawan.arisans.view.*
import com.fahgutawan.arisans.viewmodel.MyViewModel
import com.fahgutawan.arisans.viewmodel.MyViewModelFactory
import com.tahutelor.arisans.ui.theme.GrayDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

//Instanciating viewmodel
val Context.myDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
lateinit var myViewModel: MyViewModel
lateinit var pickImageLauncher: ActivityResultLauncher<String>
lateinit var pickBuktiLauncher: ActivityResultLauncher<String>
lateinit var snackbarHostState: SnackbarHostState
lateinit var coroutineScope: CoroutineScope

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewModel Stuff
        val repo = ArisansApiRepo()
        val factory = MyViewModelFactory(repo)
        myViewModel = ViewModelProvider(this, factory).get(MyViewModel::class.java)

        //Instantiate image picker
        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) myViewModel.registerNextImagePicked.value = uri
            }
        pickBuktiLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) myViewModel.addBuktiPembayaran.value = uri
            }

        //LoadToken
        val tokenFLow: Flow<String> = myDataStore.data.map {
            it[stringPreferencesKey("token")] ?: "NULL"
        }

        //Component start here
        setContent {
            ArisansTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    snackbarHostState = scaffoldState.snackbarHostState
                    coroutineScope = rememberCoroutineScope()

                    coroutineScope.launch {
                        myViewModel.userToken.value = tokenFLow.first()
                    }

                    Scaffold(scaffoldState = scaffoldState) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            FirstLayerNav(
                                scope = coroutineScope,
                                scaffoldState = scaffoldState
                            )
                        }
                    }
                }

                if (myViewModel.isLoading.value) {
                    Surface(
                        Modifier
                            .fillMaxSize()
                            .alpha(0.5f), color = GrayDark) {
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}