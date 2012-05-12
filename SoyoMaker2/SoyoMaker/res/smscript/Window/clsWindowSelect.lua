clsWindowSelect = {}
setmetatable(clsWindowSelect,clsWindowBase)
clsWindowSelect.__index = clsWindowSelect
function clsWindowSelect:new(viewport,x,y,width,height,spacing,itemHeight)
	local self = {}
	self = clsWindowBase:new(viewport,x,y,width,height) 
	self.paintf = self.paint
	setmetatable(self,clsWindowSelect)
	self.itemHeight = itemHeight                    -- 行高
	self.spacing = spacing                          -- 列距
	self.columnMax = 1   							-- 最大列数
    self.index = -1       							-- 索引
    self.cursorIndex = 0                            -- 光标位置
    self.originalIndex = 0                          -- 首行记录
    self.curIndex = 0                               -- 当前索引
    self.typeZeroY = -1
    self.items = {}
    self.tmpIndex = 0
    self.helpWindow = nil
    for i = 0,20 do
    	self.items[i] = i.."          ".."物品项目"
    end
	return self
end
-- 设置
function clsWindowSelect:setup(columnMax)
	self.columnMax = columnMax   						   -- 最大列数
	self.itemMax = math.floor((self:height() - 32)/self.itemHeight)    -- 最大选项数
	self.itemWidth = (self:width() - 32 - (self.columnMax - 1) * self.spacing)/self.columnMax
	self:cursorRect():set(16,16,self.itemWidth,self.itemHeight)
	self:refresh()
end
-- 获取行
function clsWindowSelect:row(y)
	return math.floor((y - 16)/self.itemHeight)
end
-- 获取列
function clsWindowSelect:col(x)
	return math.floor((x - 16)/(self.itemWidth+self.spacing))
end
-- 设置光标
function clsWindowSelect:setCursor(x,y)
	if self:col(x)+ self:row(y)  * self.columnMax >= 0 then
		self.cursorIndex = self:col(x)+ self:row(y)  * self.columnMax+ self.curIndex
		self:cursorRect():set(16 + self:col(x)*(self.itemWidth+self.spacing),16 + self:row(y) * self.itemHeight,self.itemWidth,self.itemHeight)
	end
end
-- 更新内容
function clsWindowSelect:refresh()
	self:clear()
	self.contents:setColor(smColor:getColor(255, 0,0, 255))
	for j = 0,self.itemMax - 1 do
		for i = 0,self.columnMax - 1 do
			if self.curIndex >= 0 and self.curIndex <= #self.items + self.columnMax - self.itemMax*self.columnMax then
				if self.curIndex + i + j*self.columnMax <= #self.items and self.curIndex + i + j*self.columnMax >= 0 then
					if self.items[self.curIndex + i + j*self.columnMax] ~= nil then
						self.contents:drawString(self.items[self.curIndex + i + j*self.columnMax],20 + i * (self.spacing + self.itemWidth),24 + j*24,0)
					end
				end
			end
		end
	end
end
-- 更新
function clsWindowSelect:update()
	self:dispatchTouchEvent()
end
-- 处理触发
function clsWindowSelect:dispatchTouchEvent()
	if self.touchListener ~= nil and self.active and self.touchListener.event.type ~= -1 then
		if self.touchListener.event.x > 16 and self.touchListener.event.x < self:width() - 16 then
			if self.touchListener.event.y > 16 and self.touchListener.event.y < self:height() - 16 then
				if self.touchListener.event.type == 0 then
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
					self.typeZeroY = self.touchListener.event.y
					self.originalIndex = self.curIndex
				end
				if self.touchListener.event.type == 1 then
					self.tmpIndex = self.originalIndex - (self:row(self.touchListener.event.y) - self:row(self.typeZeroY)) * self.columnMax
					if(self:row(self.typeZeroY) ~= self:row(self.touchListener.event.y)) then
						if self.tmpIndex >= 0 then 
							if self.tmpIndex <= #self.items + self.columnMax - self.itemMax*self.columnMax  then
								if self.curIndex ~= self.tmpIndex then
									self.curIndex = self.tmpIndex
									self:refresh()
								end
							end
						end
					end
					self:setCursor(self.touchListener.event.x,self.touchListener.event.y)
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
