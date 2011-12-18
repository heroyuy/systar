--[[
  description:标题场景
  author:wp_g4
  date:2011/12/18
--]]

--结构定义
clsSceneTitle = {}
setmetatable(clsSceneTitle,clsScene)
clsSceneTitle.__index = clsSceneTitle

--字段
clsSceneTitle.titles={"新的游戏","读取存档","退出游戏"}

--构造器
function clsSceneTitle:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneTitle)
	return self
end

-- 开始
function clsSceneTitle:onStart()
  smLog:info("标题场景启动")
  smAudioPlayer:play(globalGame.PATH..globalDictionary.config.titleMusic)
  --背景大图
  local bgLayer=clsLayer:new(0,0,800,480)
  bgLayer.backgroundImage=smImageFactory:createImage(globalGame.PATH..globalDictionary.config.titleBackground)
  --标题背景
  local titleBg=clsLayer:new(300,300,200,120)
  titleBg.backgroundImage=globalSkin:createBg(200,120)
  bgLayer:addChild(titleBg)
  --标题边框
  local titleFrame=clsLayer:new(0,0,200,120)
  titleFrame.backgroundImage=globalSkin:createFrame(200,120)
  titleBg:addChild(titleFrame)
  --按钮
  local width=160
  local height=25
  local backgroundImage=globalSkin:createBody(width,height)
  local highlightImage=globalSkin:createSelectedBg(width,height)
  local gap=5
  local boundGap=(120-3*height-2*gap)/2
  for i,v in ipairs(self.titles) do
    local button=clsButton:new((200-width)/2,boundGap+(i-1)*(height+gap),width,height)
    button.backgroundImage=backgroundImage
    button.highlightImage=highlightImage
    button.text=v
    button.textColor=globalSkin:getTextColor(1,1)
    button.tag=i
    button.delegate=self
    titleBg:addChild(button)
  end
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

function clsSceneTitle:buttonTapped(target)
  local index=target.tag
  if index==1 then
    --新的游戏
    globalGameData:newGame()
    globalGame:changeScene(globalGame.SCENE_MAP)
  elseif index==2 then
    --读取存档
  elseif index==3 then
    --退出游戏
    globalGame:stop()
  end
end

