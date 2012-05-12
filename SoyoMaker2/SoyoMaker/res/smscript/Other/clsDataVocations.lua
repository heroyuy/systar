-- 用于封装职业哈希表为只读表的类
clsDataVocations = {}
clsDataVocations.__index = clsDataVocations
clsDataVocations.__newindex = function (t,k,v) end
-- 构造体
function clsDataVocations:new(t)
	for k,v in pairs(t) do
		clsDataVocations[k] = v
	end
	local self = {}
	setmetatable(self,clsDataVocations)
	return self
end