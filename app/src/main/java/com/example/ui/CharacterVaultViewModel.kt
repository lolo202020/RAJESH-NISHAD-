package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CharacterEntity
import com.example.data.DirectorDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterVaultViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = DirectorDatabase.getDatabase(application).directorDao()

    val savedProfiles: StateFlow<List<CharacterEntity>> = dao.getAllProfiles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _nameInput = MutableStateFlow("")
    val nameInput: StateFlow<String> = _nameInput.asStateFlow()

    private val _ageInput = MutableStateFlow("")
    val ageInput: StateFlow<String> = _ageInput.asStateFlow()

    private val _traitsInput = MutableStateFlow("")
    val traitsInput: StateFlow<String> = _traitsInput.asStateFlow()

    private val _actingStyleInput = MutableStateFlow("")
    val actingStyleInput: StateFlow<String> = _actingStyleInput.asStateFlow()

    private val _voiceInput = MutableStateFlow("")
    val voiceInput: StateFlow<String> = _voiceInput.asStateFlow()

    private val _dialogueInput = MutableStateFlow("")
    val dialogueInput: StateFlow<String> = _dialogueInput.asStateFlow()

    fun updateName(v: String) { _nameInput.value = v }
    fun updateAge(v: String) { _ageInput.value = v }
    fun updateTraits(v: String) { _traitsInput.value = v }
    fun updateActingStyle(v: String) { _actingStyleInput.value = v }
    fun updateVoice(v: String) { _voiceInput.value = v }
    fun updateDialogue(v: String) { _dialogueInput.value = v }

    fun saveProfile(onSuccess: () -> Unit) {
        val name = _nameInput.value.trim()
        if (name.isBlank()) return

        viewModelScope.launch {
            val entity = CharacterEntity(
                characterName = name,
                visualAge = _ageInput.value.ifBlank { "30s" },
                facialFeatures = _traitsInput.value.ifBlank { "Expressive, authentic natural features" },
                actingStyle = _actingStyleInput.value.ifBlank { "Restrained method realism" },
                voicePattern = _voiceInput.value.ifBlank { "Low baritone, deliberate cadence" },
                dialogueHistory = _dialogueInput.value.ifBlank { "Initial dialogue record..." }
            )
            dao.insertProfile(entity)
            _nameInput.value = ""
            _ageInput.value = ""
            _traitsInput.value = ""
            _actingStyleInput.value = ""
            _voiceInput.value = ""
            _dialogueInput.value = ""
            onSuccess()
        }
    }

    fun deleteProfile(entity: CharacterEntity) {
        viewModelScope.launch {
            dao.deleteProfile(entity)
        }
    }
}
