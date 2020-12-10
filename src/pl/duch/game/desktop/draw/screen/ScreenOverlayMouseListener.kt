package pl.duch.game.desktop.draw.screen

import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class ScreenOverlayMouseListener : MouseListener {
    private val listeners : MutableList<MouseListener> = mutableListOf<MouseListener>()

    override fun mouseReleased(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseReleased(event) } }
    }

    override fun mouseEntered(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseEntered(event) } }
    }

    override fun mouseClicked(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseClicked(event) } }
    }

    override fun mouseExited(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseExited(event) } }
    }

    override fun mousePressed(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mousePressed(event) } }
    }

    fun addListener(mouseListener: MouseListener?) {
        mouseListener?.let { listeners.add(it) }
    }
}