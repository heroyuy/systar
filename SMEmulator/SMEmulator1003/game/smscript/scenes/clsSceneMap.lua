--[[
  description:地图场景
  author:wp_g4
  date:2011/12/18
--]]

--结构定义
clsSceneMap = {}
setmetatable(clsSceneMap,clsScene)
clsSceneMap.__index = clsSceneMap

--字段

--构造器
function clsSceneMap:new()
	local self = clsScene:new()
	setmetatable(self,clsSceneMap)
	local mapLayer=clsMapLayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
	local enemySprite=clsSprite:new("game/image/battler/004-Fighter04.png")
	globalGame.rootLayer:addChild(mapLayer)
	globalGame.rootLayer:addChild(enemySprite)
	enemySprite.x=(800-enemySprite.width)/2
	enemySprite.x=(480-enemySprite.height)/2
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
