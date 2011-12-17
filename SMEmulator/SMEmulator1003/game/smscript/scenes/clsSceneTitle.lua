clsSceneTitle = {}
setmetatable(clsSceneTitle,clsScene)
clsSceneTitle.__index = clsSceneTitle

function clsSceneTitle:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneTitle)
	return self
end

-- 开始
function clsSceneTitle:onStart()
  smLog:info("标题场景启动")
  smAudioPlayer:play(globalGame.PATH.."/audio/music/my_love.mp3")
  local bgLayer=clsLayer:new(0,0,800,480)
  bgLayer.backgroundImage=smImageFactory:createImage(globalGame.PATH..globalDictionary.config.titleBackground)
  local titleBg=clsLayer:new(300,300,200,120)
  titleBg.backgroundImage=globalSkin:createBg(200,120)
  bgLayer:addChild(titleBg)
  local titleFrame=clsLayer:new(295,295,210,130)
  titleFrame.backgroundImage=globalSkin:createFrame(210,130)
  bgLayer:addChild(titleFrame)
  globalGame.rootLayer:addChild(bgLayer)
end


-- 更新
function clsSceneTitle:update()
  --smLog:info("标题场景更新")
end


-- 退出
function clsSceneTitle:onStop()
  smLog:info("标题场景退出")
  smAudioPlayer:stop()
end

