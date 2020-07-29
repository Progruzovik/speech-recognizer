package net.progruzovik.speech.model.domain

class ResultHolder(excepted: String) {

    private val exceptedList: List<String> = excepted.split(' ')
    private var currentIndex = 0

    val isSuccess: Boolean
        get() = currentIndex >= exceptedList.size

    val currentWord: String
        get() = exceptedList[currentIndex]

    val nextWord: String?
        get() = if (currentIndex >= exceptedList.size - 1) null else exceptedList[currentIndex + 1]

    fun notifyFound() {
        currentIndex++
    }
}
