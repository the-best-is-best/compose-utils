package io.github.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
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
    BackHandler(handleBackButton) { showAskBackDialog = true }
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
            if (handleBackButton) {
                showAskBackDialog = true
            } else {
                navController.popBackStack()
            }
        }) {
            Text("Go Back")
        }
        Spacer(Modifier.height(10.dp))
        ElevatedButton(onClick = {
            handleBackButton = !handleBackButton
        }){
            Text("${if(handleBackButton)  "Disable" else "Enabled"} handle back button")
        }
    }
}