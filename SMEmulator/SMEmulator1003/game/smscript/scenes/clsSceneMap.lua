clsSceneMap = {}
setmetatable(clsSceneMap,clsScene)
clsSceneMap.__index = clsSceneMap

function clsSceneMap:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneMap)
	self.pane=clsLayer:new(20,20,100,80)
	self.pane.backgroundColor="0xffff0000"
	self.pane.delegate=self
	globalGame.rootLayer:addChild(self.pane)
	globalGame.rootLayer.delegate=self
	return self
end

-- 开始
function clsSceneMap:onStart()
  smLog:info("地图场景启动")
  smAudioPlayer:play("game/audio/music/my_love.mp3")
end


-- 更新
function clsSceneMap:update()
  --smLog:info("地图场景更新")
end


-- 退出
function clsSceneMap:onStop()
  smLog:info("地图场景退出")
  smAudioPlayer:stop()
end

function clsSceneMap:onTouch(target,x,y,type)
  if type==globalUIConst.touchEventType.DOWN then
    smLog:info(target:toString())
  end
end
