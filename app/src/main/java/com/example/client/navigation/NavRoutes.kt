package com.example.client.navigation

sealed class NavRoutes(val route: String) {
    object Main : NavRoutes("Main")
    object Login : NavRoutes("Login")
    object MainOnboarding : NavRoutes("MainOnboarding")
    object JobMain : NavRoutes("JobMain")
    object MyPage : NavRoutes("MyPage")
    object MyPageInterest : NavRoutes("MyPageInterest")
    object MyPageRegion : NavRoutes("MyPageRegion")
    object MyPageProfile : NavRoutes("MyPageProfile")
    object MyPageLicense : NavRoutes("MyPageLicense")
    object JobOnboarding : NavRoutes("JobOnboarding")
    object Community : NavRoutes("Community")
}