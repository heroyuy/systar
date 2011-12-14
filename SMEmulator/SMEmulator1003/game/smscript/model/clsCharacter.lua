--[[
  description:角色类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsCharacter = {}
clsCharacter.__index = clsModel

--字段定义
clsCharacter.id=0 --角色ID
clsCharacter.name=0 --角色名称
clsCharacter.desc=0 --角色描述
clsCharacter.headImageName="" --头像名称
clsCharacter.headImage=nil --头像
clsCharacter.charImageName="" --行走图名称
clsCharacter.charImage=nil --行走图
clsCharacter.battlerImageName="" --战斗图名称
clsCharacter.battlerImage=nil --战斗图
clsCharacter.mapId=0 --角色当前所在地图ID
clsCharacter.row=0 --角色当前所在行号
clsCharacter.col=0 --角色当前所在列号

--构造器
function clsCharacter:new()
	local self = {}
	setmetatable(self,clsCharacter)
	return self
end

--更新
function clsCharacter:update()
  --角色的基本更新 
  --TODO如何调用父类的update()
end