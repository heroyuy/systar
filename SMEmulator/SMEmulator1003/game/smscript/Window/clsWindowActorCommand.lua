clsWindowActorCommand = {}
setmetatable(clsWindowActorCommand,clsWindowSelect)
clsWindowActorCommand.__index = clsWindowActorCommand
function clsWindowActorCommand:new(viewport,width,rowMax,spacing,itemHeight)
	local self = {}
	if rowMax == 0 then
		rowMax = math.floor((#commands + columnMax)/columnMax)
	end
	self = clsWindowSelect:new(viewport,0,0,width,rowMax*itemHeight + 32,spacing,itemHeight)
	self.setVisibleF = self.setVisible
	self.setXF = self.setX
	self.setYF = self.setY
	self.updateF = self.update
	self.setTouchListenerF = self.setTouchListener
	self.items = globalGameParty:members()
	self.commands = {[0] = "确定",[1] = "取消"}
	self.commandWindow = clsWindowCommand:new(viewport,width,self.commands,2,0,32,24)
	self.commandWindow:setup()
	setmetatable(self,clsWindowActorCommand)
	return self
end
-- 设置横坐标
function clsWindowActorCommand:setX(x)
	self:setXF(x)
	self.commandWindow:setX(x)
end
-- 设置纵坐标
function clsWindowActorCommand:setY(y)
	self:setYF(y)
	self.commandWindow:setY(y + self:height())
end
-- 设置活动性
function clsWindowActorCommand:setActive(windowActive)
	self.active = windowActive
	self.commandWindow.active = windowActive
end
-- 甚至侦听器
function clsWindowActorCommand:setTouchListener()
	self:setTouchListenerF()
	self.commandWindow:setTouchListener()
end
-- 设置可见度
function clsWindowActorCommand:setVisible(visible)
	self:setVisibleF(visible)
	self.commandWindow:setVisible(visible)
end
-- 获取物品
function clsWindowActorCommand:item()
	return self.items[self.cursorIndex + 1]
end
-- 更新
function clsWindowActorCommand:update()
	self:updateF()
	self.commandWindow:update()
end
-- 更新内容
function clsWindowActorCommand:refresh()
	self:clear()
	for j = 0,self.itemMax - 1 do
		for i = 0,self.columnMax - 1 do
			if self.curIndex >= 0 and self.curIndex + 1 <= #self.items + self.columnMax - self.itemMax*self.columnMax then
				if self.curIndex + i + j*self.columnMax + 1 <= #self.items and self.curIndex + i + j*self.columnMax >= 0 then
					if self.items[self.curIndex + i + j*self.columnMax+1] ~= nil then	
						self:drawActorName(self.items[self.curIndex + i + j*self.columnMax+1], 124, 20 + j * self.itemHeight)
						self:drawActorFace(self.items[self.curIndex + i + j*self.columnMax+1], 20, 20 + j * self.itemHeight)
						self:drawActorHp(self.items[self.curIndex + i + j*self.columnMax+1],124,20+20+1 + j * self.itemHeight)
						self:drawActorSp(self.items[self.curIndex + i + j*self.columnMax+1],124,20+20+16+20+2 + j * self.itemHeight)
					end
				end
			end
		end
	end
end
-- 处理触发
function clsWindowActorCommand:dispatchTouchEvent()
	if self.touchListener ~= nil and self.active and self.touchListener.event.type ~= -1 then
		if self.touchListener.event.x > 16 and self.touchListener.event.x < self:width() - 16 then
			if self.touchListener.event.y > 16 and self.touchListener.event.y < self:height() - 16 then
				if self.touchListener.event.type == 0 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.typeZeroY = self.touchListener.event.y
					self.originalIndex = self.curIndex
				end
				if self.touchListener.event.type == 1 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.tmpIndex = self.originalIndex - (self:row(self.touchListener.event.y) - self:row(self.typeZeroY)) * self.columnMax
					if(self:row(self.typeZeroY) ~= self:row(self.touchListener.event.y)) then
						if self.tmpIndex >= 0 then 
							if self.tmpIndex + 1 <= #self.items + self.columnMax - self.itemMax*self.columnMax  then
								if self.curIndex ~= self.tmpIndex then
									self.curIndex = self.tmpIndex
									self:refresh()
								end
							end
						end
					end
				end
				if self.touchListener.event.type == 2 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.typeZeroY = -1
					self.originalIndex = 0
				end
			end
		end
	end	
end
----------------------
function clsWindowActorCommand:naicet()
	return "clsWindowActorCommand"
end