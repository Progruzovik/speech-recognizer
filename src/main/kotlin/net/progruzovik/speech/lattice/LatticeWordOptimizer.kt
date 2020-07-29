package net.progruzovik.speech.lattice

import edu.cmu.sphinx.result.Lattice
import edu.cmu.sphinx.result.LatticeOptimizer
import edu.cmu.sphinx.result.Node

class LatticeWordOptimizer(lattice: Lattice) : LatticeOptimizer(lattice) {

    override fun equivalentNodeLabels(n1: Node, n2: Node): Boolean = n1.word == n2.word
}
