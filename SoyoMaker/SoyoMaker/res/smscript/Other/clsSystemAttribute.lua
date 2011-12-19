-- 数据库系统选项页的属性类，此类在clsSystem类内部使用
clsSystemAttribute = {}
clsSystemAttribute.__index = clsSystemAttribute
-- 构造体
function clsSystemAttribute:new()
  local self = {}
  setmetatable(self,clsSystemAttribute)
  self.index = 0
  self.name = ""
  self.desc = ""
  return self
end
