--[[
  description:角色类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsCharacter = {}
setmetatable(clsCharacter,clsModel)
clsCharacter.__index = clsModel

--重定向需要覆盖的父类方法
clsCharacter.updateF=clsCharacter.update

--字段定义
clsCharacter.name="" --角色名称
clsCharacter.desc="" --角色描述
clsCharacter.headImageName="" --头像名称
clsCharacter.headImage=nil --头像
clsCharacter.charImageName="" --行走图名称
clsCharacter.charImage=nil --行走图
clsCharacter.battlerImageName="" --战斗图名称
clsCharacter.battlerImage=nil --战斗图
clsCharacter.mapId=0 --角色当前所在地图ID
clsCharacter.row=0 --角色当前所在行号
clsCharacter.col=0 --角色当前所在列号
clsCharacter.face=0 --角色当前面向   0上  1下  2左  3右 

clsCharacter.level=0 --等级
clsCharacter.maxHp=0 --MaxHP值
clsCharacter.maxSp=0 --MaxSP值
clsCharacter.hp=0 --HP值
clsCharacter.sp=0 --SP值
clsCharacter.str=0 --力量
clsCharacter.agi=0 --敏捷
clsCharacter.int=0 --智力
clsCharacter.vit=0 --体力
clsCharacter.dex=0 --灵巧
clsCharacter.luck=0 --幸运
clsCharacter.exp=0 --经验值
clsCharacter.money=0 --金钱

--构造器
function clsCharacter:new()
	local self = clsModel:new()
	setmetatable(self,clsCharacter)
	self.bag=clsBag:new()
	self.equip={ --装备(nil表示没有,有则存放装备ID)
         [1]=nil, --头盔			
         [2]=nil, --饰品
         [3]=nil, --武器
         [4]=nil, --盾牌
         [5]=nil, --铠甲
         [6]=nil  --战靴
    }
	return self
end

--更新[角色的基本更新] 
function clsCharacter:update()
  --调用父类的update方法
  self:updateF()
  --TODO 其它更新
end