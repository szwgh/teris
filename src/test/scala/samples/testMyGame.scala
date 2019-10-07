package samples

import java.awt.Graphics2D

import com.netbox.entity.{Box, BoxDefine}
import com.netbox.ui.{MainFrame, TerisDisplay}
import com.netbox.control.TerisControl
import org.junit.Test

import scala.collection.mutable.Stack

/**
  * Created by szwgh on 2017/6/28.
  */
class testMyGame {

  @Test def testRotate() {
    val tc = new TerisControl()
    var box2 = new Box(2, 99, 99)

    box2 = tc.rotateBox(box2)
    for (abc <- box2.boxSharpArray) {
      println(abc(0) + "," + abc(1) + "," + abc(2) + "," + abc(3))
    }
    println("-----------------------------------")
    var box3 = tc.rotateBox(box2)
    for (abc <- box3.boxSharpArray) {
      println(abc(0) + "," + abc(1) + "," + abc(2) + "," + abc(3))
    }
    println("-----------------------------------")
    box2 = tc.rotateBox(box2)
    for (abc <- box2.boxSharpArray) {
      println(abc(0) + "," + abc(1) + "," + abc(2) + "," + abc(3))
    }
    println("-----------------------------------")
    box2 = tc.rotateBox(box2)
    for (abc <- box2.boxSharpArray) {
      println(abc(0) + "," + abc(1) + "," + abc(2) + "," + abc(3))
    }

  }
  @Test def testDrawGamePanel(): Unit = {
    return
    MainFrame.displayFrame()
    val mai = MainFrame.getInstance()
    val g2: Graphics2D = mai.getGraphics().asInstanceOf[Graphics2D]
    Thread.sleep(300)
    var i=0
    var j=0
    for(i<- 1 to 10)
    {
    for(j<-1 to 20)
    {
      g2.drawRect(i*BoxDefine.boxLength+5, 70+j*BoxDefine.boxLength, BoxDefine.boxLength, BoxDefine.boxLength)
    }
    }

    Thread.sleep(30000)
  }

  @Test def testDrawFirstBox(): Unit = {
    return
    MainFrame.displayFrame()
    val mai = MainFrame.getInstance()
    val g2: Graphics2D = mai.getGraphics().asInstanceOf[Graphics2D]
    Thread.sleep(300)
    g2.drawRect(BoxDefine.boxTopXDif, BoxDefine.boxTopYDif, BoxDefine.boxLength, BoxDefine.boxLength)

    Thread.sleep(30000)
  }

  @Test def testDrawFirstLineBox(): Unit = {
    return
    MainFrame.displayFrame()
    val mai = MainFrame.getInstance()
    val g2: Graphics2D = mai.getGraphics().asInstanceOf[Graphics2D]
    Thread.sleep(300)
    var i=0
    for(i<-0 to 9)
    g2.drawRect(BoxDefine.boxTopXDif+i*BoxDefine.boxLength, BoxDefine.boxTopYDif, BoxDefine.boxLength, BoxDefine.boxLength)

    Thread.sleep(30000)
  }

  @Test def testBoxCanMove(): Unit = {
    val tc=new TerisControl
    tc.isCanMove(new Box(1,155,50), BoxDefine.DIRECT_LEFT)
  }
  @Test def testBoxArray(): Unit = {
    var i=0
    var j=0
    for(i<-0 to 19) {
      for (j <- 0 to 9)
      {
        BoxDefine.gameBoxArray(i)(j)=1
      }
    }


    for(i<-0 to 19) {
      for (j <- 0 to 9)
        {
        print(BoxDefine.gameBoxArray(i)(j))
        }
      println()
    }
  }


  @Test def testBoxArray2(): Unit = {
    val tc=new TerisControl
    tc.getGameBoxPanelValue(35,100)
    tc.getGameBoxPanelValue(95,190)
    tc.getGameBoxPanelValue(155,190)
  }
  @Test def testBoxClear(): Unit = {


    val tc=new TerisControl
    var i=0
    var j=0
    var x=0
    for(i<-0 to 19) {
      for (j <- 0 to 9)
      {
        BoxDefine.gameBoxArray(i)(j)=x
        //if(i==0) BoxDefine.gameBoxArray(i)(j)=2
        //if(i==19) BoxDefine.gameBoxArray(i)(j)=2
        if(i==19||i==17||i==15||i==16) BoxDefine.gameBoxArray(i)(j)=2
        x+=1

      }
    }
    for(i<-0 to 19) {
      for (j <- 0 to 9)
      {
        //println("before BoxDefine.gameBoxArray(%d)(%d)=".format(i,j)+BoxDefine.gameBoxArray(i)(j))
        print(BoxDefine.gameBoxArray(i)(j)+"   ")
      }
      println()
    }
//    var aleardyLineClear=0
//    var clearLineThisTime = -1
//    while(aleardyLineClear<4 ||clearLineThisTime!=0)
//      {
//        clearLineThisTime=tc.doLineClear
//        aleardyLineClear+=clearLineThisTime
//      }
//println(aleardyLineClear)
    tc.doLineClear
    for(i<-0 to 19) {
      for (j <- 0 to 9)
      {
        //println("before BoxDefine.gameBoxArray(%d)(%d)=".format(i,j)+BoxDefine.gameBoxArray(i)(j))
        print(BoxDefine.gameBoxArray(i)(j)+"   ")
      }
      println()
    }
  }
  @Test def testAutoRun(): Unit = {
    return
    val tc=new TerisControl
    tc.boxAutoMove
    Thread.sleep(5000)
  }

}
