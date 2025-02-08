package io.github.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.github.compose_utils.BackButtonHandler

@Composable
fun SecondScreen(navController: NavController) {
    var showAskBackDialog by remember { mutableStateOf(false) }

    if (showAskBackDialog) {
        AlertDialog(
            onDismissRequest = { showAskBackDialog = false },
            title = { Text("Confirm Exit") },
            text = { Text("Are you sure you want to go back?") },
            confirmButton = {
                ElevatedButton(onClick = {
                    showAskBackDialog = false
                    navController.popBackStack() // Navigate back
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                ElevatedButton(onClick = {
                    showAskBackDialog = false
                }) {
                    Text("No")
                }
            }
        )
    }
   var handleBackButton by remember { mutableStateOf(false) }
    BackButtonHandler(handleBackButton) { showAskBackDialog = true }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text("Second screen")
        Spacer(Modifier.height(10.dp))
        ElevatedButton(onClick = {
            handleBackButton = !handleBackButton
        }){
            Text("${if(handleBackButton)  "Disable" else "Enabled"} handle back button")
        }
    }
}