package com.example.foodrecipe.domain.usecase

import com.example.foodrecipe.data.local.entity.UserEntity
import com.example.foodrecipe.domain.repository.UserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<UserEntity> {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Please fill all fields"))
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.failure(Exception("Invalid email format"))
        }
        return repository.register(name, email, password)
    }
}