clsGameCharacter = {}
clsGameCharacter.__index = clsGameCharacter
function clsGameCharacter:new()
	local self = {}
	setmetatable(self,clsGameCharacter)
	self.realX = 0
	self.realY = 0
	self.x = 0
	self.y = 0
	self.face = 0
	self.characterName = ""
	self.jumpCount = 0
	self.moveRoute = {}
	self.moveRouteIndex = 0
	self.stepIndex = 0
	return self
end
-- 设置位置
function clsGameCharacter:position(ox,oy)
	self.x = self.realX - ox + globalGameEngine:getWidth()/2
	self.y = self.realY - oy + globalGameEngine:getHeight()/2
end
-- 设置事件
function clsGameCharacter:setNpc(npc)
	self.npc = npc
	self.npc:setCurStateIndex(0)
	self.realX = (self.npc:getRow() + 0.5) * globalGameMap.cellWidth
	self.realY = (self.npc:getCol() + 0.5) * globalGameMap.cellHeight 
	self.characterName = self.npc:getCharImage()
	self.face = self.npc:face() % 4
	self.moveType = self.npc:moveType()
	self.speed = 0
	self.penetrable = self.npc:penetrable()
end
-- 随机移动
function clsGameCharacter:moveTypeRandom()
	if #self.moveRoute ~= 0 then
		for i = 1,#self.moveRoute do
			self.moveRoute[i] = -1
		end
	end
	local face = smRandom:nextInt(100)%4
	if face == 0 then
		if globalGameMap.map:penetrable(self:row() - 1,self:col()) ~= -1 and self:row() > 1 then
			self.moveRoute[1] = 0
			self.stepIndex = 0
			self.moveRouteIndex = 1
		else
			self.moveRouteIndex = -1 
		end
	elseif face == 1 then
		if globalGameMap.map:penetrable(self:row() + 1,self:col()) ~= -1 then
			self.moveRoute[1] = 1
			self.stepIndex = 0
			self.moveRouteIndex = 1
		else
			self.moveRouteIndex = -1 
		end
	elseif face == 2 then
		if globalGameMap.map:penetrable(self:row(),self:col() - 1) ~= -1  and self:col() > 1 then
			self.moveRoute[1] = 2
			self.stepIndex = 0
			self.moveRouteIndex = 1
		else
			self.moveRouteIndex = -1 
		end
	elseif face == 3 then
		if globalGameMap.map:penetrable(self:row(),self:col() + 1) ~= -1 then
			self.moveRoute[1] = 3
			self.stepIndex = 0
			self.moveRouteIndex = 1
		else
			self.moveRouteIndex = -1 
		end
	end
