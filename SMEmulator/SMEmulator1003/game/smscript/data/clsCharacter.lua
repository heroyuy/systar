--[[
  description:角色类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsCharacter = {}
setmetatable(clsCharacter,clsModel)
clsCharacter.__index = clsCharacter

--重定向需要覆盖的父类方法
clsCharacter.updateF=clsCharacter.update

--字段定义
clsCharacter.name="" --角色名称
clsCharacter.desc="" --角色描述
clsCharacter.headImageName=nil --头像名称
clsCharacter.headImage=nil --头像
clsCharacter.charImageName=nil --行走图名称
clsCharacter.charImage=nil --行走图
clsCharacter.battlerImageName=nil --战斗图名称
clsCharacter.battlerImage=nil --战斗图
clsCharacter.mapId=0 --角色当前所在地图ID
clsCharacter.row=0 --角色当前所在行号
clsCharacter.col=0 --角色当前所在列号
clsCharacter.face=0 --角色当前面向   0上  1下  2左  3右 
clsCharacter.step=0 --角色当前行走步数 取值范围[0,3]
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

clsCharacter.moveSequence=nil --角色当前移动序列
clsCharacter.curMoveDirection=nil --角色当前移动方向
--构造器
function clsCharacter:new()
	local self = clsModel:new()
	setmetatable(self,clsCharacter)
	self.moveSequence=clsQueue:new()
	return self
end

--更新[角色的基本更新] 
function clsCharacter:update()
  --调用父类的update方法
  self:updateF()
  --行走
  if self.curMoveDirection==nil and self.moveSequence:size()~=0 then
    self.curMoveDirection=self.moveSequence:pop()
  end
  if self.curMoveDirection then
    self:move()
  end
  --TODO 其它更新
end

--行走
function clsCharacter:move()
  self.step=self.step+1
  if self.step==4 then
    if self.curMoveDirection==0 then
      --上
      self.row=self.row-1
    elseif self.curMoveDirection==1 then
      --下
      self.row=self.row+1
    elseif self.curMoveDirection==2 then
      --左
      self.col=self.col-1
    elseif self.curMoveDirection==3 then
      --右
      self.col=self.col+1
    end
    self.curMoveDirection=nil
    self.step=0
  end
end

