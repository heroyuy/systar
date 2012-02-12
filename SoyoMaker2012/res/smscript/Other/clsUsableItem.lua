-- 技能和物品的超类
clsUsableItem = {}
setmetatable(clsUsableItem,clsBaseItem)
clsUsableItem.__index = clsUsableItem
-- 构造体
function clsUsableItem:new()
  local self = {}
  self = clsBaseItem:new()
  setmetatable(self,clsUsableItem)
  self.target = 0
  self.limit = 0
  self.useAniIndex = 0
  self.targetAniIndex = 0
  self.sound = ""
  self.eventIndex = 0
  self.costs = {}
  self.effects = {}
  self.factors = {}
  self.attributes = {}
  self.buffs = {}
  return self
end
-- 对一些默认值赋值
function clsUsableItem:setup()
  self.baseDamage = self.effects[const.USABLE_EFFECT_BASE_DAMAGE]
  self.atkF = self.factors[const.USABLE_FACTOR_ATKF]
  self.intF = self.factors[const.USABLE_FACTOR_INTF]
  self.agiF = self.factors[const.USABLE_FACTOR_AGIF]
  self.speed = self.factors[const.USABLE_FACTOR_SPEED]
  self.variance = self.factors[const.USABLE_FACTOR_VARIANCE]
  self.physicalAttack = (self.attributes[const.PHYSICAL_ATTACK] ~= nil)
  self.damageToMp = (self.attributes[const.DAMAGE_TO_MP] ~= nil)
  self.absorbDamage = (self.attributes[const.ABSORB_DAMAGE] ~= nil)
  self.ignoreDefense = (self.attributes[const.IGNORE_DEFENSE] ~= nil)
  self.plusBuffSet,self.minusBuffSet = self:buffSet()
end
-- 判断效果范围是否对敌方队员有效
function clsUsableItem:isForOpponent()
  if self.target == 3 or self.target == 4 then
    return true
  end
  return false
end
-- 判断效果范围是否对己方队员有效
function clsUsableItem:isForFriend()
  if self.target == 1 or self.target == 2 or self.target == 5 or self.target == 6  or self.target == 7 then
    return true
  end
  return false
end
-- 判断效果范围是否对无法战斗的己方队员有效
function clsUsableItem:isForDeadFriend()
  if self.target == 5 or self.target == 6 then
    return true
  end
  return false
end
-- 判断效果范围是否对使用者本身有效
function clsUsableItem:isForUser()
  if self.target == 7 then
    return true
  end
  return false
end
-- 判断效果范围是否对单体队员有效
function clsUsableItem:isForOne()
  if self.target == 1 or self.target == 3 or self.target == 5 or self.target == 7 then
    return true
  end
  return false
end
-- 判断效果范围是否对全体队员有效
function clsUsableItem:isForAll()
  if self.target == 2 or self.target == 4 or self.target == 6 then
    return true
  end
  return false
end
-- 判断效果范围是否对需要选取目标有效
function clsUsableItem:isNeedSelection()
  if self.target == 1 or self.target == 3 or self.target == 5 then
    return true
  end
  return false
end
-- 判断物品是否可以在战斗中使用
function clsUsableItem:isBattleOk()
  if self.limit == 0 or self.limit == 2 then
    return true
  end
  return false
end
-- 判断物品是否可以在菜单中使用
function clsUsableItem:isMenuOk()
  if self.limit == 0 or self.limit == 1 then
    return true
  end
  return false
end
-- 获取附加状态列表
function clsUsableItem:buffSet()
	local plus = {}
	local minus = {}
	for k,v in pairs(self.buffs) do
		if v > 0 then
			plus[k] = v
		end
		if v < 0 then
			minus[k] = v
		end
	end
	return plus,minus
end