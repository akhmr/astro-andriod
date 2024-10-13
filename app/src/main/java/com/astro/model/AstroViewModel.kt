package com.astro.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astro.data.AstroResponse
import com.astro.data.User
import com.astro.repo.AstroApiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AstroViewModel : ViewModel() {

  //  private val _astroData = MutableStateFlow<AstroResponse?>(null)
    private val _astroData = MutableStateFlow(AstroResponse("","",null))
    val astroData: StateFlow<AstroResponse?> = _astroData

    fun fetchAstroData(user: User) {
        val astroApiRepo = AstroApiRepo()
        viewModelScope.launch {
            try {
                val response = astroApiRepo.getAstroNum(user)
                _astroData.value = response.body()!! // Update the state with the API result
            } catch (e: Exception) {
                // Handle error case, such as updating the state with an error message
                _astroData.value = AstroResponse("201","failure",data = null)
            }
        }
    }
}


