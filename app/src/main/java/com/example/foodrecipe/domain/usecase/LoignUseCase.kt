package com.example.foodrecipe.domain.usecase

import com.example.foodrecipe.data.local.entity.UserEntity
import com.example.foodrecipe.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Result<UserEntity> {
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception( "Fields cannot be empty"))
        }
        // Add basic regex for email if needed here
        return repository.login(email, password)
    }
}