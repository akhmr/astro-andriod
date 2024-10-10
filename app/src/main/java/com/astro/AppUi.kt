package com.astro

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import java.util.Calendar

@Composable
fun InitUi() {
    // Temporary states for inputs
    val tempUserName = remember { mutableStateOf("") }
    var tempGender by remember { mutableStateOf("Male") }
    var tempSelectedDate by remember { mutableStateOf("Select Date of Birth") }

    // Submitted values
    var submittedName by remember { mutableStateOf("") }
    var submittedGender by remember { mutableStateOf("Male") }
    var submittedDateOfBirth by remember { mutableStateOf("Select Date of Birth") }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, pickedYear, pickedMonth, pickedDay ->
            // Format day and month with leading zero if necessary
            tempSelectedDate = String.format("%02d/%02d/%d", pickedDay, pickedMonth + 1, pickedYear)
        },
        year, month, day
    )

    // State to control display of selected values after submission
    var showSummary by remember { mutableStateOf(false) }

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
        OutlinedTextField(
            value = tempUserName.value,
            onValueChange = {
                tempUserName.value = it
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "person")
            },
            label = {
                Text(text = "Name")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Gender selection with text on the same row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Gender: ", modifier = Modifier.padding(end = 8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = tempGender == "Male", onClick = { tempGender = "Male" })
                Text(text = "Male")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = tempGender == "Female", onClick = { tempGender = "Female" })
                Text(text = "Female")
            }
        }

        // Row for DOB label and Date picker button with spacing
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // DOB label
            Text(text = "DOB: ", modifier = Modifier.padding(end = 8.dp))

            Spacer(modifier = Modifier.padding(start = 40.dp)) // Adds space between DOB and Button

            // Date picker for date of birth
            Button(onClick = { datePickerDialog.show() }) {
                Text(text = tempSelectedDate)
            }
        }

        // Submit button
        Button(
            onClick = {
                submittedName = tempUserName.value
                submittedGender = tempGender
                submittedDateOfBirth = tempSelectedDate
                showSummary = true // Show the summary below upon button click
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }

        // Display the selected values if the Submit button is clicked
        if (showSummary) {
            Spacer(modifier = Modifier.padding(16.dp)) // Adds spacing before the display
            Text(
                text = "Summary",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Name: $submittedName", fontSize = 18.sp, color = Black)
            Text(text = "Gender: $submittedGender", fontSize = 18.sp, color = Black)
            Text(text = "Date of Birth: $submittedDateOfBirth", fontSize = 18.sp, color = Black)
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun InitUiPreview() {
    InitUi()
}
