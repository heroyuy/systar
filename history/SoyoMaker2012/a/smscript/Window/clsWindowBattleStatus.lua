clsWindowBattleStatus = {}
setmetatable(clsWindowBattleStatus,clsWindowBase)
clsWindowBattleStatus.__index = clsWindowBattleStatus
-- 构造体
function clsWindowBattleStatus:new(viewport,x,y,width,height)
	local self = {}
	self = clsWindowBase:new(viewport,x,y,width,height)
	setmetatable(self,clsWindowBattleStatus)  
	self.index = 0
	self:refresh()
	return self 
end
function clsWindowBattleStatus:setIndex(actorIndex)
	self.index = actorIndex
	self:refresh()
end
-- 更新内容
function clsWindowBattleStatus:refresh()
	if self.index > 0 then
		self:clear()
		if globalGameParty:members()[self.index] ~= nil then
			self:drawActorName(globalGameParty:members()[self.index], 120, 16)
			self:drawActorFace(globalGameParty:members()[self.index], 16, 16)
			self:drawActorHp(globalGameParty:members()[self.index],120,37)
			self:drawActorSp(globalGameParty:members()[self.index],120,74)
			self.contents:drawString("状态",16,120,0)
			self:drawIcon(".\\game\\image\\icon\\IconSet.png", 60, 116,true)
		end
	end
end
-------------------
function clsWindowBattleStatus:naicet()
	return "clsWindowBattleStatus"
end