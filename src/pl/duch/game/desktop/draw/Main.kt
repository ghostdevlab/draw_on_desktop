package pl.duch.game.desktop.draw

import pl.duch.game.desktop.draw.app.UiLayer
import pl.duch.game.desktop.draw.screen.ScreenOverlay

fun main() {
    ScreenOverlay().run {
        addLayer(UiLayer())
    }
}