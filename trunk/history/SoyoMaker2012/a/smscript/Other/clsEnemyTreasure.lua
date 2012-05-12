-- 敌人会掉落的物品，此类在敌人数据类内部使用
clsEnemyTreasure = {}
clsEnemyTreasure.__index = clsEnemyTreasure
-- 构造体
function clsEnemyTreasure:new()
  local self = {}
  setmetatable(self,clsEnemyTreasure)
  self.index = -1
  self.type = -1
  self.rate = 0
  return self
end
