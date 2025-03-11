package nakern.be_camera.utils

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

/**
 * A simple, utility timer running on the client.
 * After creation use [ClientTimer.start]
 */
class ClientTimer(waitTicks: Int, action: () -> Unit) {
    private val ticks: Int = waitTicks;
    private val callback: () -> Unit = action

    fun start() {
        var ticksUntil = ticks;

        ClientTickEvents.END_CLIENT_TICK.register() {
            if (ticksUntil == 0) {
                callback()
                ticksUntil -= 1
                return@register
            }
            ticksUntil -= 1
        }
    }
}