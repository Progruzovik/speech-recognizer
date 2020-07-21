package net.progruzovik.speech.service

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.SpeechResult
import edu.cmu.sphinx.api.StreamSpeechRecognizer
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class SpeechRecognitionService(recognizerConfig: Configuration) {

    private val recognizer = StreamSpeechRecognizer(recognizerConfig)

    @Synchronized
    fun recognizeSpeech(speech: ByteArray): HashSet<String> {
        recognizer.startRecognition(ByteArrayInputStream(speech))

        val recognitionResult = HashSet<String>()
        var speechResult: SpeechResult? = recognizer.result
        while (speechResult != null) {
            val hypothesis = speechResult.hypothesis
            if (hypothesis.isNotEmpty()) {
                hypothesis.split(' ')
                    .also { recognitionResult.addAll(it) }
            }
            speechResult = recognizer.result
        }

        recognizer.stopRecognition()
        return recognitionResult
    }
}
