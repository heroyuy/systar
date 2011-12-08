globalDataLoader={}

globalDataLoader.loadPlayer=function()
  smLog:setDebug(true)
  local t=os.time()
  dofile(".\\game\\luadata\\test.gat")
  t=os.time()-t
  smLog:info("time"..t)
  print(item)
  smLog:info(item.name)
  smLog:setDebug(false)
end