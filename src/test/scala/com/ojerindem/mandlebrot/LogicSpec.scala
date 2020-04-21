package mandlebrot

import mandlebrot.logic.Logic._
import mandlebrot.point._

import org.scalatest.flatspec.AnyFlatSpec

class LogicSpec extends AnyFlatSpec {

  /*"Mandlebrot Logic" should "return None when the 'max iteration' " +
    "parameter is set to 0" in {
    val p = Point(1,2)
    val maxIteration = 0
    val iteration = getIteration(p,maxIteration)
    assert(iteration == None)
  }

  it should "return None for a point which is in the Mandlebrot set" in {
    val p = Point(0,0)
    val maxIteration = 100
    val iteration = getIteration(p,maxIteration)
    iteration match {
      case None => assert(true)
      case Some(v) => assert(false)
    }
  }
  it should "return Some Int value for a point which isn't in the Mandlebrot set" in {
    val p = Point(2,3)
    val maxIteration = 100
    val iteration = getIteration(p,maxIteration)
    iteration match {
      case Some(v) => assert(true)
      case None => assert(false)
    }
  }*/

}
