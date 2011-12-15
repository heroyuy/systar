globalGame={}

--标志常量
globalGame.SCENE_MAP=0 --地图场景

--成员
globalGame.curScene=nil

--脚本启动
function globalGame:onStart()
  --导入需要的文件
  package.path = package.path .. ";.\\game\\smscript\\?.lua"
  require("requires")
  --配置引擎
  smGameEngine:setShowFps(true)
  smGameEngine:setRatedFps(500)
  smLog:setDebug(true)
  --切换到地图场景
  self:changeScene(self.SCENE_MAP)
  --smLog:info("onStart")
end

--触屏事件
function globalGame:onTouch(x,y,type)
  --smLog:info("onTouch: x:"..x.." y:"..y.." t:"..type)
  if self.curScene then
    self.curScene:onTouch(x,y,type)
  end
end

--更新model
function globalGame:update()
  --smLog:info("update")
  if self.curScene then
    self.curScene:update()
  end
end

--绘制屏幕
function globalGame:paint(painter)
  --smLog:info("paint")
  if self.curScene then
    self.curScene:paint(painter)
  end
end

--退出
function globalGame:onStop()
  --smLog:info("onStop")
  if self.curScene then
    self.curScene:onStop()
  end
end

--切换场景
function globalGame:changeScene(index)
  --(1)原场景onStop
  if self.curScene then
    self.curScene:onStop()
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
  if index==self.SCENE_MAP then
    self.curScene=clsSceneMap:new()
  end
end 


