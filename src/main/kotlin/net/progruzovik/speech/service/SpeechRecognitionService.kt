package net.progruzovik.speech.service

import edu.cmu.sphinx.api.Configuration
import edu.cmu.sphinx.api.SpeechResult
import edu.cmu.sphinx.api.StreamSpeechRecognizer
import edu.cmu.sphinx.result.Lattice
import edu.cmu.sphinx.result.Node
import net.progruzovik.speech.lattice.optimize
import net.progruzovik.speech.model.domain.ResultHolder
import net.progruzovik.speech.model.dto.SpeechRecognitionResultDTO
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class SpeechRecognitionService(recognizerConfig: Configuration) {

    private val recognizer = StreamSpeechRecognizer(recognizerConfig)

    @Synchronized
    fun recognizeSpeech(excepted: String, speech: ByteArray): SpeechRecognitionResultDTO {
        val resultHolder = ResultHolder(excepted)

        recognizer.startRecognition(ByteArrayInputStream(speech))

        var speechResult: SpeechResult? = recognizer.result
        while (speechResult != null) {
            val lattice: Lattice = speechResult.lattice.optimize()
            var currentNode: Node? = lattice.initialNode
            while (currentNode != null) {
                val nodeProcessor = NodeProcessor(currentNode, resultHolder.currentWord, resultHolder.nextWord)
                currentNode = nodeProcessor.retrieveMatchNode()
                if (currentNode != null) {
                    resultHolder.notifyFound()
                    if (resultHolder.isSuccess) {
                        recognizer.stopRecognition()
                        return SpeechRecognitionResultDTO(true, null)
                    }
                }
            }
            speechResult = recognizer.result
        }

        recognizer.stopRecognition()
        return SpeechRecognitionResultDTO(false, resultHolder.currentWord)
    }
}
