package com.example.foodrecipe.domain.repository

import com.example.foodrecipe.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

//package com.yourapp.foodrecipe.core.domain.repository
//
//import com.yourapp.foodrecipe.core.data.local.entity.UserEntity
//
//interface AuthRepository {
//    suspend fun login(email: String, password: String): Result<UserEntity>
//    suspend fun register(name: String, email: String, password: String): Result<UserEntity>
//    suspend fun checkAutoLogin(): Result<UserEntity>
//    suspend fun logout(): Result<Unit>
//}
interface UserRepository {

    suspend fun register(name: String, email: String, password: String): Result<UserEntity>

    suspend fun login(email: String, password: String): Result<UserEntity>

    suspend fun logout(): Result<Unit>

    suspend fun getLoggedInUser(): UserEntity?

    fun observeLoggedInUser(): Flow<UserEntity?>

    suspend fun getUserById(userId: String): UserEntity?

    suspend fun isEmailTaken(email: String): Boolean

    suspend fun deleteAccount(): Result<Unit>

    suspend fun checkAutoLogin(): Result<UserEntity>

}

