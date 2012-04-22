--[[
  description:玩家类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsPlayer = {}
setmetatable(clsPlayer,clsCharacter)
clsPlayer.__index = clsPlayer


--重定向需要覆盖的父类方法
clsPlayer.updateFF=clsPlayer.update

--字段定义
clsPlayer.vocationId=0 --职业ID

--构造器
function clsPlayer:new()
  local self = clsCharacter:new()
  setmetatable(self,clsPlayer)
  return self
end

--更新[Player更新] 
function clsPlayer:update()
  --调用父类的update方法
  self:updateFF()
  --TODO 其它更新
end

