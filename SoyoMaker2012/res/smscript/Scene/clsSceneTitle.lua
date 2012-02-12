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
-- 退出
function clsSceneTitle:onStop()
end
function clsSceneTitle:loadDatabase()
	globalDataLoader = smDataLoader
	globalDataLoader:setup()
	globalDataManager = smDataManager:getInstance()
end
-- 生成游戏对象
function clsSceneTitle:createGameObjects()
	globalDataSystem = {}
	globalDataSystem.skin = ".\\game\\image\\skin\\Window.png\\"
	globalDataSystem.title = ".\\game\\image\\skin\\Window.png\\"
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
	
end 
-- 生成标题画面图像
function clsSceneTitle:createTitleGraphic()
end      
-- 生成指令窗口
function clsSceneTitle:createCommandWindow()
end
-- 播放标题画面音乐
function clsSceneTitle:playTitleMusic()
end               