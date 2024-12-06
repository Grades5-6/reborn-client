package com.example.client.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.client.AppViewModels
import com.example.client.screen.JobMainScreen
import com.example.client.screen.JobOnBoardingScreen
import com.example.client.screen.LoginScreen
import com.example.client.screen.MainScreen
import com.example.client.screen.MainOnboardingScreen
import com.example.client.screen.MyPageScreen
import com.example.client.screen.mypage.EditInterestedFieldScreen
import com.example.client.screen.mypage.EditProfileScreen
import com.example.client.screen.mypage.EditRegionScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModels: AppViewModels
) {
    NavHost(
        navController = navController,
        //todo: 커밋하기 전에 여기 바꾸기
        startDestination = NavRoutes.Login.route
    ) {
        composable(NavRoutes.Login.route) {
            LoginScreen(
                myPageViewModel = viewModels.myPageViewModel,
                navController = navController)
        }

        composable(NavRoutes.Main.route) {
            MainScreen(
                navController = navController
            )
        }

        composable(NavRoutes.MainOnboarding.route) {
            MainOnboardingScreen(
                mainOnBoardingViewModel = viewModels.mainOnBoardingViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.JobMain.route) {
            JobMainScreen(
                jobPostViewModel = viewModels.jobPostViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.MyPage.route) {
            MyPageScreen(
                sharedCertificationViewModel = viewModels.sharedCertificationViewModel,
                myPageViewModel = viewModels.myPageViewModel,
                navController = navController)
        }

        composable(NavRoutes.JobOnboarding.route){
            JobOnBoardingScreen(
                sharedViewModel = viewModels.sharedCertificationViewModel,
                viewModel = viewModels.jobOnBoardingViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.MyPageInterest.route) {
            EditInterestedFieldScreen(
                editInterestedViewModel = viewModels.editInterestedViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.MyPageRegion.route) {
            EditRegionScreen(
                editRegionViewModel = viewModels.editRegionViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.MyPageProfile.route) {
            EditProfileScreen(
                editProfileViewModel = viewModels.editProfileViewModel,
                navController = navController
            )
        }

    }
}