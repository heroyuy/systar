-- 敌人动作条件类，此类在敌人动作类内部使用
clsEnemyActionCondition = {}
clsEnemyActionCondition.__index = clsEnemyActionCondition
-- 构造体
function clsEnemyActionCondition:new()
  local self = {}
  setmetatable(self,clsEnemyActionCondition)
  self.conditionType = -1
  self.paramList = {} -- ID数组
  return self
end
