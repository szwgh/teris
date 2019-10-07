package com.netbox.entity

/**
  * Created by szwgh on 2017/7/2.
  */
class Box (_boxType:Int,_xPosition:Int,_yPosition:Int){

  var boxType=_boxType
  var xPosition=_xPosition
  var yPosition=_yPosition
  var boxSharpArray=BoxDefine.boxTypeMap(_boxType)

}
