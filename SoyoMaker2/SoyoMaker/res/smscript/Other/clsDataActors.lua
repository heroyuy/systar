-- 用于封装角色哈希表为只读表用的类
clsDataActors = {}
clsDataActors.__index = clsDataActors
clsDataActors.__newindex = function (t,k,v) end
-- 构造体
function clsDataActors:new(t)
	for k,v in pairs(t) do
		clsGameActors[k] = v
	end
	local self = {}
	setmetatable(self,clsDataActors)
	return self
end