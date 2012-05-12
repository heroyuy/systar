clsBuffRemoveCondition = {}
clsBuffRemoveCondition.__index = clsBuffRemoveCondition
function clsBuffRemoveCondition:new()
	local self = {}
	setmetatable(self,clsBuffRemoveCondition)
	self.conditionType = -1
	self.lastParam = -1
	self.param = -1
	return self
end