package net.progruzovik.speech.service

import edu.cmu.sphinx.result.Node
import net.progruzovik.speech.model.SpeechRecognizerConstants.OPTIONAL_WORDS
import net.progruzovik.speech.model.SpeechRecognizerConstants.UNRECOGNIZED_SPEECH
import net.progruzovik.speech.model.SpeechRecognizerConstants.WORD_SYMLINKS
import net.progruzovik.speech.node.retrieveNextNodes
import java.util.*

class NodeProcessor(node: Node, private val currentWord: String, private val nextWord: String?) {

    private val remainingNodes = LinkedList<Node>()

    init {
        remainingNodes.offer(node)
    }

    fun retrieveMatchNode(): Node? {
        while (remainingNodes.isNotEmpty()) {
            val node: Node = remainingNodes.poll()
            if (node.word.spelling == currentWord ||
                node.word.spelling == WORD_SYMLINKS[currentWord] ||
                OPTIONAL_WORDS.contains(currentWord) && (node.word.spelling == nextWord || node.word.spelling == UNRECOGNIZED_SPEECH)
            ) {
                return node
            }
            remainingNodes.addAll(node.retrieveNextNodes())
        }
        return null
    }
}
