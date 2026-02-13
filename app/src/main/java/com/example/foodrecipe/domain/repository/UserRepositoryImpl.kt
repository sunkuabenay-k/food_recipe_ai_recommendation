package com.example.foodrecipe.data.repository

import com.example.foodrecipe.core.util.PasswordHasher
import com.example.foodrecipe.data.local.dao.UserDao
import com.example.foodrecipe.data.local.entity.UserEntity
import com.example.foodrecipe.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> {
        return try {
            val user = userDao.getUserByEmail(email) ?:
            return Result.failure(Exception("No account found with this email."))

            if (PasswordHasher.verify(password, user.password)) {
                // Clear old sessions and set this user as active
                userDao.logoutAllUsers()
                userDao.setLoginState(user.userId)

                // Return a copy with the updated state for the UI to use immediately
                Result.success(user.copy(isLoggedIn = true))
            } else {
                Result.failure(Exception("Incorrect password. Please try again."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(name: String, email: String, password: String): Result<UserEntity> {
        return try {
            // 1. Double check email availability
            if (isEmailTaken(email)) {
                return Result.failure(Exception("An account with this email already exists."))
            }

            // 2. Create the entity (Hashing is handled here)
            val newUser = UserEntity(
                name = name,
                email = email,
                password = PasswordHasher.hash(password),
                createdAt = System.currentTimeMillis(),
                isLoggedIn = true
            )

            // 3. Clear any existing sessions before logging in the new user
            userDao.logoutAllUsers()
            userDao.insertUser(newUser)

            Result.success(newUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun checkAutoLogin(): Result<UserEntity> {
        return try {
            val user = userDao.getLoggedInUser()
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("No session found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            userDao.logoutAllUsers()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLoggedInUser(): UserEntity? =
        userDao.getLoggedInUser()

    override suspend fun isEmailTaken(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }

    override fun observeLoggedInUser(): Flow<UserEntity?> {
        return userDao.observeLoggedInUser()
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return try {
            val currentUser = userDao.getLoggedInUser()

            if (currentUser != null) {
                userDao.deleteUser(currentUser)
                userDao.logoutAllUsers()
                Result.success(Unit)
            } else {
                Result.failure(Exception("No user logged in"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(userId: String): UserEntity? {
        return userDao.getUserById(userId)
    }
}
