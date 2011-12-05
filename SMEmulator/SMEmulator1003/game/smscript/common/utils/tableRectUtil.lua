g_rectUtil={}
g_rectUtil.check=function(tx,ty,x,y,w,h)
  if tx>x and tx<x+w and ty>y and ty<y+h then
    return true
  else
    return false
  end
end