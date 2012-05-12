clsWindowSkill = {}
setmetatable(clsWindowSkill,clsWindowUsable)
clsWindowSkill.__index = clsWindowSkill
function clsWindowSkill:new(viewport,x,y,width,height,spacing,itemHeight)
	local self = {}
	self = clsWindowUsable:new(viewport,x,y,width,height,spacing,itemHeight)
	setmetatable(self,clsWindowSkill)
	return self
end
-- 设置战斗者
function clsWindowSkill:setSkills(battler)
	self.items = battler:skills()
	self.helpWindow:drawIntro(self.items[self.cursorIndex + 1],16,21)
	self:refresh()
end
-- 更新内容
function clsWindowSkill:refresh()
	self:clear()
	self.contents:setColor(smColor:getColor(255, 0,0, 255))
	for j = 0,self.itemMax - 1 do
		for i = 0,self.columnMax - 1 do
			if self.curIndex + i + j*self.columnMax + 1 <= #self.items and self.curIndex + i + j*self.columnMax >= 0 then
				if self.items[self.curIndex + i + j*self.columnMax + 1] ~= nil then
					self:drawIcon(".\\game\\image\\icon\\skill\\"..self.items[self.curIndex + i + j*self.columnMax + 1]:icon(),20 + i * (self.spacing + self.itemWidth),20 + j*self.itemHeight)
					self.contents:drawString(self.items[self.curIndex + i + j*self.columnMax + 1]:name(),48 + i * (self.spacing + self.itemWidth),22 + j*self.itemHeight,0)
				end
				--self.contents:drawString(":"..globalGameParty:itemNumber(self.items[self.curIndex + i + j*self.columnMax + 1]),(i+1) * (self.spacing + self.itemWidth)-48,22 + j*self.itemHeight,0)
			end
		end
	end
end
-- 获取技能
function clsWindowSkill:skill()
	return self.items[self.cursorIndex + 1]
end