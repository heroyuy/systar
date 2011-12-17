clsSceneMap = {}
setmetatable(clsSceneMap,clsScene)
clsSceneMap.__index = clsSceneMap

function clsSceneMap:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneMap)
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
