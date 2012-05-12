clsWindowBattleMessage = {}
setmetatable(clsWindowBattleMessage,clsWindowBase)
clsWindowBattleMessage.__index = clsWindowBattleMessage
function clsWindowBattleMessage:new(viewport,x,y)
	local self = {}
	self = clsWindowBase:new(viewport,x,y,360,152)
	setmetatable(self,clsWindowBattleMessage)
	self.itemMax = math.floor((self:height() - 32)/16)
	self.message = {}
    self:refresh()
    return self
end
-- 更新内容  
function clsWindowBattleMessage:refresh()
	table.delete(self.message,nil)
    self:clear()
    self.contents:setTextSize(14)
    self.contents:setColor(self:normalColor())
    if #self.message - self.itemMax > 0 then
    	for i = 1,#self.message - self.itemMax do
    		table.shift(self.message)
    	end
    end
    for i = 1,#self.message do
    	if self.message[i] ~= nil then
    		self.contents:drawString(self.message[i],16,i*16,0)
    	end
    end
end  
-------------------
function clsWindowBattleMessage:naicet()
	return "clsWindowBattleMessage"
end