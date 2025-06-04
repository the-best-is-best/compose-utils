package io.github.compose_utils_navigation

import androidx.navigation.NavController

fun NavController.replace(route: String) {
    navigate(route) {
        currentBackStackEntry?.destination?.route?.let {
            popUpTo(it) {
                inclusive = true
            }
        }
    }

}

fun NavController.removeStackAndPush(route: String) {
    navigate(route) {
        popUpTo(0) {
            inclusive = true
        }
    }
}

fun NavController.push(route: String) {
    navigate(route) {

    }
}

