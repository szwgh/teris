package com.netbox.ui

import java.awt.Graphics

import com.netbox.entity.Box

/**
  * Created by szwgh on 2017/6/30.
  */
trait TerisDisplayTrait {
  def drawBox(g: Graphics, box: Box)

  def drawAllBox(g: Graphics)
}
