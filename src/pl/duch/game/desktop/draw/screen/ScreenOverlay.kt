package pl.duch.game.desktop.draw.screen

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Window
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.SwingUtilities



class ScreenOverlay {

    companion object {
        val DISABLE_COLOR = Color(0, true)
        val ENABLE_COLOR = Color(0x0A0A0A0A, true)
    }

    private var initialized = false
    private val layers = mutableListOf<ScreenLayer>()
    private var lastInitialized = 0

    private val mouseListener = ScreenOverlayMouseListener()
    private val mouseMotionListener = ScreenOverlayMouseMotionListener()

    private val window = object : Window(null) {
        override fun paint(g: Graphics) {
            super.paint(g)
            val g2d = g as Graphics2D
            initialize(g2d)
            layers.forEach { it.draw(g2d) }
        }

        override fun update(g: Graphics) {
            paint(g)
        }

    }

    fun addLayer(layer: ScreenLayer) {
        this.layers.add(layer)
        window.background = ENABLE_COLOR
        window.background = DISABLE_COLOR
    }

    private fun initialize(g: Graphics2D) {
        if (!initialized) {

            window.addMouseListener(mouseListener)
            window.addMouseMotionListener(mouseMotionListener)

            initialized = true
        }

        if (lastInitialized < layers.size) {
            for(i in lastInitialized until layers.size) {
                layers[i].initialize(window, g)
                mouseListener.addListener(layers[i].mouseListener)
                mouseMotionListener.addListener(layers[i].mouseMotionListener)
            }
        }
    }

    init {
        window.isAlwaysOnTop = true
        window.bounds = window.graphicsConfiguration.bounds
        window.background = DISABLE_COLOR
        window.isVisible = true
    }

}