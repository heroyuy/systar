clsMap = {}
clsMap.__index = clsMap
function clsMap:new()
	local self = {}
	setmetatable(self,clsMap)
	self.index = -1
	self.name = ""
	self.musicName = ""
	self.battleBack = ""
	self.battleMusic = ""
	self.rowNum = 0
	self.colNum = 0
	self.cellWidth = 0
	self.cellHeight = 0
	self.imageSet = {}
	self.layer = {}
	self.area = {}
	self.npcIndex = {}
	return self
end