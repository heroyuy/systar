clsMapLayerData = {}
clsMapLayerData.__index = clsMapLayerData
function clsMapLayerData:new()
	local self = {}
	setmetatable(self,clsMapLayerData)
	self.id = -1
	self.index = -1
	return self
end