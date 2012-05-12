-- 命令窗体，点击即可以确定命令。
clsWindowCommand = {}
setmetatable(clsWindowCommand,clsWindowSelect)
clsWindowCommand.__index = clsWindowCommand
function clsWindowCommand:new(viewport,width,commands,columnMax,rowMax,spacing,itemHeight,windowType)
	local self = {}
	if rowMax == 0 then
		rowMax = math.floor((#commands + columnMax)/columnMax)
	end
	self = clsWindowSelect:new(viewport,0,0,width,rowMax*itemHeight + 32,spacing,itemHeight) 
	setmetatable(self,clsWindowCommand)
	if windowType == nil then
		self.type = 1
	else
		self.type = windowType
	end
	self.items = commands
	self.columnMax = columnMax
	return self
end
-- 设置
function clsWindowCommand:setup()
	self.itemMax = math.floor((self:height() - 32)/self.itemHeight)    -- 最大选项数
	self.itemWidth = (self:width() - 32 - (self.columnMax - 1) * self.spacing)/self.columnMax
	self:cursorRect():set(16,16,self.itemWidth,self.itemHeight)
	self:refresh()
end
-- 更新内容
function clsWindowCommand:refresh()
	self:clear()
	self.contents:setTextSize(14)
	self.contents:setColor(self:normalColor())
	for j = 0,self.itemMax - 1 do
		for i = 0,self.columnMax - 1 do
			if self.curIndex >= 0 and self.curIndex <= #self.items + self.columnMax - self.itemMax*self.columnMax then
				if self.curIndex + i + j*self.columnMax <= #self.items and self.curIndex + i + j*self.columnMax >= 0 then
					if self.items[self.curIndex + i + j*self.columnMax] ~= nil then
						self.contents:drawString(self.items[self.curIndex + i + j*self.columnMax],16 + i * (self.spacing + self.itemWidth) + self.itemWidth / 2,21 + j*self.itemHeight,3)
					end
				end
			end
		end
	end
end
-- 处理触发
function clsWindowCommand:dispatchTouchEvent()
	if self.touchListener ~= nil and self.active and self.touchListener.event.type ~= -1 and self.active then
		if self.touchListener.event.x > 16 and self.touchListener.event.x < self:width() - 16 then
			if self.touchListener.event.y > 16 and self.touchListener.event.y < self:height() - 16 then
				if self.touchListener.event.type == 0 then
					if self.index == -1 and self.cursorIndex == self:col(self.touchListener.event.x)+ self:row(self.touchListener.event.y) * self.columnMax+ self.curIndex and self.type == 2 then
						self.index = self.cursorIndex
					end
					self.typeZeroY = self.touchListener.event.y
					self.originalIndex = self.curIndex
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					if self.index == -1 and self.type == 1 then
						self.index = self.cursorIndex
					end
					self.touchListener.event:setup(-1,-1,-1)
				end
				if self.touchListener.event.type == 1 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.tmpIndex = self.originalIndex - (self:row(self.touchListener.event.y) - self:row(self.typeZeroY)) * self.columnMax
					if(self:row(self.typeZeroY) ~= self:row(self.touchListener.event.y)) then
						if self.tmpIndex >= 0 then 
							if self.tmpIndex <= #self.items + self.columnMax - self.itemMax*self.columnMax then
								if self.curIndex ~= self.tmpIndex then
									self.curIndex = self.tmpIndex
									self:refresh()
								end
							end
						end
					end
					self.touchListener.event:setup(-1,-1,-1)
				end
				if self.touchListener.event.type == 2 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.typeZeroY = -1
					self.originalIndex = 0
					self.touchListener.event:setup(-1,-1,-1)
				end				
			end
		end
	end	
end