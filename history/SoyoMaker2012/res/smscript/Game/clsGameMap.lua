clsGameMap = {}
clsGameMap.__index = clsGameMap
function clsGameMap:new()
	local self = {}
	setmetatable(self,clsGameMap)
	self.npcs = {}
	self.step = 4
	return self
end
-- 设置
function clsGameMap:setup(mapIndex)
	self.map = globalDataManager:getModel(const.DATA_MANAGER_MAP,mapIndex)
	globalAStar:setMapData(self.map:getWays())
	self.cellWidth = self.map:getCellWidth()
	self.cellHeight = self.map:getCellHeight()
	self:refresh()
	for i = 0,self.map:getRowNum() - 1 do
		for j = 0,self.map:getColNum() - 1 do
			if self.map:getNpc(i,j) ~= -1 then
				self.npcs[self.map:getNpc(i,j)] = clsGameCharacter:new()
				self.npcs[self.map:getNpc(i,j)]:setNpc(globalDataManager:getModel(const.DATA_MANAGER_NPC,self.map:getNpc(i,j)))
				self.npcs[self.map:getNpc(i,j)]:moveTypeRandom()
			end
		end
	end
end
-- 刷新
function clsGameMap:refresh()
	if globalGamePlayer.realX >= globalGameEngine:getWidth()/2 and globalGamePlayer.realX <= self.cellWidth * self.map:getRowNum() - globalGameEngine:getWidth()/2 then
		self.displayX = globalGamePlayer.realX
	elseif globalGamePlayer.realX < globalGameEngine:getWidth()/2 then
		self.displayX = globalGameEngine:getWidth()/2
	elseif globalGamePlayer.realX > self.cellWidth * self.map:getRowNum() - globalGameEngine:getWidth()/2 then
		self.displayX = self.cellWidth * self.map:getRowNum() - globalGameEngine:getWidth()/2
	end
	if globalGamePlayer.realY <= self.cellHeight * self.map:getColNum() - globalGameEngine:getHeight()/2 and globalGamePlayer.realY > globalGameEngine:getHeight()/2 then
		self.displayY = globalGamePlayer.realY
	elseif globalGamePlayer.realY < globalGameEngine:getHeight()/2 then
		self.displayY = globalGameEngine:getHeight()/2
	elseif globalGamePlayer.realY > self.cellHeight * self.map:getColNum() - globalGameEngine:getHeight()/2 then
		self.displayY = self.cellHeight * self.map:getColNum() - globalGameEngine:getHeight()/2
	end
	for _,v in pairs(self.npcs) do
		v:refresh()
		v:position(globalGameMap.displayX,globalGameMap.displayY)
	end
end
