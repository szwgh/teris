package com.netbox.entity

/**
  * Created by szwgh on 2017/6/27.
  */
object BoxDefine {

  def boxTypeMap = Map(
    0 -> Array(Array(0,0,0,0), Array(0,0,0,0), Array(0,0,0,0),Array(0,0,0,0)),//null
    1 -> Array(Array(1,1,1,1), Array(0,0,0,0), Array(0,0,0,0),Array(0,0,0,0)),//长条
    2 -> Array(Array(1,1,1,0), Array(0,0,1,0), Array(0,0,0,0),Array(0,0,0,0)),//正横折
    3 -> Array(Array(1,1,1,0), Array(1,0,0,0), Array(0,0,0,0),Array(0,0,0,0)),//反横折
    4 -> Array(Array(1,1,1,0), Array(0,1,0,0), Array(0,0,0,0),Array(0,0,0,0)),//T型
    5 -> Array(Array(1,1,0,0), Array(1,1,0,0), Array(0,0,0,0),Array(0,0,0,0)),//方块
    6 -> Array(Array(1,1,0,0), Array(0,1,1,0), Array(0,0,0,0),Array(0,0,0,0)),//正Z
    7 -> Array(Array(0,1,1,0), Array(1,1,0,0), Array(0,0,0,0),Array(0,0,0,0))//反Z

  )
  def boxLength = 30
  def panelTopX=15
  def panelTopY=55
  def panelBottomX=305
  def panelBottomY=640
  val DIRECT_UP=0
  val DIRECT_DOWN=1
  val DIRECT_LEFT=2
  val DIRECT_RIGHT=3
  //定义21行12列的数组，用来判断是否能移动，是否要消去行，因为边也是不能移动的，所以最边上默认为1
  var gameBoxArray=Array.ofDim[Int](20,10)
  val panelWidth=320  //画出来的棋盘大小
  val panelHeight=640 //画出来的棋盘大小
  val boxTopXDif=28   //左上角box与真实位置的偏移值
  val boxTopYDif=68
  val boxSpeed=500
}
