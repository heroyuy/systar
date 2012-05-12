clsSpriteCharacter = {}
setmetatable(clsSpriteCharacter,clsSpriteBase)
clsSpriteCharacter.__index = clsSpriteCharacter
-- 构造体
function clsSpriteCharacter:new(viewport,character)
	local self = {}
	self = clsSpriteBase:new(viewport)
	setmetatable(self,clsSpriteCharacter)
	self.character = character
	self.characterName = ""
	return self
end
-- 更新
function clsSpriteCharacter:update()
	self:updateImage()
	self:updateSrcRect()
	self:setX(self.character.x)
	self:setY(self.character.y)
end

-- 更新传送源的位图
function clsSpriteCharacter:updateImage()
	if self.characterName ~= self.character.characterName then
		self.characterName = self.character.characterName
		self:setImage(smImage:createImage(self.characterName))
		self:srcRect():set(self:action(),self:face(),self:image():getWidth()/3,self:image():getHeight()/4)
		self:setOx(self:width()/2)
		self:setOy(self:height()/2)
		self:setVisible(true)
	end
end
-- 更新方块
function clsSpriteCharacter:updateSrcRect()
	self:srcRect():set(self:action(),self:face(),self:image():getWidth()/3,self:image():getHeight()/4)
end
-- 取得动作
function clsSpriteCharacter:action()
	if self.character.face == 0 or self.character.face == 1 then
		if self.character.realX / (globalGameMap.cellWidth/globalGameMap.step) % 4 == 0 then
			return 32
		end
		if self.character.realX / (globalGameMap.cellWidth/globalGameMap.step) % 4 == 1 then
			return 0
		end
		if self.character.realX / (globalGameMap.cellWidth/globalGameMap.step) % 4 == 2 then
			return 32
		end
		if self.character.realX / (globalGameMap.cellWidth/globalGameMap.step) % 4 == 3 then
			return 64
		end
	end
	if self.character.face == 3 or self.character.face == 2 then
		if self.character.realY / (globalGameMap.cellHeight/globalGameMap.step) % 4 == 0 then
			return 32
		end
		if self.character.realY / (globalGameMap.cellHeight/globalGameMap.step) % 4 == 1 then
			return 0
		end
		if self.character.realY / (globalGameMap.cellHeight/globalGameMap.step) % 4 == 2 then
			return 32
		end
		if self.character.realY / (globalGameMap.cellHeight/globalGameMap.step) % 4 == 3 then
			return 64
		end
	end
end
-- 取得朝向
function clsSpriteCharacter:face()
	if self.character.face == 0 then
		return 32
	elseif self.character.face == 1 then
		return 64
	elseif self.character.face == 2 then
		return 96
	else 
		return 0
	end
end