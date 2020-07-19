package net.progruzovik.speech

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpeechRecognitionApplication

fun main(args: Array<String>) {
    runApplication<SpeechRecognitionApplication>(*args)
}
