-- 主角的资料类
clsPlayer = {}
clsPlayer.__index = clsPlayer
-- 构造体
function clsPlayer:new()
  local self = {}
  setmetatable(self,clsPlayer)
  self.index = 0
  self.name = ""
  self.intro = ""
  self.headImg = ""
  self.charImg = ""
  self.battlerImg = ""
  self.vocationIndex = 0
  self.startLev = 0
  self.maxLev = 99
  self.expList = {}
  self.maxHpList = {}
  self.maxSpList = {}
  self.strList = {}
  self.agiList = {}
  self.intList = {}
  self.vitList = {}
  self.dexList = {}
  self.lucList = {}
  self.helmIndex = 0
  self.jewelryIndex = 0
  self.weaponIndex = 0
  self.shieldIndex = 0
  self.armourIndex = 0
  self.bootsIndex = 0
  return self
end
