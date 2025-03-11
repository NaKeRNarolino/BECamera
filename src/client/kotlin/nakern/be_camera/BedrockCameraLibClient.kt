package nakern.be_camera

import nakern.be_camera.camera.*
import nakern.be_camera.easings.Easings
import nakern.be_camera.utils.ClientTimer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.option.KeyBinding
import net.minecraft.util.Colors
import net.minecraft.util.math.Vec3d
import org.lwjgl.glfw.GLFW

object BedrockCameraLibClient : ClientModInitializer {
	override fun onInitializeClient() {
//		FadeDrawer.initialize();
//		val keybind = KeyBinding(
//			"becamera.init",
//			GLFW.GLFW_KEY_J,
//			"becamera"
//		)
//
//		ClientTickEvents.END_CLIENT_TICK.register {
//			if (keybind.wasPressed()) {
//				if (CameraManager.isActive()) {
//					CameraManager.clear();
//				} else {
//					CameraManager.setCamera(
//						CameraData(
//                            location = Vec3d(36.0, 71.0, -57.0),
//                            targetLocation = Vec3d(43.0, 63.0, -45.0),
//							easeOptions = EaseOptions(
//								easing = Easings::easeInOutBack,
//								duration = 2500
//							)
//                        )
//					)
//
//					ClientTimer(60) {
//						CameraManager.setFade(
//							CameraFadeOptions(
//                                fadeColor = Colors.WHITE,
//                                fadeInTime = 1000,
//                                holdTime = 1000,
//                                fadeOutTime = 1000
//                            )
//						)
//					}.start()
//
//					ClientTimer(100) {
//						CameraManager.setCamera(
//							CameraData(
//								location = Vec3d(24.0, 71.0, -70.0),
//								targetLocation = Vec3d(43.0, 63.0, -45.0),
//								easeOptions = EaseOptions(
//									easing = Easings::easeInOutBack,
//									duration = 2500
//								)
//							)
//						)
//					}.start()
//				}
//			}

//			println(Clock.System.now().toEpochMilliseconds())
//		}
	}
}