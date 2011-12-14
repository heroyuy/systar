--[[
  description:玩家类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsPlayer = {}
setmetatable(clsPlayer,clsCharacter)
clsPlayer.__index = clsPlayer

--字段定义
clsPlayer.vocationId=0 --职业ID
clsPlayer.level=0 --等级
clsPlayer.exp=0 --经验值
clsPlayer.hp=0 --HP值
clsPlayer.sp=0 --SP值
clsPlayer.str=0 --力量
clsPlayer.agi=0 --敏捷
clsPlayer.int=0 --智力
clsPlayer.vit=0 --体力
clsPlayer.dex=0 --灵巧
clsPlayer.luck=0 --幸运

--构造器
function clsPlayer:new()
	local self = {}
	setmetatable(self,clsPlayer)
	return self
end

local player=clsPlayer:new()
player.index=20
print(player.index)
player=clsPlayer:new()
print(player.index)
