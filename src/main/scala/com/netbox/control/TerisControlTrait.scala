package com.netbox.control

import com.netbox.entity.Box

/**
  * Created by szwgh on 2017/6/30.
  */
trait TerisControlTrait {

  def isCanMove(box: Box, direct: Int): Boolean

  def setBoxSpeed

  def boxAutoMove

  def lineClear: Int

  def doLineClear: Int

  def boxMove(box: Box, direct: Int)

  def getGameBoxPanelValue(x: Int, y: Int): Int

  def setGameBoxPanelValue(x: Int, y: Int, value: Int)

  def rotateBox(box: Box): Box
}
