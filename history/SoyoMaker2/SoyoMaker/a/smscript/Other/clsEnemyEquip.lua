-- 敌人装备类，此类在敌人数据类内部使用
clsEnemyEquip = {}
clsEnemyEquip.__index = clsEnemyEquip
-- 构造体
function clsEnemyEquip:new()
  local self = {}
  setmetatable(self,clsEnemyEquip)
  self.equipIndex = -1
  self.kind = -1
  self.rate = 0
  return self
end
