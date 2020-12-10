package pl.duch.game.desktop.draw.screen

import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class ScreenOverlayMouseMotionListener : MouseMotionListener {
    private val listeners : MutableList<MouseMotionListener> = mutableListOf<MouseMotionListener>()

    override fun mouseDragged(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseDragged(event) } }
    }

    override fun mouseMoved(e: MouseEvent?) {
        e?.let { event -> listeners.forEach { it.mouseMoved(event) } }
    }

    fun addListener(mouseMotionListener: MouseMotionListener?) {
        mouseMotionListener?.let { listeners.add(it) }
    }
}