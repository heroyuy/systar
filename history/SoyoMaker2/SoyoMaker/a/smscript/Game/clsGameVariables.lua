clsGameVariales = {}
clsGameVariales.__index = clsGameVariales
-- 构造体
function clsGameVariales:new()
	local self = {}
	setmetatable(self,clsGameVariales)
	self.reader = {data = {}}
	for i = 1,5000 do
		self.reader.data[i] = 0
	end
end
-- 设置变量
function clsGameVariales:set(variableIndex,value)
	if self.reader.data[variableIndex] ~= nil and type(value) == "number" then
		self.reader.data[variableIndex] = value
	end
end
-- 获取变量
function clsGameVariales:get(variableIndex)
	return self.reader.data[variableIndex]
end