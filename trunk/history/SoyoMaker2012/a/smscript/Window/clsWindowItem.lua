clsWindowItem = {}
setmetatable(clsWindowItem,clsWindowUsable)
clsWindowItem.__index = clsWindowItem
function clsWindowItem:new(viewport,x,y,width,height,spacing,itemHeight)
	local self = {}
	self = clsWindowUsable:new(viewport,x,y,width,height,spacing,itemHeight)
	setmetatable(self,clsWindowItem)
	self.items = globalGameParty:items()
	self.helpWindow:drawIntro(self.items[self.cursorIndex + 1],16,21)
	self:refresh()
	return self
end
-- 更新内容
function clsWindowItem:refresh()
	self:clear()
	for j = 0,self.itemMax - 1 do
		for i = 0,self.columnMax - 1 do
			if self.curIndex + i + j*self.columnMax + 1 <= #self.items and self.curIndex + i + j*self.columnMax >= 0 then
				if self.items[self.curIndex + i + j*self.columnMax + 1] ~= nil then
					self:drawItemName(self.items[self.curIndex + i + j*self.columnMax + 1],20 + i * (self.spacing + self.itemWidth),20 + j*self.itemHeight,self.spacing + self.itemWidth)
				end
			end
		end
	end
end
-- 获取物品
function clsWindowItem:item()
	return self.items[self.cursorIndex + 1]
end
-------------------
function clsWindowItem:naicet()
	return "clsWindowItem"
end