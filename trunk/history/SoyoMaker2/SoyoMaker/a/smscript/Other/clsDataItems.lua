-- 用于封装物品哈希表为只读表的类
clsDataItems = {}
clsDataItems.__index = clsDataItems
clsDataItems.__newindex = function (t,k,v) end
-- 构造体
function clsDataItems:new(t)
	for k,v in pairs(t) do
		clsDataItems[k] = v
	end
	local self = {}
	setmetatable(self,clsDataItems)
	return self
end