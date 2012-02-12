-- 触碰事件，用于记录触屏状态
clsTouchEvent = {}
clsTouchEvent.__index = clsTouchEvent
-- 构造体
function clsTouchEvent:new()
	local self = {}
	setmetatable(self,clsTouchEvent)
	self.type = -1
	self.x = -1
	self.y = -1
	return self
end
-- 设置
function clsTouchEvent:setup(x,y,type)
	self.type = type
	self.x = x
	self.y = y
end