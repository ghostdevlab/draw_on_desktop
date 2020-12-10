package pl.duch.game.desktop.draw.app

import pl.duch.game.desktop.draw.component.GAnchor
import pl.duch.game.desktop.draw.component.GButton
import pl.duch.game.desktop.draw.screen.ScreenLayer
import pl.duch.game.desktop.draw.screen.ScreenOverlayBLoC
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Window
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import kotlin.system.exitProcess

class UiLayer : ScreenLayer {
    lateinit var buttons : List<GButton>
    lateinit var startToggle : List<GButton>

    lateinit var bloc: ScreenOverlayBLoC

    override fun initialize(window: Window, bloc: ScreenOverlayBLoC, g: Graphics2D) {
        this.bloc = bloc
        buttons = mutableListOf(
            GButton(window, window.width, 50, "Quit", { exitProcess(0) }, GAnchor.RIGHT, 10, 10)
        )

        startToggle = mutableListOf(
            GButton(window, window.width, 120, "Start", ::toggleStart, GAnchor.RIGHT, 10, 10),
            GButton(window, window.width, 120, "Stop", ::toggleStart, GAnchor.RIGHT, 10, 10)
        )
    }

    private fun toggleStart() {
        bloc.state.set((bloc.state.get()+ 1) and 1)
        bloc.refresh()
    }

    override fun draw(g: Graphics2D) {
        g.color = Color.RED

        buttons.forEach { it.draw(g) }
        startToggle[bloc.state.get()].draw(g)
    }

    override val mouseListener: MouseListener? = object : MouseListener {
        override fun mouseReleased(e: MouseEvent?) {
        }

        override fun mouseEntered(e: MouseEvent?) {
        }

        override fun mouseClicked(e: MouseEvent?) {
            e?.let { event ->
                println(event)
                buttons.forEach { it.mouseClicked(event) }
                startToggle[bloc.state.get()].mouseClicked(event)
            }
        }

        override fun mouseExited(e: MouseEvent?) {
        }

        override fun mousePressed(e: MouseEvent?) {
        }

    }
    override val mouseMotionListener: MouseMotionListener? = null
}