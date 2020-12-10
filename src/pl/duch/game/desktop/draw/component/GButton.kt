package pl.duch.game.desktop.draw.component

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Window
import java.awt.event.MouseEvent

class GButton(
    private val window: Window,
    private val positionX: Int, private val positionY: Int,
    private val name: String,
    private val action: () -> Unit = {},
    private val anchor: Int = GAnchor.LEFT,
    private val padding : Int = 0,
    private val margin: Int = 0
) {
    private var initialized: Boolean = false

    private lateinit var boundary: Boundary
    private var fontShift: Int = 0

    private fun initialize(g : Graphics2D) {
        if (!initialized) {
            val dMargin = margin * 2
            val dPadding = padding * 2
            val metrics = g.fontMetrics.getStringBounds(name, g)
            val len = metrics.width.toInt() + dMargin + dPadding
            val height = metrics.height.toInt() + dMargin + dPadding
            val x = GAnchor.horizontal(anchor, positionX, len)
            val y = GAnchor.vertical(anchor, positionY, height)

            val lineMetrics = g.font.getLineMetrics(name, g.fontRenderContext)

            fontShift = (lineMetrics.ascent).toInt()

            boundary = Boundary(x + margin, y + margin, x + len - margin, y + height - margin)

            initialized = true
        }
    }

    fun draw(g : Graphics2D) {
        g.font = window.font.deriveFont(24f)
        initialize(g)

        g.color = Color.BLACK
        g.fillRect(boundary.x1, boundary.y1, boundary.width(), boundary.height())

        g.color = Color.RED
        g.drawString(name, boundary.x1 + padding, boundary.y1 + padding + fontShift)
    }

    fun mouseClicked(e: MouseEvent) {
        if (boundary.inside(e.x, e.y)) {
            action()
        }
    }
}