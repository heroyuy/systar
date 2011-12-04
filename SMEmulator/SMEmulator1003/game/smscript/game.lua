--脚本启动
function onStart()
  --导入需要的文件
  package.path = package.path .. ";.\\game\\smscript\\?.lua"
  require("requires")
  --创建地图场景
  game_sceneMap=clsSceneMap:new()
  game_curScene=nil;
  changeScene(0)
end

--触屏事件
function onTouch(x,y,type)
  if game_curScene then
    game_curScene:onTouch(x,y,type)
  end
end

--更新model
function update()
  if game_curScene then
    game_curScene:update()
  end
end

--绘制屏幕
function paint(painter)
  if game_curScene then
    painter:setColor(0x000000)
    painter:fillRect(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
    game_curScene:paint(painter)
  end
end

--退出
function onStop()
  if game_curScene then
    game_curScene:onStop()
  end
end

--切换场景
function changeScene(index)
  --(1)原场景onStop
  if game_curScene then
    game_curScene:onStop()
  end
  --(2)切换场景
  if index==0 then
    game_curScene=game_sceneMap
  end
  --(3)新场景onStart
  if game_curScene then
    game_curScene:onStart()
  end
end


