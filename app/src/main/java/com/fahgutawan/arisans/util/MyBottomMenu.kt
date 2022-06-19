package com.fahgutawan.arisans.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fahgutawan.arisans.R
import com.fahgutawan.arisans.myViewModel
import com.fahgutawan.arisans.navroute.BaseNavRoute
import com.fahgutawan.arisans.navroute.FirstNavRoute
import com.tahutelor.arisans.ui.theme.GrayLight
import kotlinx.coroutines.CoroutineScope

@Composable
fun MyBottomMenu(
    height: Dp,
    scope: CoroutineScope,
    firstNavController: NavController,
    navController: NavController
) {
    //Default Sizes
    val icHomeSize = remember {
        mutableStateOf(36.dp)
    }
    val icHistorySize = remember {
        mutableStateOf(24.dp)
    }
    val icAddSize = remember {
        mutableStateOf(52.dp)
    }
    val icRewardSize = remember {
        mutableStateOf(24.dp)
    }
    val icProfileSize = remember {
        mutableStateOf(24.dp)
    }

    //AnimateAsDP
    val icAnimatedHome = animateDpAsState(targetValue = icHomeSize.value)
    val icAnimatedHistory = animateDpAsState(targetValue = icHistorySize.value)
    val icAnimatedAdd = animateDpAsState(targetValue = icAddSize.value)
    val icAnimatedReward = animateDpAsState(targetValue = icRewardSize.value)
    val icAnimatedProfile = animateDpAsState(targetValue = icProfileSize.value)

    //Condition handler
    //Home
    if (myViewModel.isHomeSelected.value) {
        icHomeSize.value = 36.dp
        myViewModel.icHomeIcon.value = R.drawable.ic_botmenu_home_selected
    } else {
        icHomeSize.value = 28.dp
        myViewModel.icHomeIcon.value = R.drawable.ic_botmenu_home_unselected
    }

    //History
    //Icon history tidak sama dengan yg lain, karena terlalu besar sedikit
    if (myViewModel.isRiwayatSelected.value) {
        icHistorySize.value = 32.dp
        myViewModel.icHistoryIcon.value = R.drawable.ic_botmenu_history_selected
    } else {
        icHistorySize.value = 24.dp
        myViewModel.icHistoryIcon.value = R.drawable.ic_botmenu_history_unselected
    }

    //Add
//    if (myViewModel.isAddArisanSelected.value) {
//        icAddSize.value = 52.dp
//        myViewModel.icAddIcon.value = R.drawable.ic_botmenu_add_selected
//    } else {
//        icAddSize.value = 30.dp
//        myViewModel.icAddIcon.value = R.drawable.ic_botmenu_add_unselected
//    }

    //Reward
    if (myViewModel.isRewardSelected.value) {
        icRewardSize.value = 32.dp
        myViewModel.icRewardIcon.value = R.drawable.ic_botmenu_reward_selected
    } else {
        icRewardSize.value = 24.dp
        myViewModel.icRewardIcon.value = R.drawable.ic_botmenu_reward_unselected
    }

    //Profile
    if (myViewModel.isProfileSelected.value) {
        icProfileSize.value = 32.dp
        myViewModel.icProfileIcon.value = R.drawable.ic_botmenu_profile_selected
    } else {
        icProfileSize.value = 24.dp
        myViewModel.icProfileIcon.value = R.drawable.ic_botmenu_profile_unselected
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        color = GrayLight
    ) {
        Row(
            modifier = Modifier.padding(all = 14.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = {
                navController.navigate(BaseNavRoute.HomePage.route){
                    popUpTo(BaseNavRoute.HomePage.route){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    modifier = Modifier.size(icAnimatedHome.value),
                    painter = painterResource(id = myViewModel.icHomeIcon.value),
                    contentDescription = "Home",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = {
                navController.navigate(BaseNavRoute.HistoryPage.route){
                    popUpTo(BaseNavRoute.HistoryPage.route){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    modifier = Modifier.size(icAnimatedHistory.value),
                    painter = painterResource(id = myViewModel.icHistoryIcon.value),
                    contentDescription = "History",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = {
                firstNavController.navigate(FirstNavRoute.AddArisanScr.route)
            }) {
                Icon(
                    modifier = Modifier.size(icAnimatedAdd.value),
                    painter = painterResource(id = myViewModel.icAddIcon.value),
                    contentDescription = "Add",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = {
                navController.navigate(BaseNavRoute.RewardPage.route){
                    popUpTo(BaseNavRoute.RewardPage.route){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    modifier = Modifier.size(icAnimatedReward.value),
                    painter = painterResource(id = myViewModel.icRewardIcon.value),
                    contentDescription = "Reward",
                    tint = Color.Unspecified
                )
            }
            IconButton(onClick = {
                navController.navigate(BaseNavRoute.ProfilePage.route){
                    popUpTo(BaseNavRoute.ProfilePage.route){
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    modifier = Modifier.size(icAnimatedProfile.value),
                    painter = painterResource(id = myViewModel.icProfileIcon.value),
                    contentDescription = "Profile",
                    tint = Color.Unspecified
                )
            }
        }
    }
}