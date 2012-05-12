-- 物品的资料类
clsItem = {}
setmetatable(clsItem,clsUsableItem)
clsItem.__index = clsItem
-- 构造体
function clsItem:new()
  local self = {}
  self = clsUsableItem:new()
  self.setupf = self.setup
  setmetatable(self,clsItem)
  self.target = 1
  self.hit = 100
  self.price = 0
  return self
end
-- 对一些默认的属性赋值
function clsSkill:setup()
  self:setupf()
  self.consumable = self.costs[const.ITEM_COST_CONSUMABLE]
  self.hpRecoveryRate = self.effects[const.ITEM_EFFECT_HP_RECOVERY_RATE]
  self.hpRecovery = self.effects[const.ITEM_EFFECT_HP_RECOVERY]
  self.spRecoveryRate = self.effects[const.ITEM_EFFECT_SP_RECOVERY_RATE]
  self.spRecovery = self.effects[const.ITEM_EFFECT_SP_RECOVERY]
  self.maxHpPlus = self.effects[const.ITEM_EFFECT_MAXHP]
  self.maxSpPlus = self.effects[const.ITEM_EFFECT_MAXSP]
  self.atkPlus = self.effects[const.ITEM_EFFECT_ATK]
  self.defPlus = self.effects[const.ITEM_EFFECT_DEF]
  self.strPlus = self.effects[const.ITEM_EFFECT_STRE]
  self.intPlus = self.effects[const.ITEM_EFFECT_INTE]
  self.agiPlus = self.effects[const.ITEM_EFFECT_AGIL]
  self.vitPlus = self.effects[const.ITEM_EFFECT_VITA]
  self.dexPlus = self.effects[const.ITEM_EFFECT_DEXT]
  self.lucPlus = self.effects[const.ITEM_EFFECT_LUCK]
  self.parameter = self:getParameter()
end
-- 是否有能力改变
function clsItem:getParameter()
	for i in pairs(self.effects) do
  		if i > const.ITEM_EFFECT_SP_RECOVERY and i <= const.ITEM_EFFECT_LUCK then
  			if self.effects[i] ~= nil and self.effects[i] ~= 0 then
  				return true
  			end
  		end
    end
    return false
end