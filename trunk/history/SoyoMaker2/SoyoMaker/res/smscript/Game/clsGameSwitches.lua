-- 处理开关的类。本类的实例请参考globalGameSwitches
clsGameSwitches = {}
clsGameSwitches.__index = clsGameSwitches
-- 构造体
function clsGameSwitches:new()
	local self = {}
	setmetatable(self,clsGameSwitches)
	self.reader = {data = {}}
	for i = 1,5000 do
		self.reader.data[i] = false
	end
	return self
end
-- 设置开关，必须开关存在并且输入值为布尔型
function clsGameSwitches:set(switchIndex,value)
	if self.reader.data[switchIndex] ~= nil and type(value) == "boolean" then
		self.reader.data[switchIndex] = value
	end
end
-- 获得开关值
function clsGameSwitches:get(switchIndex)
	return self.reader.data[switchIndex]
end