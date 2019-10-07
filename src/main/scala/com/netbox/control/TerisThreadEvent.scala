package com.netbox.control

import akka.actor.UntypedActor
import akka.event.Logging
import com.netbox.entity.BoxDefine
import com.netbox.ui.MainFrame;

/**
  * Created by szwgh on 2017/7/9.
  */
class TerisThreadEvent extends UntypedActor {

  case class Message()

  import akka.event.LoggingAdapter

  val log: LoggingAdapter = Logging.getLogger(getContext.system, this)

  override def onReceive(message: Any): Unit = {
    val tc = new TerisControl
    message match {
      case "boxDown" => {
        if (MainFrame.getInstance().isGameStartFlag) {
          MainFrame.getInstance().getScoreLabel().setText(MainFrame.score + "")
          MainFrame.getInstance().getScoreLabel().repaint()
          tc.boxMove(MainFrame.getInstance().getCurrentBox, BoxDefine.DIRECT_DOWN)
        }

      }
      case _ => {

      }
    }
  }
}
