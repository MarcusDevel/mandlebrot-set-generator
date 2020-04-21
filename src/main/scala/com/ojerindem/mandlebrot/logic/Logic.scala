package mandlebrot.logic

import java.awt.Color

import mandlebrot.paint.{Pixel,PixelColour,Point}

import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters._


object Logic {


  val xAxisLength = 3.5
  val yAxisLength = 2.0
  val xAxisMin = -2.5
  val yAxisMax = 1.0
  val yAxisMin = -1.0
  val xAxisMax = 1.0

  /**
   * Returns a Color representing the iteration it took for a given
   * point to prove itself 'out' of the mandlebrot set. For points that are
   * none-terminating (in the mandlebrot set) the upper board called
   * maxIteration will capture this and return the Color BLACK
   * @param point the given point to validate against the mandlebrot equation
   * @param maxIterations upperbound to ensure the equation isn't none-terminating
   * @return
   */

  def getPointColor(point: Point, maxIterations: BigInt): Color = {
    val originalX = point.x
    val originalY = point.y

    @tailrec
    def mandlebrotAlgorithm(x: BigDecimal, y: BigDecimal, iteration: Int): Color = {
      iteration == maxIterations match {
        case true => Color.BLACK
        case false =>
          ((x * x) + (y * y)) >= 4 match {
            case true => getIterationColour(iteration)
            case false => {
              mandlebrotAlgorithm(
                originalX + (x * x) - (y * y),
                originalY + (2 * x * y),
                iteration + 1
              )
            }
          }
      }
    }
    mandlebrotAlgorithm(0,0,0)
  }

  def getPixelXYTuples(width: Int, height: Int) = {
    val pixelXYTuples = for {
      xPixel <- 0 until width
      yPixel <- 0 until height
    } yield Pixel(xPixel,yPixel)
    pixelXYTuples
  }

  def getPixelColor(pixel: Pixel, maxIterations: Int, xValueDiff: BigDecimal, yValueDiff: BigDecimal): PixelColour = {
    def getPixelPointEquiv(pixel: Pixel) = {
      Point(xAxisMin + (xValueDiff * pixel.xPixel), yAxisMax - (yValueDiff * pixel.yPixel))
    }

    val pixelPoint = getPixelPointEquiv(pixel)
    val pixelPointColour = getPointColor(pixelPoint,maxIterations)
    PixelColour(pixel,pixelPointColour)
  }

  def getPixelColourMap(pixelXYTuples: IndexedSeq[Pixel], maxIterations: Int, xValueDiff: BigDecimal, yValueDiff: BigDecimal) = {
    val pixelColourMap = for (
      pixel <- pixelXYTuples.par
    ) yield {
      getPixelColor(pixel,maxIterations, xValueDiff, yValueDiff)
    }

    pixelColourMap
  }

  def getIterationColour(iteration: Int) = {
    val modulo = iteration % 16
    modulo match {
      case 0 => new Color(66, 30, 15)
      case 1 => new Color(25, 7, 26)
      case 2 => new Color(9, 1, 47)
      case 3 => new Color(4, 4, 73)
      case 4 => new Color(0, 7, 100)
      case 5 => new Color(12, 44, 138)
      case 6 => new Color(24, 82, 177)
      case 7 => new Color(57, 125, 209)
      case 8 => new Color(134, 181, 229)
      case 9 => new Color(211, 236, 248)
      case 10 => new Color(241, 233, 191)
      case 11 => new Color(248, 201, 95)
      case 12 => new Color(255, 170, 0)
      case 13 => new Color(204, 128, 0)
      case 14 => new Color(153, 87, 0)
      case 15 => new Color(106, 52, 3)
    }
  }

}
