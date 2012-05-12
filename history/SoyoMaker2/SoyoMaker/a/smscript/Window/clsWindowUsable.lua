-- 物品窗体
clsWindowUsable = {}
setmetatable(clsWindowUsable,clsWindowSelect)
clsWindowUsable.__index = clsWindowUsable
-- 构造体
function clsWindowUsable:new(viewport,x,y,width,height,spacing,itemHeight)
	local self = {}
	self = clsWindowSelect:new(viewport,x,y+56,width,height-112,spacing,itemHeight)
	self:setup(3)
	self.updateF = self.update
	self.setVisibleF = self.setVisible
	self.setZF = self.setZ
	self.setTouchListenerF = self.setTouchListener
	self.helpWindow = clsWindowBase:new(viewport,x,y,width,56)
	self.commands = {[0] = "确定",[1] = "取消"}
	self.commandWindow = clsWindowCommand:new(viewport,width,self.commands,2,0,32,24)
	self.commandWindow:setY(height - 56)
	self.commandWindow:setup()
	setmetatable(self,clsWindowUsable)
	self:setZ(100)
	return self
end
-- 设置可见性
function clsWindowUsable:setVisible(visible)
	self:setVisibleF(visible)
	self.helpWindow:setVisible(visible)
	self.commandWindow:setVisible(visible)
end
-- 设置窗体深度
function clsWindowUsable:setZ(z)
	self:setZF(z)
	self.helpWindow:setZ(z)
	self.commandWindow:setZ(z)
end
-- 设置活动性
function clsWindowUsable:setActive(windowActive)
	self.active = windowActive
	self.helpWindow.active = windowActive
	self.commandWindow.active = windowActive
end
-- 设置侦听器
function clsWindowUsable:setTouchListener()
	self:setTouchListenerF()
	self.helpWindow:setTouchListener()
	self.commandWindow:setTouchListener()
end
-- 设置光标
function clsWindowUsable:setCursor(x,y)
	if self.cursorIndex ~= self:col(x)+ self:row(y)  * self.columnMax+ self.curIndex then
		if self:col(x)+ self:row(y) * self.columnMax+ self.curIndex + 1 <= #self.items then
			if self:col(x)+ self:row(y)  * self.columnMax >= 0 then
				self.cursorIndex = self:col(x)+ self:row(y)  * self.columnMax+ self.curIndex
				self.helpWindow:drawIntro(self.items[self.cursorIndex + 1],16,21)
				self:cursorRect():set(16 + self:col(x)*(self.itemWidth+self.spacing),16 + self:row(y) * self.itemHeight,self.itemWidth,self.itemHeight)
			end
		end
	end
end
-- 更新
function clsWindowUsable:update()
	self:updateF()
	self.helpWindow:update()
	self.commandWindow:update()
end
