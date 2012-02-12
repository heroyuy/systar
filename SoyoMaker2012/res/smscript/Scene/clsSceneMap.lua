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
	globalGamePlayer:moveTo(1,432,432)
	self.spriteSet = clsSpriteSetMap:new()
	self.main = true
end
-- 处理触屏
function clsSceneMap:onTouch(x,y,type)
	if type == 0 then
		globalGamePlayer:moveTypeAuto(x,y)
	end
end
-- 更新
function clsSceneMap:update()
	globalGamePlayer:refresh()
	globalGameMap:refresh()
	self.spriteSet:update()
	globalGamePlayer:position()
end
-- 退出
function clsSceneMap:onStop()
end