globalGame={}

--标志常量
globalGame.SCENE_TITLE=0 --标题场景
globalGame.SCENE_MAP=1   --地图场景

--成员
globalGame.curScene=nil

globalGame.rootLayer=nil

--脚本启动
function globalGame:onStart()
  --导入需要的文件
  package.path = package.path .. ";.\\game\\smscript\\?.lua"
  require("requires")
  --配置引擎
  smGameEngine:setShowFps(true)
  smGameEngine:setRatedFps(500)
  smLog:setDebug(true)
  --配置游戏
  self.rootLayer=clsLayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
  self.rootLayer.clipBounds=false
  self.rootLayer.backgroundColor="0xffabcdef"
  smLog:info("game-start")
  --切换到标题场景
  self:changeScene(self.SCENE_TITLE)
end

--触屏事件
function globalGame:onTouch(x,y,type)
  if self.rootLayer then
    self.rootLayer:dispatchEvent(x,y,type)
  end
end

--更新model
function globalGame:update()
  if self.curScene then
    self.curScene:update()
  end
end

--绘制屏幕
function globalGame:paint(painter)
  if self.rootLayer then
    self.rootLayer:paint(painter)
  end
end

--退出
function globalGame:onStop()
  if self.curScene then
    self.curScene:onStop()
    self.rootLayer:removeAll()
  end
  smLog:info("game-stop")
end

--切换场景
function globalGame:changeScene(index)
  --(1)原场景onStop
  if self.curScene then
    self.curScene:onStop()
    self.rootLayer:removeAll()
  end
  --(2)切换场景
  self:createScene(index)
  --(3)新场景onStart
  if self.curScene then
    self.curScene:onStart()
  end
end

--辅助方法:创建场景(非当前Chunk不应该调用)
function globalGame:createScene(index)
 if index==self.SCENE_TITLE then
    self.curScene=clsSceneTitle:new()
 elseif index==self.SCENE_MAP then
    self.curScene=clsSceneMap:new()
  end
end 


