package com.astro

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.astro.composable.component.DateOfBirthPicker
import com.astro.composable.component.GenderSelection
import com.astro.composable.component.SubmitButton
import com.astro.composable.component.UserInputField
import com.astro.data.AstroResponse
import com.astro.data.User
import com.astro.model.AstroViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun InitUi(viewModel: AstroViewModel) {
    // Temporary states for inputs
    val userName = remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var selectedDate by remember { mutableStateOf("Select Date of Birth") }

    // Submitted values
    var submittedName by remember { mutableStateOf("") }
    var submittedGender by remember { mutableStateOf("Male") }
    var submittedDateOfBirth by remember { mutableStateOf("Select Date of Birth") }

    // Calendar initialization for date picker
    val calendar = Calendar.getInstance()
    val (year, month, day) = Triple(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

    val astroData by viewModel.astroData.collectAsState() //
    val coroutineScope = rememberCoroutineScope() // Manage coroutines in Compose

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, pickedYear, pickedMonth, pickedDay ->
            selectedDate = String.format("%02d/%02d/%d", pickedDay, pickedMonth + 1, pickedYear)
        },
        year, month, day
    )
    // Background Gradient
    val gradient = Brush.verticalGradient(
        colors = listOf(Cyan, LightGray, Yellow)
    )

    Column(
        modifier = Modifier
            .fillMaxSize() // Ensures the column takes the entire screen
            .background(gradient) // Applies the gradient background
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Scrolls if content overflows
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Username input field
        UserInputField(userName.value) { userName.value = it }
        GenderSelection(gender) { gender = it }
        DateOfBirthPicker(selectedDate, datePickerDialog)

        SubmitButton {
            coroutineScope.launch {
                val user = User(userName.value, selectedDate)
                viewModel.fetchAstroData(user)
            }
        }
            // Display data from API
            astroData?.let { AstroDataComposable(it) }
        }
    }


@Composable
fun AstroDataComposable(astroData: AstroResponse) {

    Text(
        text = astroData.data.toString(),
        modifier = Modifier.padding(top = 20.dp),
        style = MaterialTheme.typography.bodyMedium
    )

}

/*@Composable
@Preview(showSystemUi = true, showBackground = true)
fun InitUiPreview() {
    InitUi(viewModel)
}
*/