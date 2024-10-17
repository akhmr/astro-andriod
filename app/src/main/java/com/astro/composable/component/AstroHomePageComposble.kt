package com.astro.composable.component

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp

@Composable
fun UserInputField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person") },
        label = { Text("Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun GenderSelection(selectedGender: String, onGenderChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Gender: ", modifier = Modifier.padding(end = 8.dp))
        RadioButton(selected = selectedGender == "Male", onClick = { onGenderChange("Male") })
        Text("Male")
        Spacer(modifier = Modifier.width(8.dp))
        RadioButton(selected = selectedGender == "Female", onClick = { onGenderChange("Female") })
        Text("Female")
    }
}

@Composable
fun DateOfBirthPicker(selectedDate: String, datePickerDialog: DatePickerDialog) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("DOB: ", modifier = Modifier.padding(end = 8.dp))
        Spacer(modifier = Modifier.width(40.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = selectedDate)
        }
    }
}

@Composable
fun SubmitButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text("Submit")
    }
}