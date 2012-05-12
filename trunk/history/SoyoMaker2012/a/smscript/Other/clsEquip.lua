-- 装备资料类，包括防具及武器
clsEquip = {}
setmetatable(clsEquip,clsBaseItem)
clsEquip.__index = clsEquip
-- 构造体
function clsEquip:new()
  local self = {}
  self = clsBaseItem:new()
  setmetatable(self,clsEquip)
  self.kind = 0
  self.price = 0
  self.useAniIndex = 0
  self.targetAniIndex = 0
  self.effects = {}
  self.attributes = {}
  self.buffs = {}
  return self
end
-- 对一些默认属性赋值
function clsEquip:setup()
  self.atk = self.effects[const.EQUIP_EFFECT_ATK]
  self.def = self.effects[const.EQUIP_EFFECT_DEF]
  self.hit = self.effects[const.EQUIP_EFFECT_HIT]
  self.eva = self.effects[const.EQUIP_EFFECT_EVA]
  self.stre = self.effects[const.EQUIP_EFFECT_STRE]
  self.inte = self.effects[const.EQUIP_EFFECT_INTE]
  self.agil = self.effects[const.EQUIP_EFFECT_AGIL]
  self.dext = self.effects[const.EQUIP_EFFECT_DEXT]
  self.vita = self.effects[const.EQUIP_EFFECT_VITA]
  self.luck = self.effects[const.EQUIP_EFFECT_LUCK]
  if self.kind == const.EQUIP_WEAPON then -- 当装备为武器的时候
    self.twoHanded = (self.attributes[const.TWO_HANDED] ~= nil)
    self.fastAttack = (self.attributes[const.FAST_ATTACK] ~= nil)
    self.dualAttack = (self.attributes[const.DUAL_ATTACK] ~= nil)
    self.criticalBonus = (self.attributes[const.CRITICAL_BONUS] ~= nil)
	self.preventCritical = false
    self.halfMpCost = false
    self.doubleExpGain = false
    self.autoHpRecover = false
  else -- 当装备为防具的时候
    self.twoHanded = false
	self.fastAttack = false
	self.dualAttack = false
	self.criticalBonus = false
    self.preventCritical = (self.attributes[const.PREVENT_CRITICAL] ~= nil)
    self.halfMpCost = (self.attributes[const.HALF_MP_COST] ~= nil)
    self.doubleExpGain = (self.attributes[const.DOUBLE_EXP_GAIN] ~= nil)
    self.autoHpRecover = (self.attributes[const.AUTO_HP_RECOVER] ~= nil)
  end
end
