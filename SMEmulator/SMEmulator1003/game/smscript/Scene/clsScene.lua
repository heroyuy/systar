-- 游戏中全部场景的超级类。
clsScene = {}
clsScene.__index = clsScene
-- 构造体
function clsScene:new()
	local self = {}
	setmetatable(self,clsScene)
	self.viewports = {}
	self.main = false;
	return self
end
-- 开始
function clsScene:onStart()
end
-- 处理触屏
function clsScene:onTouch(x,y,type)
end
-- 更新
function clsScene:update()
end
-- 退出
function clsScene:onStop()
	globalGameEngine:stop()
end