package mandlebrot.paint

import java.awt.Color

case class Pixel(xPixel: Int, yPixel: Int)

case class PixelColour(pixel: Pixel, color: Color)

case class Point(x: BigDecimal, y: BigDecimal)
