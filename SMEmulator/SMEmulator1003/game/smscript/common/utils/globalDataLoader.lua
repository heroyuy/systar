globalDataLoader={}

globalDataLoader.loadPlayer=function()
  smLog:setDebug(true)
  local t=os.time()
  dofile(".\\game\\luadata\\player.gat")
  t=os.time()-t
  smLog:info("time"..t)
  --smLog:info("name:"..players[0])
  print(players[0])
  io.flush()
  smLog:setDebug(false)
end