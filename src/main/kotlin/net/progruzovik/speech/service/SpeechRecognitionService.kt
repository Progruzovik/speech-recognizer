package net.progruzovik.speech.service

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.SpeechResult
import edu.cmu.sphinx.api.StreamSpeechRecognizer
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream

@Service
class SpeechRecognitionService(private val recognizerConfig: Configuration) {

    fun recognizeSpeech(speech: ByteArray): HashSet<String> {
        val recognizer = StreamSpeechRecognizer(recognizerConfig)
        recognizer.startRecognition(ByteArrayInputStream(speech))

        val recognitionResult = HashSet<String>()
        var speechResult: SpeechResult? = recognizer.result
        while (speechResult != null) {
            speechResult.hypothesis
                .split(' ')
                .also { recognitionResult.addAll(it) }
            speechResult = recognizer.result
        }

        recognizer.stopRecognition()
        return recognitionResult
    }
}
