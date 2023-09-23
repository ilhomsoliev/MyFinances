package com.ilhomsoliev.myfinances.feature.goals.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ilhomsoliev.myfinances.data.repository.GoalRepository
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.NUMBER_SCREENS_ADD_GOAL
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import com.ilhomsoliev.myfinances.shared.model.ui.constants.CurrencyModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrency
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddGoalViewModel(
    private val repository: GoalRepository,
) : ViewModel() {

    private val _screen = MutableStateFlow(0)
    val screen = _screen.asStateFlow()

    private val _selectedCurrency = MutableStateFlow<CurrencyModel?>(null)
    val selectedCurrency = _selectedCurrency.asStateFlow()

    private val _targetAmount = MutableStateFlow("")
    val targetAmount = _targetAmount.asStateFlow()

    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri = _imageUri.asStateFlow()

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _isReminderActive = MutableStateFlow(true)
    val isReminderActive = _isReminderActive.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private val _deadline = MutableStateFlow(System.currentTimeMillis())
    val deadline = _deadline.asStateFlow()


    suspend fun navigate(page: Int) {
        _screen.emit(page)
    }

    suspend fun navigateForward(onSuccess: () -> Unit) {
        if (_screen.value + 1 < NUMBER_SCREENS_ADD_GOAL) {
            _screen.emit(_screen.value + 1)
        } else {
            checkImage(onSuccess = onSuccess)
        }
    }

    suspend fun navigateBackward() {
        if (_screen.value - 1 >= 0)
            _screen.emit(_screen.value - 1)
    }

    suspend fun changeCurrency(currencyModel: CurrencyModel) {
        _selectedCurrency.emit(currencyModel)
    }

    suspend fun changeTargetAmount(targetAmount: String) {
        _targetAmount.emit(targetAmount)
    }

    suspend fun changeGoalName(goalName: String) {
        _goalName.emit(goalName)
    }

    suspend fun changeReminderActive() {
        _isReminderActive.emit(!_isReminderActive.value)
    }

    suspend fun changeUri(uri: Uri?) {
        _imageUri.emit(uri)
    }

    private suspend fun checkImage(onSuccess: () -> Unit) {
        // Upload Image
        _isLoading.emit(true)
        //onEvent(CreateBotEvent.OnIsUploadingChange(true))
        if (_imageUri.value != null) {
            val storage = Firebase.storage
            val storageRef = storage.reference
            val file = _imageUri.value
            val riversRef = storageRef.child("images/${getRandomString()}")
            val uploadTask = file?.let { riversRef.putFile(it) }
            uploadTask?.addOnFailureListener { exception ->
                viewModelScope.launch {
                    _isLoading.emit(false)
                }
                // TODO Show message in Snackbar
            }?.addOnSuccessListener { taskSnapshot ->
                viewModelScope.launch {
                    _isLoading.emit(false)
                }
                riversRef.downloadUrl.addOnSuccessListener {
                    viewModelScope.launch {
                        _imageUrl.emit(it.normalizeScheme().toString())
                        addGoalToDatabase(onSuccess)
                    }
                    //onEvent(CreateBotEvent.OnImageUrlChange(it.normalizeScheme().toString()))
                }.addOnFailureListener { exception ->
                    viewModelScope.launch {
                        _isLoading.emit(false)
                    }
                    // TODO Show message in Snackbar
                }
            }
        } else {
            addGoalToDatabase(onSuccess = onSuccess)
        }
    }

    private suspend fun addGoalToDatabase(onSuccess: () -> Unit) {
        _selectedCurrency.value?.id?.let { currencyId ->
            repository.insertGoal(
                GoalUi(
                    id = null,
                    name = _goalName.value,
                    targetAmount = _targetAmount.value.toDouble(),
                    currentAmount = 0.0,
                    imageUrl = _imageUrl.value,
                    isReminderActive = _isReminderActive.value,
                    dateCreated = System.currentTimeMillis(),
                    currency = getCurrency(currencyId),
                    deadline = _deadline.value,
                    state = GoalState.Active,
                )
            )
        }
        onSuccess()
    }
}

fun getRandomString(length: Int = 20): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}