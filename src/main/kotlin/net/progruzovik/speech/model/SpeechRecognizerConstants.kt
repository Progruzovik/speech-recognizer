package net.progruzovik.speech.model

object SpeechRecognizerConstants {

    const val UNRECOGNIZED_SPEECH = "[SPEECH]"

    val WORD_SEPARATORS = listOf("<s>", "</s>", "<sil>")

    val OPTIONAL_WORDS = listOf("the", "box")

    val WORD_SYMLINKS = mapOf(
        "ball" to "doll",
        "doll" to "ball",
        "plane" to "train",
        "train" to "plane"
    )
}
