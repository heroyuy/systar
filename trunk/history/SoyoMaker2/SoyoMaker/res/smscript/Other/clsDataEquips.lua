-- 用于封装装备哈希表为只读表的类
clsDataEquips = {}
clsDataEquips.__index = clsDataEquips
clsDataEquips.__newindex = function (t,k,v) end
-- 构造体
function clsDataEquips:new(t)
	for k,v in pairs(t) do
		clsDataEquips[k] = v
	end
	local self = {}
	setmetatable(self,clsDataEquips)
	return self
end