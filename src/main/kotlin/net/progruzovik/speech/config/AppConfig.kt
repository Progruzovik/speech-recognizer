package net.progruzovik.speech.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun recognizerConfiguration(): edu.cmu.sphinx.api.Configuration = edu.cmu.sphinx.api.Configuration().apply {
        acousticModelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us"
        dictionaryPath = "resource:/dictionary.txt"
        languageModelPath = "resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin"
    }
}
