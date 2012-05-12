-- 方块类
clsRect = {}
clsRect.__index = clsRect
-- 构造体
function clsRect:new(x,y,width,height)
	local self = {}
	setmetatable(self,clsRect)
	self.rect = smRect
	self.rect:set(x,y,width,height)
	return self
end
-- 设置方块体
function clsRect:set(x,y,width,height)
	self.rect:set(x,y,width,height)
end