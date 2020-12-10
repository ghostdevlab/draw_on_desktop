package pl.duch.game.desktop.draw.screen

import java.awt.Graphics2D
import java.awt.Window
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

interface ScreenLayer {
    fun initialize(window: Window, g: Graphics2D)
    fun draw(g: Graphics2D)

    val mouseListener : MouseListener?
    val mouseMotionListener : MouseMotionListener?
}