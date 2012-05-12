-- 地图精灵，用于显示地图
clsSpriteMap = {}
clsSpriteMap.__index = clsSpriteMap
-- 构造体
function clsSpriteMap:new(viewport)
	local self = {}
	setmetatable(self,clsSpriteMap)
	self.sprite = smSpriteMap:createSprite()
	self:setup(viewport.viewport)
	table.insert(viewport.sprites,self)
	return self
end
-- 获得深度	
function clsSpriteMap:z()
	return self.sprite:z()
end
-- 设置
function clsSpriteMap:setup(viewport)
	self.sprite:setup(viewport)
end
-- 绘图
function clsSpriteMap:paint(painter)
	self.sprite:paint(painter)
end