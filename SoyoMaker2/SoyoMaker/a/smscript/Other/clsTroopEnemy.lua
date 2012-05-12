-- 敌人队伍中的敌人类，此类在clsEnemyTroop类内部使用
clsTroopEnemy = {}
clsTroopEnemy.__index = clsTroopEnemy
-- 构造体
function clsTroopEnemy:new()
  local self = {}
  setmetatable(self,clsTroopEnemy)
  self.EnemyIndex = -1 -- 敌人编号
  self.x = -1 -- 相对x坐标
  self.y = -1 -- 相对y坐标
  return self
end
