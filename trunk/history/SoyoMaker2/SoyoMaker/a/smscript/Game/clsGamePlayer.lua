clsGamePlayer = {}
setmetatable(clsGamePlayer,clsGameCharacter)
clsGamePlayer.__index = clsGamePlayer
function clsGamePlayer:new()
	local self = {}
	self = clsGameCharacter:new()
	setmetatable(self,clsGamePlayer)
	self.characterName = ".\\game\\image\\character\\$姜维.png\\"
	self.face = 3
	return self
end
function clsGamePlayer:moveTo(mapIndex,x,y)
	self.realX = x
	self.realY = y
	globalGameMap:setup(mapIndex)
end
-- 更新
function clsGamePlayer:refresh()
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
end
-- 设置位置
function clsGamePlayer:position()
	if self.realX - globalGameMap.displayX + globalGameEngine:getWidth()/2 > 0 then
		self.x = self.realX - globalGameMap.displayX + globalGameEngine:getWidth()/2
	end
	if self.realY - globalGameMap.displayY + globalGameEngine:getHeight()/2 > 0 then
		self.y = self.realY - globalGameMap.displayY + globalGameEngine:getHeight()/2
	end
end
-- 是否被阻挡
function clsGamePlayer:isPrevent()
	if self.face == 0 then
		for _,v in pairs(globalGameMap.npcs) do
			if self:row() == v:row() + 1 and self:col() == v:col() then
				return true
			end
		end
	elseif self.face == 1 then
		for _,v in pairs(globalGameMap.npcs) do
			if self:row() == v:row() - 1 and self:col() == v:col() then
				return true
			end
		end
	elseif self.face == 2 then
		for _,v in pairs(globalGameMap.npcs) do
			if self:col() == v:col() + 1 and self:row() == v:row() then
				return true
			end
		end
	elseif self.face == 3 then
		for _,v in pairs(globalGameMap.npcs) do
			if self:col() == v:col() - 1 and self:row() == v:row() then
				return true
			end
		end
	end
	return false
end