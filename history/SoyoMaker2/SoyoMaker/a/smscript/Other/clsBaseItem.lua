-- 技能、物品、武器和防具的超类
clsBaseItem = {}
clsBaseItem.__index = clsBaseItem
-- 构造体
function clsBaseItem:new()
  local self = {}
  setmetatable(self,clsBaseItem)
  self.index = 0
  self.name = ""
  self.icon = 0
  self.intro = ""
  self.lev = 0
  return self
end
