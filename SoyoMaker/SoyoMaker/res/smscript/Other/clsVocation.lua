-- 职业的资料类
clsVocation = {}
clsVocation.__index = clsVocation
-- 构造体
function clsVocation:new()
  local self = {}
  setmetatable(self,clsVocation)
  self.index = 0
  self.name = ""
  self.equipList = {}
  self.itemList = {}
  self.skillList = {}
  self.attributes = {}
  self.buffs = {}
  return self
end
-- 对一些默认值赋值
function clsVocation:setup()
  self.twoSwordsStyle = (self.attributes[const.TWO_SWORDS_STYLE] ~= nil)
  self.fixEquipment = (self.attributes[const.FIX_EQUIPMENT] ~= nil)
  self.autoBattle = (self.attributes[const.AUTO_BATTLE] ~= nil)
  self.superGuard = (self.attributes[const.SUPER_GUARD] ~= nil)
  self.pharmacology = (self.attributes[const.PHARMACOLOGY] ~= nil)
  self.criticalBonus = (self.attributes[const.CRITICAL_BONUS] ~= nil)
end
