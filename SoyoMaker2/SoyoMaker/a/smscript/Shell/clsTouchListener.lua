-- 侦听器
clsTouchListener = {}
clsTouchListener.__index = clsTouchListener
-- 构造体,widget组件
function clsTouchListener:new()
	local self = {}
	setmetatable(self,clsTouchListener)
	self.z = 0         -- 触屏优先度
	self.widget = nil
	self.event = clsTouchEvent:new()
	return self
end
-- 检查触碰
function clsTouchListener:onTouch(x,y,type)
	if self.widget:visible() and self.widget.active and  x > self.widget:x() - self.widget:ox() and x < self.widget:x() + self.widget:width() - self.widget:ox() and y > self.widget:y() - self.widget:oy() and y < self.widget:y() + self.widget:height() - self.widget:oy() then
		x = x - self.widget:x() + self.widget:ox()
		y = y - self.widget:y() + self.widget:oy()
		self.event:setup(x,y,type)
		return true
	else
		self.event:setup(-1,-1,-1)
		return false
	end
end
