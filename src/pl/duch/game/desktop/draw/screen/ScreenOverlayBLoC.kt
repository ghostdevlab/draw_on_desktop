package pl.duch.game.desktop.draw.screen

import java.util.concurrent.atomic.AtomicInteger

class ScreenOverlayBLoC(
    val refresh: () -> Unit = {}
) {
    val state = AtomicInteger(0)
}