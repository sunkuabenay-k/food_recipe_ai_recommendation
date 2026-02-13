package com.example.foodrecipe.ui.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipe.data.local.entity.UserEntity
import com.example.foodrecipe.domain.repository.UserRepository
import com.example.foodrecipe.domain.usecase.CheckSessionUseCase
import com.example.foodrecipe.domain.usecase.LoginUseCase
import com.example.foodrecipe.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UserRepository,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val checkSessionUseCase: CheckSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()

    /* ---------------- SESSION CHECK(Auto login) ---------------- */
    init{
        checkAutoLogin()
    }

    private fun checkAutoLogin(){
        viewModelScope.launch {
            val result = checkSessionUseCase()
            result.onSuccess {
                _event.send(AuthEvent.NavigateToHome)
            }
        }
    }
    val loggedInUser: Flow<UserEntity?> =
        repository.observeLoggedInUser()

    /* ---------------- INPUT HANDLERS ---------------- */

    fun onNameChange(value: String) {
        _uiState.update { it.copy(name = value, error = null) }
    }

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, error = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, error = null) }
    }

    /* ---------------- LOGIN ---------------- */

    fun login() {

        val state = _uiState.value

        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(error = "All fields are required") }
            return
        }

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = loginUseCase(
                state.email.trim(),
                state.password.trim()
            )

            result.onSuccess {
                _uiState.update {
                    it.copy(password = "", isLoading = false)
                }
                _event.send(AuthEvent.NavigateToHome)
            }

            result.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Login failed"
                    )
                }
            }
        }
    }

    /* ---------------- REGISTER ---------------- */

    fun register() {

        val state = _uiState.value

        when {
            state.name.isBlank() ||
                    state.email.isBlank() ||
                    state.password.isBlank() -> {
                _uiState.update { it.copy(error = "All fields are required") }
                return
            }

            !Patterns.EMAIL_ADDRESS.matcher(state.email).matches() -> {
                _uiState.update { it.copy(error = "Invalid email format") }
                return
            }

            state.password.length < 6 -> {
                _uiState.update { it.copy(error = "Password must be 6+ characters") }
                return
            }
        }

        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }

            val result = registerUseCase(
                state.name.trim(),
                state.email.trim(),
                state.password.trim()
            )

            result.onSuccess {
//                loginUseCase(state.email, state.password)
                _uiState.update { it.copy(isLoading = false) }
                _event.send(AuthEvent.NavigateToHome)
            }

            result.onFailure { e ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Registration failed"
                    )
                }
            }
        }
    }

    /* ---------------- LOGOUT ---------------- */
    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _event.send(AuthEvent.NavigateToSignIn)
        }
    }
}
