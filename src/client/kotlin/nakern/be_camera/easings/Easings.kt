package nakern.be_camera.easings

import kotlin.math.*

typealias EasingFn = (Float) -> Float;

object Easings {
    fun none(x: Float): Float {
        return 1.0f;
    }

    fun linear(x: Float): Float {
        return x;
    }

    fun easeInSine(x: Float): Float {
        return (1 - cos(x * PI / 2)).toFloat()
    }

    fun easeOutSine(x: Float): Float {
        return cos(x * PI / 2).toFloat()
    }

    fun easeInOutSine(x: Float): Float {
        return (-(cos(PI * x) - 1) / 2).toFloat();
    }

    fun easeInQuad(x: Float): Float {
        return x.pow(2);
    }

    fun easeOutQuad(x: Float): Float {
        return 1f - (1f - x).pow(2);
    }

    fun easeInOutQuad(x: Float): Float {
        return if (x < 0.5) {
            2 * x.pow(2)
        } else {
            1 - (-2 * x + 2).pow(2) / 2
        }
    }

    fun easeInCubic(x: Float): Float {
        return x.pow(3);
    }

    fun easeOutCubic(x: Float): Float {
        return 1 - (1 - x).pow(3);
    }

    fun easeInOutCubic(x: Float): Float {
        return if (x < 0.5) {
            4 * x.pow(3)
        } else {
            1 - (-2 * x + 2).pow(3) / 2
        }
    }

    fun easeInQuart(x: Float): Float {
        return x.pow(4);
    }

    fun easeOutQuart(x: Float): Float {
        return 1 - (1 - x).pow(4)
    }

    fun easeInOutQuart(x: Float): Float {
        return if (x < 0.5) {
            8 * x.pow(4)
        } else {
            1 - (-2 * x + 2).pow(4) / 2
        }
    }

    fun easeInQuint(x: Float): Float {
        return x.pow(5);
    }

    fun easeOutQuint(x: Float): Float {
        return 1 - (1 - x).pow(5);
    }

    fun easeInOutQuint(x: Float): Float {
        return if (x < 0.5) {
            16 * x.pow(5)
        } else {
            1 - (-2 * x + 2).pow(5) / 2
        }
    }

    fun easeInExpo(x: Float): Float {
        return if (x == 0f) {
            0f
        } else {
            2f.pow(10 * x - 10)
        }
    }

    fun easeOutExpo(x: Float): Float {
        return if (x == 1f) {
            1f
        } else {
            1 - 2f.pow(-10 * x)
        }
    }

    fun easeInOutExpo(x: Float): Float {
        return if (x == 0f) {
            0f
        } else {
            if (x == 1f) {
                1f
            } else {
                if (x < 0.5f) {
                    2f.pow(20 * x - 10) / 2;
                } else {
                    2 - 2f.pow(-20 * x + 10) / 2;
                }
            }
        }
    }

    fun easeInCirc(x: Float): Float {
        return 1 - sqrt(1 - x.pow(2))
    }

    fun easeOutCirc(x: Float): Float {
        return sqrt(1 - (x - 1).pow(2))
    }

    fun easeInOutCirc(x: Float): Float {
        return if (x < 0.5f) {
            (1 - sqrt(1 - (2 * x).pow(2))) / 2
        } else {
            (sqrt(1 - (-2 * x + 2).pow(2) + 1)) / 2
        }
    }

    fun easeInBack(x: Float): Float {
        val c1 = 1.70158;
        val c3 = c1 + 1f;

        return (c3 * x.pow(3) - c1 * x.pow(2)).toFloat()
    }

    fun easeOutBack(x: Float): Float {
        val c1 = 1.70158;
        val c3 = c1 + 1f;

        return (1f + c3 * (x - 1).pow(3) + c1 * (x - 1).pow(2)).toFloat()
    }

    fun easeInOutBack(x: Float): Float {
        val c1 = 1.70158;
        val c2 = c1 * 1.525;

        return if (x < 0.5) {
            ((2 * x).pow(2) * ((c2 + 1) * 2 * x - c2) / 2).toFloat()
        } else {
            (((2 * x - 2).pow(2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2).toFloat()
        }
    }

    fun easeInElastic(x: Float): Float {
        val c4 = (2 * PI) / 3;

        return when (x) {
            0f -> {
                0f
            }
            1f -> {
                1f
            }
            else -> {
                (-(2f.pow(10 * x - 10)) * sin(c4 * (x * 10 - 10.75))).toFloat()
            }
        }
    }

    fun easeOutElastic(x: Float): Float {
        val c4 = (2 * PI) / 3;

        return when (x) {
            0f -> {
                0f
            }
            1f -> {
                1f
            }
            else -> {
                (2f.pow(-10 * x) * sin(c4 * (x * 10 - 0.75))).toFloat() + 1f
            }
        }
    }

    fun easeInOutElastic(x: Float): Float {
        val c5 = (2 * Math.PI) / 4.5

        return (when {
            x == 0f -> 0f
            x == 1f -> 1f
            x < 0.5 -> -((2f.pow(20 * x - 10) * sin((20 * x - 11.125) * c5)) / 2)
            else -> (2f.pow(-20 * x + 10) * sin((20 * x - 11.125) * c5)) / 2 + 1
        }).toFloat()
    }
}