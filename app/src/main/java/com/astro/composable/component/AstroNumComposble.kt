package com.astro.composable.component

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
import com.astro.data.AstroResponse
import com.astro.data.User
import com.astro.model.AstroViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

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