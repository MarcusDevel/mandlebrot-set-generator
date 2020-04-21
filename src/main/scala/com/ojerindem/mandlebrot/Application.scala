package mandlebrot

import mandlebrot.paint.Paint.MandelbrotBitmap

object Application extends App {

  MandelbrotBitmap(args(0).toInt,args(1).toInt,args(2).toInt)

}

