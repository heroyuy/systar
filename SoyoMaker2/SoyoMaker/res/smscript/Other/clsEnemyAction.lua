-- 敌人会使用的动作，此类在敌人数据类中使用
clsEnemyAction = {}
clsEnemyAction.__index = clsEnemyAction
-- 构造体
function clsEnemyAction:new()
  local self = {}
  setmetatable(self,clsEnemyAction)
  self.conditions = {}
  self.actionType = -1
  self.paramList = {}
  self.rate = 0
  return self
end
