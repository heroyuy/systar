clsGameMessage = {}
clsGameMessage.__index = clsGameMessage
function clsGameMessage:new()
	local self = {}
	setmetatable(self,clsGameMessage)
	self.texts = {}
    self.headImg = ""
    self.background = 0
    self.position = 2
    self.mainProc = nil
    self.choiceStart = 99
    self.choiceMax = 0
    self.choiceCancelType = 0
    self.choiceProc = nil
    self.numInputVariableIndex = 0
    self.numInputDigitsMax = 0
    return self
end
-- 确认忙碌状态
function clsGameMessage:busy()
	return #self.texts > 0
end
-- 下一页
function clsGameMessage:newPage()
end