end
-- 自动寻路(也可用于指定路线)
function clsGameCharacter:moveTypeAuto(x,y)
	self.moveRoute = {}
	local starX,starY,endRow,endCol,starRow,starCol
	if globalGameEngine:getWidth()/2 <= self.realX and globalGameMap.map:getRowNum() * globalGameMap.cellWidth - globalGameEngine:getWidth()/2 >= self.realX then
		endRow = math.floor((x - globalGameEngine:getWidth()/2 + self.realX)/globalGameMap.cellWidth)
	elseif globalGameEngine:getWidth()/2 > self.realX then
		endRow = math.floor(x/globalGameMap.cellWidth)
	elseif globalGameMap.map:getRowNum() * globalGameMap.cellWidth - globalGameEngine:getWidth()/2 < self.realX then
		endRow = math.floor((globalGameMap.map:getRowNum() * globalGameMap.cellWidth - 2 * globalGameEngine:getWidth()/2 + x)/globalGameMap.cellWidth) 
	end
	if globalGameEngine:getHeight()/2 <= self.realY and globalGameMap.map:getColNum() * globalGameMap.cellHeight - globalGameEngine:getHeight()/2 >= self.realY then
		endCol = math.floor((y - globalGameEngine:getHeight()/2 + self.realY)/globalGameMap.cellHeight)
	elseif  globalGameEngine:getHeight()/2 > self.realY then
		endCol = math.floor(y/globalGameMap.cellHeight)
	elseif globalGameMap.map:getColNum() * globalGameMap.cellHeight - globalGameEngine:getHeight()/2 < self.realY then
		endCol = math.floor((globalGameMap.map:getColNum() * globalGameMap.cellHeight - 2 * globalGameEngine:getHeight()/2 + y)/globalGameMap.cellHeight) 
	end
	starRow = math.floor(self.realX/globalGameMap.cellWidth)
	starCol = math.floor(self.realY/globalGameMap.cellHeight)
	starX = starRow * globalGameMap.cellWidth + globalGameMap.cellWidth / 2
	starY = starCol * globalGameMap.cellHeight + globalGameMap.cellHeight / 2
	globalAStar:searchDirections(starRow, starCol, endRow, endCol)
	for i = 1,globalAStar:getLength() do
		self.moveRoute[i] = globalAStar:getDirections(i - 1)
	end
	if self.realX ~= starX or self.realY ~= starY then
		self.moveRouteIndex = 0
		if self.moveRoute[1] == 0 then
			if self.realX < starX then
				self.moveRouteIndex = 1
				self.stepIndex = (starX - self.realX)/(globalGameMap.cellWidth/globalGameMap.step)
			else
				self.moveRouteIndex = 0
			end
		elseif self.moveRoute[1] == 1 then
			if self.realX > starX then
				self.moveRouteIndex = 1
				self.stepIndex = (self.realX - starX)/(globalGameMap.cellWidth/globalGameMap.step)
			else
				self.moveRouteIndex = 0
			end
		elseif self.moveRoute[1] == 2 then
			if self.realY < starY then
				self.moveRouteIndex = 1
				self.stepIndex = (starY - self.realY)/(globalGameMap.cellHeight/globalGameMap.step)
			else
				self.moveRouteIndex = 0
			end
		elseif self.moveRoute[1] == 3 then
			if self.realY > starY then
				self.moveRouteIndex = 1
				self.stepIndex = (self.realY - starY)/(globalGameMap.cellHeight/globalGameMap.step)
			else
				self.moveRouteIndex = 0
			end
		end
	elseif  self.realX == starX and self.realY == starY then
		self.moveRouteIndex = 1
		self.stepIndex = 0
	end
end
-- 位置校正
function clsGameCharacter:straighten()
	if self.realX > self:row() * globalGameMap.cellWidth + globalGameMap.cellWidth / 2 then
		self.face = 0
		self.realX = self.realX - globalGameMap.cellWidth / globalGameMap.step
		return false
	end
	if self.realX < self:row() * globalGameMap.cellWidth + globalGameMap.cellWidth / 2 then
		self.face = 1
		self.realX = self.realX + globalGameMap.cellWidth / globalGameMap.step
		return false
	end
	if self.realY > self:col() * globalGameMap.cellHeight + globalGameMap.cellHeight / 2 then
		self.face = 2
		self.realY = self.realY - globalGameMap.cellHeight / globalGameMap.step
		return false
	end
	if self.realY < self:col() * globalGameMap.cellHeight + globalGameMap.cellHeight / 2 then
		self.face = 3
		self.realY = self.realY + globalGameMap.cellHeight / globalGameMap.step
		return false
	end
	if self.realX == self:row() * globalGameMap.cellWidth + globalGameMap.cellWidth / 2 and self.realY == self:col() * globalGameMap.cellHeight + globalGameMap.cellHeight / 2 then
		return true
	end
end
-- 向左移动
function clsGameCharacter:moveRight()
	--if self.stepIndex == 0 or self.stepIndex == globalGameMap.step / 2 - 1 then
		if self:isPrevent() then
			self.moveRouteIndex = #self.moveRoute + 1
			return
		end
	--end
	self.realX = self.realX - globalGameMap.cellWidth / globalGameMap.step
	self.stepIndex = self.stepIndex + 1
	if self.stepIndex == globalGameMap.step then
		self.stepIndex = 0
		self.moveRouteIndex = self.moveRouteIndex + 1
	end
