package com.netbox.control


import java.util.Random

import akka.actor.{ActorSystem, Props}
import com.netbox.entity.{Box, BoxDefine}
import com.netbox.ui.{MainFrame, TerisDisplay}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by szwgh on 2017/7/3.
  */
class TerisControl extends TerisControlTrait {

  override def getGameBoxPanelValue(x: Int, y: Int): Int = {
    var i = 0
    var j = 0
    for (i <- 0 to 9) {
      for (j <- 0 to 19) {
        if ((i + 1) * BoxDefine.boxLength - 2 == x && 38 + (j + 1) * BoxDefine.boxLength == y) {
          return BoxDefine.gameBoxArray(j)(i)
        }
      }
    }

    return 0
  }


  override def setGameBoxPanelValue(x: Int, y: Int, value: Int): Unit = {
    var i = 0
    var j = 0
    for (i <- 0 to 9) {
      for (j <- 0 to 19) {
        if ((i + 1) * BoxDefine.boxLength - 2 == x && 38 + (j + 1) * BoxDefine.boxLength == y) {
          BoxDefine.gameBoxArray(j)(i) = value
        }
      }
    }
  }

  override def isCanMove(box: Box, direct: Int): Boolean = {
    //模拟出新的位置

    direct match {
      case BoxDefine.DIRECT_UP => {
        box.yPosition -= BoxDefine.boxLength
      }
      case BoxDefine.DIRECT_DOWN => {
        box.yPosition += BoxDefine.boxLength
      }
      case BoxDefine.DIRECT_LEFT => {
        box.xPosition -= BoxDefine.boxLength
      }
      case BoxDefine.DIRECT_RIGHT => {
        box.xPosition += BoxDefine.boxLength
      }
      case _ => {

      }
    }
    var x = box.xPosition
    var y = box.yPosition
    //2种情况不能移动
    //1 BoxDefine.gameBoxArray 对应的位置的值为1，代表这个地方被之前的方块占领了
    //2 边界超出panel的范围了
    val dispBox = box.boxSharpArray
    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        if (dispBox(i)(j) == 1) {
          if (getGameBoxPanelValue(x, y) == 2) {
            //gameBoxArray 对应的位置的值为2,代表被锁住了
            return false
          }
          //边界超出panel的范围了
          if (x < BoxDefine.panelTopX || x > BoxDefine.panelBottomX || y < BoxDefine.panelTopY || y > BoxDefine.panelBottomY) {
            return false
          }
        }
        x += BoxDefine.boxLength
      }
      x = box.xPosition
      y += BoxDefine.boxLength
    }
    true
  }


  override def setBoxSpeed: Unit = ???

  override def boxAutoMove: Unit = {

    val actorSystem = ActorSystem("actorSystem")
    val systemActor = actorSystem.actorOf(Props[TerisThreadEvent], name = "systemActor")

    // 在2秒后向testActor发送消息“haha”
    actorSystem.scheduler.schedule(0 seconds, BoxDefine.boxSpeed milliseconds, systemActor, "boxDown")
  }

  override def doLineClear: Int = {
    val td = new TerisDisplay
    var aleardyLineClear = 0
    var clearLineThisTime = -1
    while (aleardyLineClear < 4 && clearLineThisTime != 0) {
      clearLineThisTime = lineClear
      aleardyLineClear += clearLineThisTime
    }

    return aleardyLineClear
  }

  override def lineClear: Int = {
    var removeLines = 0
    for (i <- (0 to 19).reverse) {
      //从最下面开始消的
      var countLockBoxPerLin = 0
      for (j <- 0 to 9) {
        if (BoxDefine.gameBoxArray(i)(j) == 2) countLockBoxPerLin += 1
      }
      //这一行全锁定了，这一行要消去了
      if (countLockBoxPerLin >= 10) {
        removeLines += 1
        for (removerIndex <- (0 to i).reverse)
          for (k <- 0 to 9) {
            if (removerIndex == 0) {
              BoxDefine.gameBoxArray(removerIndex)(k) = 0
            }
            else {
              BoxDefine.gameBoxArray(removerIndex)(k) = BoxDefine.gameBoxArray(removerIndex - 1)(k)
            }
          }
      }
    }
    return removeLines
  }

  def lockBox2(box: Box): Unit = {

    val dispBox = box.boxSharpArray
    var x = box.xPosition
    var y = box.yPosition
    val terisControl = new TerisControl
    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        if (dispBox(i)(j) == 1) {

          terisControl.setGameBoxPanelValue(x, y, 2)

        }
        x += BoxDefine.boxLength
      }
      x = box.xPosition
      y += BoxDefine.boxLength
    }
  }

  override def boxMove(box: Box, direct: Int): Unit = {
    val cloneBox = new Box(box.boxType, box.xPosition, box.yPosition)
    cloneBox.boxSharpArray = box.boxSharpArray
    if (isCanMove(cloneBox, direct)) {
      direct match {
        case BoxDefine.DIRECT_UP => {
          box.yPosition -= BoxDefine.boxLength
        }
        case BoxDefine.DIRECT_DOWN => {
          box.yPosition += BoxDefine.boxLength
        }
        case BoxDefine.DIRECT_LEFT => {
          box.xPosition -= BoxDefine.boxLength
        }
        case BoxDefine.DIRECT_RIGHT => {
          box.xPosition += BoxDefine.boxLength
        }
      }
    }
    else {
      if (direct == BoxDefine.DIRECT_DOWN) {
        lockBox2(MainFrame.getInstance().getCurrentBox)
        val clearLines = doLineClear
        MainFrame.score += clearLines

        if (clearLines == 0 && MainFrame.getInstance().getCurrentBox.yPosition <= BoxDefine.boxTopYDif) {
          MainFrame.getInstance().getScoreLabel.setText("Over")
          MainFrame.getInstance().repaint()
          return
        }
        MainFrame.getInstance().setCurrentBox(new Box(new Random().nextInt(6) + 1, BoxDefine.boxTopXDif, BoxDefine.boxTopYDif))
      }
    }
    MainFrame.gamePanel.repaint()
  }

  override def rotateBox(box: Box): Box = {
    var boxArray = box.boxSharpArray
    var nBoxArray = BoxDefine.boxTypeMap(0)

    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        var tmp = i match {
          case 0 => 3
          case 1 => 2
          case 2 => 1
          case 3 => 0
        }
        nBoxArray(j)(tmp) = boxArray(i)(j)
      }
    }

    box.boxSharpArray = nBoxArray
    box
  }


}
