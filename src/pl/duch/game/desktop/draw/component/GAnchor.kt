package pl.duch.game.desktop.draw.component

class GAnchor {
    companion object {
        val LEFT = 1
        val RIGHT = 2
        val CENTER_H = LEFT or RIGHT
        val TOP = 4
        val BOTTOM = 8
        val CENTER_V = TOP or BOTTOM
        val CENTER = CENTER_H or CENTER_V

        fun horizontal(anchor: Int, positionX: Int, width: Int): Int =
            positionX - when(anchor and CENTER_H) {
                CENTER_H -> width / 2
                RIGHT -> width
                else -> 0
            }

        fun vertical(anchor: Int, positionY: Int, height:Int) : Int =
            positionY - when(anchor and CENTER_V) {
                CENTER_V -> height / 2
                BOTTOM -> height
                else -> 0
            }
    }
}