package mandlebrot.paint
import java.awt.image.BufferedImage
import java.awt.Color
import java.io.File

import javax.imageio.ImageIO
import mandlebrot.logic.Logic._

import scala.collection.parallel.immutable.ParSeq
import scala.language.reflectiveCalls



object Paint {

  class MandelbrotBitmap(val dim: (Int, Int)) {
    def width = dim._1
    def height = dim._2

    val xValueDiff = BigDecimal(xAxisLength / width)
    val yValueDiff = BigDecimal(yAxisLength / height)

    private val image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)

    def getImage = image

    def apply(x: Int, y: Int) = new Color(image.getRGB(x, y))

    def update(x: Int, y: Int, c: Color) = image.setRGB(x, y, c.getRGB)

    def setPixel(x: Int, y: Int, c: Color) = this(x, y) = c

    def getPixel(x: Int, y: Int) = this(x, y)

    def fill(c: Color) = {
      val g = image.getGraphics
      g.setColor(c)
      g.fillRect(0, 0, width, height)
    }

    def generateMandelbrotImage(maxIterations: Int) = {
      fill(Color.BLACK)
      val pixelXYTuples = getPixelXYTuples(width,height)
      val pixelColourMap = getPixelColourMap(pixelXYTuples,maxIterations,xValueDiff,yValueDiff)
      def setImage(pixelColourMap: ParSeq[PixelColour]): Unit = {
        pixelColourMap.par
          .foreach(
            pixelColour => {
              /*println(s"Working on Pixel(${pixelColour.pixel.xPixel},${pixelColour.pixel.yPixel})" +
                s" With colour ${pixelColour.color.toString}")*/
              setPixel(
                pixelColour.pixel.xPixel,
                pixelColour.pixel.yPixel,
                pixelColour.color
              )
            }
          )
      }
      def writeImage: Unit = ImageIO.write(getImage, "png", new File(s"mandlebrot${System.currentTimeMillis / 1000}.png"))
      setImage(pixelColourMap)
      writeImage
    }
  }

  object MandelbrotBitmap {
    def apply(width: Int, height: Int, maxIterations: Int) = {
      new MandelbrotBitmap(width, height).generateMandelbrotImage(maxIterations)
    }
  }
}
