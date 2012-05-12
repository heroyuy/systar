-- 敌人资料类
clsEnemy = {}
clsEnemy.__index = clsEnemy
-- 构造体
function clsEnemy:new()
  local self = {}
  setmetatable(self,clsEnemy)
  self.index = 0
  self.name = ""
  self.intro = ""
  self.battleImg = ""
  self.lev = 0
  self.maxHp = 0
  self.maxSp = 0
  self.stre = 0
  self.inte = 0
  self.agil = 0
  self.dext = 0
  self.vita = 0
  self.luck = 0
  self.exp = 0
  self.money = 0
  self.equips = {}
  self.treasures = {}
  self.actions = {}
  self.attributes = {}
  self.buffs = {}
  return self
end
-- 对一些默认属性赋值
function clsEnemy:setup()
  self.levitate = (self.attributes[const.LEVITATE] ~= nil)
  self.hasCritical = (self.attributes[const.HAS_CRITICAL] ~= nil)
end
