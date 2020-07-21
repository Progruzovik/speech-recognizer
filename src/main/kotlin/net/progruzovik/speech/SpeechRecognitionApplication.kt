package net.progruzovik.speech

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.logging.Level
import java.util.logging.Logger

@SpringBootApplication
class SpeechRecognitionApplication

fun main(args: Array<String>) {
    val recognizerLogger: Logger = Logger.getLogger("default.config")
    recognizerLogger.level = Level.OFF
    val recognizerConfig = System.getProperty("java.util.logging.config.file")
    if (recognizerConfig == null) {
        System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput")
    }
    runApplication<SpeechRecognitionApplication>(*args)
}
