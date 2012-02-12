-- 用于封装系统为只读类的类
clsDataSystem = {}
clsDataSystem.__index = clsDataSystem
clsDataSystem.__newindex = function (t,k,v) end
-- 构造体
function clsDataSystem:new(t)
	for k,v in pairs(t) do
		clsDataSystem[k] = v
	end
	local self = {}
	setmetatable(self,clsDataSystem)
	return self
end