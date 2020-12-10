package pl.duch.game.desktop.draw.geo

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.lang.Math.abs

fun lerp(dt: Float, start: Float, stop: Float) =
    when {
        dt > stop -> 0.0f
        dt > start -> 1.0f - (dt - start) / (stop - start)
        else -> 1.0f
    }

class GPath(private val delta: Int) {
    private val points = mutableListOf<GPoint>()
    private var lastUpdate = System.currentTimeMillis()

    var alive = true
        private set

    fun updatePoint(x: Int, y: Int) : Boolean {
        if (points.isEmpty()) {
            points.add(GPoint(x, y))
        } else {
            val last = points.last()
            val testDelta = abs(last.x - x) + abs(last.y - y)
            if (testDelta > delta) {
                points.add(GPoint(x, y))
                lastUpdate = System.currentTimeMillis()
                return true
            }
        }
        return false
    }

    fun draw(g : Graphics2D) {
        if (points.size < 2) return

        val dt = (System.currentTimeMillis() - lastUpdate) / 1000.0f
        val alpha = lerp(dt, 5.0f, 8.0f)

        if (alpha < 0.05f) {
            alive = false
            return
        }

        g.color = Color(0.0f, 1.0f, 0.0f, alpha)
        g.stroke = BasicStroke(3.0f)

        var p0 = points[0]
        for(i in 1 until points.size) {
            val p1 = points[i]
            g.drawLine(p0.x, p0.y, p1.x, p1.y)
            p0 = p1
        }
    }
}