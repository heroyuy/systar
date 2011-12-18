--重定义print方法
do
  local oldPrint=print
  print=function(arg)
    oldPrint(arg)
    io.flush()
  end
end

--定义点与矩形位置判断方法
isPointInRect=function(x,y,rectX,rectY,rectWidth,rectHeight)
  if x>=rectX and x<=rectX+rectWidth and y>=rectY and y<=rectY+rectHeight then
    return true
  else
    return false
  end
end