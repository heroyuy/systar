-- 技能的资料类
clsSkill = {}
setmetatable(clsSkill,clsUsableItem)
clsSkill.__index = clsSkill
-- 构造体
function clsSkill:new()
  local self = {}
  self = clsUsableItem:new()
  self.setupf = self.setup
  setmetatable(self,clsSkill)
  self.target = 3
  return self
end
-- 对一些默认的属性赋值
function clsSkill:setup()
  self:setupf()
  self.spCost = self.costs[const.SKILL_COST_SP]
  self.hit = self.factors[const.SKILL_FACTOR_HIT]
end
