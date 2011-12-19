clsMapImageSet = {}
clsMapImageSet.__index = clsMapImageSet
function clsMapImageSet:new()
	local self = {}
	setmetatable(self,clsMapImageSet)
	self.index = -1
	self.path = ""
	return self
end