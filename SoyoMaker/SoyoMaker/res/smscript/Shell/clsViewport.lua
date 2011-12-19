clsViewport = {}
clsViewport.__index = clsViewport
-- 构造体
function clsViewport:new(x,y,width,height)
	local self = {}
	setmetatable(self,clsViewport)
	self.viewport = smViewport
	self.viewport:setup(x,y,width,height)
	self.sprites = {}
	self.touchListeners = {}
	table.insert(curScene.viewports,self)
	return self
end
-- 屏幕闪烁
function clsViewport:flash(color, duration) 
end
-- 设置深度
function clsViewport:setZ(z)
	self.viewport:setZ(z)
end
-- 获取深度
function clsViewport:z()
	return self.viewport:getZ()
end
-- 触屏处理
function clsViewport:onTouch(x,y,type)
	table.sort(self.touchListeners,function (a,b) return a.z > b.z end)
	for _,v in pairs(self.touchListeners) do
		if v ~= nil then
			if v:onTouch(x,y,type) then
				return true
			end
		end
	end
	return false
end
-- 画图
function clsViewport:paint(painter)
	table.sort(self.sprites,function (a,b) return a:z() < b:z() end)
	for _,v in pairs(self.sprites) do
		if v ~= nil then
			v:paint(painter)
		end
	end
end