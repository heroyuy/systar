-- ϵͳ������
clsSystem = {}
clsSystem.__index = clsSystem
-- ������
function clsSystem:new()
  local self = {}
  setmetatable(self,clsSystem)
  self.name = ""
  self.help = ""
  self.about = ""
  self.gold = ""
  self.players = {}
  self.curMapIndex = -1
  self.row = -1
  self.col = -1
  self.face = -1
  self.hp = ""
  self.sp = ""
  self.atk = ""
  self.def = ""
  self.matk = ""
  self.mdef = ""
  self.stre = ""
  self.inte = ""
  self.agil = ""
  self.dext = ""
  self.vita = ""
  self.luck = ""
  self.helm = ""
  self.armour = ""
  self.weapon = ""
  self.shield = ""
  self.boots = ""
  self.jewelry = ""
  self.item = ""
  self.equip = ""
  self.skill = ""
  self.skin = ""
  self.startAniIndex = -1
  self.titleBackground = ""
  self.titleMusic = ""
  self.startBattleSound = ""
  self.attributes = {}
  return self
end
