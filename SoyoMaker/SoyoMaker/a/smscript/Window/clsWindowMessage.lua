-- 显示文章的信息窗口。
clsWindowMessage = {}
setmetatable(clsWindowMessage,clsWindowSelect)
clsWindowMessage.__index = clsWindowMessage
-- 构造体
function clsWindowMessage:new(viewport)
	local self = {}
	--self = clsWindowSelect:new(viewport,0,globalGameEngine:getHeight() - 152,globalGameEngine:getWidth(),128)
	self = clsWindowSelect:new(viewport,0,0,globalGameEngine:getWidth(),128)
	setmetatable(self,clsWindowMessage)
	self.text = nil                 -- 尚未显示文章
	return self
end
-- 释放窗口
function clsWindowMessage:dispose()

end
-- 更新画面
function clsWindowMessage:update()
	
end
