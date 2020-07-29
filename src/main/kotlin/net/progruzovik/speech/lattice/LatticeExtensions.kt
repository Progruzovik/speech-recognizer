package net.progruzovik.speech.lattice

import edu.cmu.sphinx.result.Lattice

fun Lattice.optimize(): Lattice {
    LatticeWordOptimizer(this).optimize()
    return this
}
