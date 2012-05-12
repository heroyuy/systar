clsBuffParam = {}
clsBuffParam.__index = clsBuffParam
function clsBuffParam:new()
	local self = {}
	setmetatable(self,clsBuffParam)
	self.attributeType = -1
	self.ruleType = -1
	self.paramValue = 0
	return self
end