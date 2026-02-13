package com.example.foodrecipe.domain.usecase

import com.example.foodrecipe.data.local.entity.UserEntity
import com.example.foodrecipe.domain.repository.UserRepository
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): Result<UserEntity> = repository.checkAutoLogin()
}