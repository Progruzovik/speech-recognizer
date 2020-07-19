package net.progruzovik.speech.controller

import net.progruzovik.speech.service.SpeechRecognitionService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/speech")
class SpeechRecognitionController(private val speechRecognitionService: SpeechRecognitionService) {

    @CrossOrigin("*")
    @PostMapping
    fun postSpeech(@RequestBody speech: ByteArray): HashSet<String> {
        return speechRecognitionService.recognizeSpeech(speech)
    }
}
