package pl.duch.game.desktop.draw.component

data class Boundary(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun inside(x: Int, y: Int): Boolean =
        (x1 <= x) and (x <= x2) and (y1 <= y) and (y <= y2)

    fun width() = (x2 - x1) + 1
    fun height() = (y2 - y1) + 1
}
