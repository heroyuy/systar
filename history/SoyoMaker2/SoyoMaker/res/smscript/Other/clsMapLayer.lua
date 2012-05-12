clsMapLayer = {}
clsMapLayer.__index = clsMapLayer
function clsMapLayer:new()
	local self = {}
	setmetatable(self,clsMapLayer)
	self.deepth = 0
	self.data = {}
	return self
end