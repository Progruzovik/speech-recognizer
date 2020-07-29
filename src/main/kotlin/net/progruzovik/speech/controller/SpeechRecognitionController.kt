package net.progruzovik.speech.controller

import net.progruzovik.speech.model.dto.SpeechRecognitionResultDTO
import net.progruzovik.speech.service.SpeechRecognitionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/speech")
class SpeechRecognitionController(private val speechRecognitionService: SpeechRecognitionService) {

    @CrossOrigin("*")
    @PostMapping
    fun postSpeech(@RequestParam excepted: String, @RequestBody speech: ByteArray): SpeechRecognitionResultDTO {
        return speechRecognitionService.recognizeSpeech(excepted, speech)
    }
}
