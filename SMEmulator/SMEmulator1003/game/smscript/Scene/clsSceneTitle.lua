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
	self:loadDatabase()                     -- 载入数据库
    self:createGameObjects()                -- 生成游戏对象
    self:checkContinue()                    -- 判断继续是否有效
    self:createViewports()                  -- 建立视口
    self:createTitleGraphic()               -- 生成标题画面图像
    self:createCommandWindow()             	-- 生成指令窗口
    self:playTitleMusic()                  	-- 播放标题画面音乐
    self.main = true
end
-- 处理触屏
function clsSceneTitle:onTouch(x,y,type)
end
-- 更新
function clsSceneTitle:update()
	self.commandWindow:update()
    if self.commandWindow.index == 0 then  	   -- 开始游戏
    	self:commandNewGame()
    elseif self.commandWindow.index == 1 then  -- 读取记录
    	self:commandContinue()
    elseif self.commandWindow.index == 2 then  -- 退出游戏
    	self:commandShutdown()
    end
end
-- 读取数据
function clsSceneTitle:loadDatabase()
	globalDataLoader = smDataLoader
	globalDataLoader:setup()
	globalDataManager = smDataManager:getInstance()
end
-- 生成游戏对象
function clsSceneTitle:createGameObjects()
	globalDataSystem = {}
	globalDataSystem.skin = ".\\game\\image\\skin\\Window1.png\\"
	globalDataSystem.title = ".\\game\\image\\picture\\title.png\\"
	globalGameEngine = smGameEngine:getInstance()
	globalAStar = smAStar
	globalGameMap = clsGameMap:new()
	globalGamePlayer = clsGamePlayer:new()
	globalGameActors = clsGameActors:new()
	globalGameParty = clsGameParty:new()
	globalGameParty:setupStartingMembers()
	globalGameTemp = clsGameTemp:new()
	globalGameTroop = clsGameTroop:new()
end
-- 判断继续是否有效
function clsSceneTitle:checkContinue()
	self.continueEnabled = false
end 
-- 生成视口
function clsSceneTitle:createViewports()
	self.viewport = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())
	self.viewportInfo = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())
	self.viewportInfo:setZ(30)
end
-- 生成标题画面图像
function clsSceneTitle:createTitleGraphic()
	self.sprite = clsSpriteBase:new(self.viewport)
	self.sprite:setImage(smImageFactory:createImage(globalDataSystem.title))
	self.sprite:setVisible(true)
end      
-- 生成指令窗口
function clsSceneTitle:createCommandWindow()
	local s1 = vocab:newGame()
	local s2 = vocab:continue()
	local s3 = vocab:shutDown()
	self.commands = {[0] = s1,[1] = s2,[2] = s3}
	self.commandWindow = clsWindowCommand:new(self.viewportInfo,172,self.commands,1,0,32,WLH,2)
	self.commandWindow:setup()
	self.commandWindow:setY(globalGameEngine:getHeight() - self.commandWindow:height() - 80)
	self.commandWindow:setX((globalGameEngine:getWidth() - self.commandWindow:width())/2)
	self.commandWindow:setTouchListener()
	self.commandWindow:setVisible(true)
	self.commandWindow.active = true
end
-- 播放标题画面音乐
function clsSceneTitle:playTitleMusic()
end               
-- 命令：新游戏
function clsSceneTitle:commandNewGame()
	--curScene = clsSceneMap:new()
	curScene = clsSceneBattle:new()
end 
-- 命令：继续游戏
function clsSceneTitle:commandContinue()
	self.commandWindow.index = -1
end 
-- 命令：离开游戏
function clsSceneTitle:commandShutdown()
	self:onStop()
end