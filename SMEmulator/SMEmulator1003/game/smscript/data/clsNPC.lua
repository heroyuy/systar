--[[
  description:NPC类
  author:wp_g4
  date:2012/02/09
--]]

--结构定义
clsNPC = {}
setmetatable(clsNPC,clsCharacter)
clsNPC.__index = clsNPC


--重定向需要覆盖的父类方法
clsNPC.updateFF=clsNPC.update

--字段定义
clsNPC.vocationId=0 --职业ID

--构造器
function clsNPC:new()
  local self = clsCharacter:new()
  setmetatable(self,clsNPC)
  return self
end

--更新[NPC更新] 
function clsCharacter:update()
  --调用父类的update方法
  self:updateFF()
  --TODO 其它更新
end

