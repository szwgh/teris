package com.netbox.control

import java.awt._
import java.awt.event.{AWTEventListener, KeyEvent, MouseEvent}

import com.netbox.entity.{Box, BoxDefine}
import com.netbox.ui.{MainFrame, TerisDisplay}

class TerisEventListener extends AWTEventListener {
  override def eventDispatched(event: AWTEvent): Unit = if (event.getClass eq classOf[KeyEvent]) { // 被处理的事件是键盘事件.
    val keyEvent = event.asInstanceOf[KeyEvent]
    if (keyEvent.getID == KeyEvent.KEY_PRESSED) { //按下时你要做的事情
      keyPressed(keyEvent)
    }
    else if (keyEvent.getID == KeyEvent.KEY_RELEASED) { //放开时你要做的事情
      keyReleased(keyEvent)
    }
  }

  private def keyReleased(event: KeyEvent) = {
  }

  private def keyPressed(event: KeyEvent) = {
    val mai = MainFrame.getInstance()
    val terisControl = new TerisControl
    event.getKeyCode match {
      case KeyEvent.VK_SPACE => {
        mai.setGameStartFlag(true)
      }
      case KeyEvent.VK_LEFT => {
        terisControl.boxMove(mai.getCurrentBox, BoxDefine.DIRECT_LEFT)
      }
      case KeyEvent.VK_RIGHT => {
        terisControl.boxMove(mai.getCurrentBox, BoxDefine.DIRECT_RIGHT)
      }
      case KeyEvent.VK_DOWN => {
        terisControl.boxMove(mai.getCurrentBox, BoxDefine.DIRECT_DOWN)
      }
      case KeyEvent.VK_UP => {
        val testRotateBox = new Box(0, mai.getCurrentBox.xPosition, mai.getCurrentBox.yPosition)
        testRotateBox.boxSharpArray = mai.getCurrentBox.boxSharpArray
        if (terisControl.isCanMove(terisControl.rotateBox(testRotateBox), -1)) {
          mai.setCurrentBox(terisControl.rotateBox(mai.getCurrentBox))
          MainFrame.gamePanel.repaint()
        }
      }
      case _ => println("oh no")
    }

  }
}