-- 敌人队伍数据类
clsTroop = {}
clsTroop.__index = clsTroop
-- 构造体
function clsTroop:new()
  local self = {}
  setmetatable(self,clsTroop)
  self.index = 0
  self.name = ""
  self.enemys = {}  -- 敌人位置->敌人内部类
  self.events = {}
  return self
end
