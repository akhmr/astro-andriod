package com.astro.model


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.data.AstroResponse
import com.astro.data.User
import com.astro.repo.AstroApiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AstroViewModel : ViewModel() {

    private val _astroData = MutableStateFlow<AstroResponse?>(null)
    val astroData: StateFlow<AstroResponse?> = _astroData

    private val _isLoading = MutableStateFlow(true) // Add loading state
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchAstroData(user: User) {
        val astroApiRepo = AstroApiRepo()
        viewModelScope.launch {
            _isLoading.value = true // Set loading to true when the API call starts
            try {
                val response = astroApiRepo.getAstroNum(user)
                _astroData.value = response.body() // Update the state with the API result
                Log.d("Hello ", _astroData.value.toString())
            } catch (e: Exception) {
                // Handle error case, such as updating the state with an error message
                _astroData.value = AstroResponse("201", "failure", data = null)
            } finally {
                _isLoading.value = false // Set loading to false once the API call finishes
            }
        }
    }
}


