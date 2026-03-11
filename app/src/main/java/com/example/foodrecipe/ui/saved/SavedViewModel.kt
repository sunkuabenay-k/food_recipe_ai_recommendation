package com.example.foodrecipe.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipe.data.local.entity.RecipeEntity
import com.example.foodrecipe.data.local.entity.SavedRecipeEntity
import com.example.foodrecipe.domain.repository.SavedRepository
import com.example.foodrecipe.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val savedRepository: SavedRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved


    private val _savedRecipes =
        MutableStateFlow<List<RecipeEntity>>(emptyList())
    val savedRecipes: StateFlow<List<RecipeEntity>> = _savedRecipes


    fun observeRecipe(recipeId: Int) {
        viewModelScope.launch {

            val user = userRepository.getLoggedInUser()
                ?: return@launch

            savedRepository
                .observeIsSaved(user.userId, recipeId)
                .collect { saved ->
                    _isSaved.value = saved
                }
        }
    }


    fun observeSavedRecipes() {
        viewModelScope.launch {

            val user = userRepository.getLoggedInUser()
                ?: return@launch

            savedRepository
                .observeSavedRecipes(user.userId)
                .collect {
                    _savedRecipes.value = it
                }
        }
    }


    fun toggleSave(recipeId: Int) {
        viewModelScope.launch {

            val user = userRepository.getLoggedInUser()
                ?: return@launch

            if (_isSaved.value) {

                savedRepository.unsave(
                    user.userId,
                    recipeId
                )

            } else {

                savedRepository.save(
                    SavedRecipeEntity(
                        userId = user.userId,
                        recipeId = recipeId,
                        savedAt = System.currentTimeMillis()
                    )
                )
            }
        }
    }
}