-- 用于封装状态哈希表为只读表的类
clsDataBuffs = {}
clsDataBuffs.__index = clsDataBuffs
clsDataBuffs.__newindex = function (t,k,v) end
-- 构造体
function clsDataBuffs:new(t)
	for k,v in pairs(t) do
		clsDataBuffs[k] = v
	end
	local self = {}
	setmetatable(self,clsDataBuffs)
	return self
end