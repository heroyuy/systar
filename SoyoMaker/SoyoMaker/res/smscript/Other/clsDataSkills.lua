-- 用于封装技能哈希表为只读表的类
clsDataSkills = {}
clsDataSkills.__index = clsDataSkills
clsDataSkills.__newindex = function (t,k,v) end
-- 构造体
function clsDataSkills:new(t)
	for k,v in pairs(t) do
		clsDataSkills[k] = v
	end
	local self = {}
	setmetatable(self,clsDataSkills)
	return self
end