--重定义print方法
do
  local oldPrint=print
  print=function(arg)
    oldPrint(arg)
    io.flush()
  end
end