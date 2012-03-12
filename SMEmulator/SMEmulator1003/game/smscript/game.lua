globalGame={}

--标志常量：场景
globalGame.SCENE_TITLE=0 --标题场景
globalGame.SCENE_MAP=1   --地图场景
globalGame.SCENE_STATUS=2   --状态场景

--成员
globalGame.curScene=nil

globalGame.rootLayer=nil

--脚本启动  lua层不应该调用
function globalGame:onStart()
  --导入需要的文件
  package.path = package.path .. ";"..smGameEngine:getGamePath().."/smscript/?.lua"
  require("requires")
  --配置引擎
  smGameEngine:setShowFps(true)
  smGameEngine:setRatedFps(20)
  smLog:setDebug(true)
  smLog:info("game-start")
  --配置游戏
  self.rootLayer=clsUILayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
  self.rootLayer.clipBounds=false
  --加载游戏数据
  smDataLoader:init()
  --初始化皮肤
  globalSkin:init(smGameEngine:getGamePath()..globalDictionary.config.skin)
  --切换到标题场景
  self:changeScene(self.SCENE_TITLE)
end

--触屏事件  lua层不应该调用
function globalGame:onTouch(x,y,type)
  if self.rootLayer then
    self.rootLayer:dispatchEvent(x,y,type)
  end
end

--更新model  lua层不应该调用
function globalGame:update()
  --更新数据模型
  globalData:update()
  --更新场景
  if self.curScene then
    self.curScene:update()
  end
  --FPS
  if smGameEngine:getActualFps()<smGameEngine:getRatedFps() then
    smLog:info("FPS警告:"..smGameEngine:getActualFps())
  end
end

--绘制屏幕  lua层不应该调用
function globalGame:paint(painter)
  if self.rootLayer then
    self.rootLayer:paint(painter)
  end
end

--退出  lua层不应该调用
function globalGame:onStop()
  if self.curScene then
    self.curScene:onStop()
    self.rootLayer:clear()
  end
  smLog:info("game-stop")
end

--退出  lua调用
function globalGame:stop()
  smGameEngine:stop()
end

--切换场景  lua调用
function globalGame:changeScene(index)
  --(1)原场景onStop
  if self.curScene then
    self.curScene:onStop()
    self.rootLayer:clear()
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
  elseif index==self.SCENE_STATUS then
    self.curScene=clsSceneStatus:new()
  end
end 



