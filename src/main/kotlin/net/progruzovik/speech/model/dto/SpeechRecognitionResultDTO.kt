package net.progruzovik.speech.model.dto

data class SpeechRecognitionResultDTO(
    val isRecognized: Boolean,
    val lastWord: String?
)
