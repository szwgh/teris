package com.netbox.ui

import java.awt.{Color, Dimension, Graphics, Graphics2D}
import javax.swing.{JComponent, JPanel}

import com.netbox.entity.{Box, BoxDefine}
import com.netbox.control.TerisControl

/**
  * Created by szwgh on 2017/6/30.
  */
class TerisDisplay extends JComponent with TerisDisplayTrait{

  setPreferredSize(new Dimension(580, 700))

  override  def paintComponent(g:Graphics): Unit =
  {
    if(MainFrame.getInstance().isGameStartFlag){
      drawBox(g,MainFrame.getInstance().getCurrentBox)
      drawAllBox(g)
    }
  }

  override def drawBox(g:Graphics,box: Box): Unit = {
    //val g2: Graphics2D = g.asInstanceOf[Graphics2D]

    val g2: Graphics2D = g.asInstanceOf[Graphics2D]

    val dispBox = box.boxSharpArray
    var x = box.xPosition
    var y = box.yPosition
    val terisControl = new TerisControl
    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        if (dispBox(i)(j) == 1) {
          g2.setColor(Color.BLUE)
          g2.fillRect(x, y, BoxDefine.boxLength - 1, BoxDefine.boxLength - 1)
//          terisControl.setGameBoxPanelValue(x, y, 1)

        }
        x += BoxDefine.boxLength
      }
      x = box.xPosition
      y += BoxDefine.boxLength
    }
  }

  override def drawAllBox(g:Graphics): Unit = {
    val g2: Graphics2D =g.asInstanceOf[Graphics2D]
    g2.setColor(Color.BLUE)
    for (i <- 0 to 9) {
      for (j <- 0 to 19) {
        if (BoxDefine.gameBoxArray(j)(i) == 2) {
          g2.fillRect((i + 1) * BoxDefine.boxLength-2 , 38+ (j + 1) * BoxDefine.boxLength, BoxDefine.boxLength - 1, BoxDefine.boxLength - 1)
        }
      }
    }
  }


}
