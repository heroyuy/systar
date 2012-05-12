-- 用于封装敌人哈希表为只读表的类
clsDataEnemys = {}
clsDataEnemys.__index = clsDataEnemys
clsDataEnemys.__newindex = function (t,k,v) end
-- 构造体
function clsDataEnemys:new(t)
	for k,v in pairs(t) do
		clsDataEnemys[k] = v
	end
	local self = {}
	setmetatable(self,clsDataEnemys)
	return self
end