-- 存储所有角色的类，这个类的实例请参考globalGameActors。
clsGameActors = {}
clsGameActors.__index = clsGameActors
-- 构造体
function clsGameActors:new()
	local self = {}
	setmetatable(self,clsGameActors)
	self.reader = {data = {}}
	return self
end
-- 获取角色
function clsGameActors:get(actorIndex)
	if self.reader.data[actorIndex] == nil and globalDataManager:getModel(const.DATA_MANAGER_ACTOR,actorIndex) ~= nil then
		self.reader.data[actorIndex] = clsGameActor:new(actorIndex)
	end
	return self.reader.data[actorIndex]
end