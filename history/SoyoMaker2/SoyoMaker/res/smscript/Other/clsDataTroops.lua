-- 用于封装敌人队伍哈希表为只读表的类
clsDataTroops = {}
clsDataTroops.__index = clsDataTroops
clsDataTroops.__newindex = function (t,k,v) end
-- 构造体
function clsDataTroops:new(t)
	for k,v in pairs(t) do
		clsDataTroops[k] = v
	end
	local self = {}
	setmetatable(self,clsDataTroops)
	return self
end