end
-- 向右移动
function clsGameCharacter:moveLeft()
	--if self.stepIndex == 0 or self.stepIndex == globalGameMap.step / 2 - 1 then
		if self:isPrevent() then
			self.moveRouteIndex = #self.moveRoute + 1
			return
		end
	--end
	self.realX = self.realX + globalGameMap.cellWidth / globalGameMap.step
	self.stepIndex = self.stepIndex + 1
	if self.stepIndex == globalGameMap.step then
		self.stepIndex = 0
		self.moveRouteIndex = self.moveRouteIndex + 1
	end
end
-- 向上移动
function clsGameCharacter:moveUp()
	--if self.stepIndex == 0 or self.stepIndex == globalGameMap.step / 2 - 1 then
		if self:isPrevent() then
			self.moveRouteIndex = #self.moveRoute + 1
			return
		end
	--end
	self.realY = self.realY - globalGameMap.cellHeight / globalGameMap.step
	self.stepIndex = self.stepIndex + 1
	if self.stepIndex == globalGameMap.step then
		self.stepIndex = 0
		self.moveRouteIndex = self.moveRouteIndex + 1
	end
end
-- 向下移动
function clsGameCharacter:moveDown()
	--if self.stepIndex == 0 or self.stepIndex == globalGameMap.step / 2 - 1 then
		if self:isPrevent() then
			self.moveRouteIndex = #self.moveRoute + 1
			return
		end
	--end
	self.realY = self.realY + globalGameMap.cellHeight / globalGameMap.step
	self.stepIndex = self.stepIndex + 1
	if self.stepIndex == globalGameMap.step then
		self.stepIndex = 0
		self.moveRouteIndex = self.moveRouteIndex + 1
	end
end
-- 判断是否移动
-- 如果在移动中理论坐标与实际坐标不同
function clsGameCharacter:isMoving()
	return (self.realX - globalGameMap.cellWidth / 2) % globalGameMap.cellWidth ~= 0 or (self.realY - globalGameMap.cellHeight / 2) % globalGameMap.cellHeight ~= 0
end
-- 判断是否跳跃
function clsGameCharacter:isJumping()
	return self.jumpCount > 0
end
-- 判断是否停止
function clsGameCharacter:isStoping()
	return (not (self:isMoving() or self:isJumping()))
end
-- 强制行动路线
function clsGameCharacter:forceMoveRoute(moveRoute)

end
-- 是否被阻挡
function clsGameCharacter:isPrevent()
	if self:row() == globalGamePlayer:row() + 1 and self:col() == globalGamePlayer:col() then
		return true
	end
	if self:row() == globalGamePlayer:row() - 1 and self:col() == globalGamePlayer:col() then
		return true
	end
	if self:col() == globalGamePlayer:col() + 1 and self:row() == globalGamePlayer:row() then
		return true
	end
	if self:col() == globalGamePlayer:col() - 1 and self:row() == globalGamePlayer:row() then
		return true
	end
	return false
end
-- 获取行数
function clsGameCharacter:row()
	return math.floor(self.realX/globalGameMap.cellWidth)
end
-- 获取列数
function clsGameCharacter:col()
	return math.floor(self.realY/globalGameMap.cellHeight)
end
-- 更新
function clsGameCharacter:refresh()
	if self.moveRouteIndex ~= -1 then
		if self.moveRouteIndex == 0 then
			if self:straighten() then
				self.moveRouteIndex = 1
			end
		elseif self.moveRouteIndex > 0 and self.moveRouteIndex <= #self.moveRoute then
			self.face = self.moveRoute[self.moveRouteIndex]
			if self.face == 0 then
				self:moveRight()
			elseif self.face == 1 then
				self:moveLeft()
			elseif self.face == 2 then
				self:moveUp()
			elseif self.face == 3 then
				self:moveDown()
			end
		elseif self.moveRouteIndex > #self.moveRoute then
			if self:straighten() then
				self.moveRouteIndex = -1
			end
		end
	end
	if self.moveRouteIndex == -1 then
		if self.speed % (13 - self.npc:speed() + 90) == 0 then
			if not self:isPrevent() then
				self:moveTypeRandom()
			end
		end
	end
	self.speed = self.speed + 1
end