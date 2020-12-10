package pl.duch.game.desktop.draw.geo

import java.awt.Color
import java.awt.Graphics2D
import java.lang.Math.abs

class GPath(private val delta: Int) {
    private val points = mutableListOf<GPoint>()

    fun updatePoint(x: Int, y: Int) : Boolean {
        if (points.isEmpty()) {
            points.add(GPoint(x, y))
        } else {
            val last = points.last()
            val testDelta = abs(last.x - x) + abs(last.y - y)
            if (testDelta > delta) {
                points.add(GPoint(x, y))
                return true
            }
        }
        return false
    }

    fun draw(g : Graphics2D) {
        if (points.size < 2) return

        g.color = Color.RED
        var p0 = points[0]
        for(i in 1 until points.size) {
            val p1 = points[i]
            g.drawLine(p0.x, p0.y, p1.x, p1.y)
            p0 = p1
        }
    }
}