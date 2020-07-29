package net.progruzovik.speech.node

import edu.cmu.sphinx.result.Node
import net.progruzovik.speech.model.SpeechRecognizerConstants.WORD_SEPARATORS
import java.util.*

fun Node.retrieveNextNodes(): List<Node> {
    val nextNodes = ArrayList<Node>()
    for (edge in leavingEdges) {
        if (WORD_SEPARATORS.contains(edge.toNode.word.spelling)) {
            nextNodes.addAll(edge.toNode.retrieveNextNodes())
        } else {
            nextNodes.add(edge.toNode)
        }
    }
    return nextNodes
}
