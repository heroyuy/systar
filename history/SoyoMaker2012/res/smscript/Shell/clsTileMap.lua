-- 用于显示瓦片地图的类
clsTileMap = {}
clsTileMap.__index = clsTileMap
-- 构造体
function clsTileMap:new()
	local self = {}
	setmetatable(self,clsTileMap)
	self.tilemap = smTileMap
	return self
end
-- 设置精灵
function clsTileMap:setSprite(sprite1,sprite2)
	self.tilemap:setSprite(sprite1.sprite,sprite2.sprite)
end
-- 设置瓦片地图
function clsTileMap:setup(map,displayX,displayY,width,height,fps)
	self.tilemap:setup(map,displayX,displayY,width,height,fps)
end
-- 更新瓦片地图
function clsTileMap:update()
	self.tilemap:update()
end
-- 绘制前景
function clsTileMap:forePaint(painter)
	self.tilemap:forePaint(painter)
end
-- 绘制背景
function clsTileMap:backPaint(painter)
	self.tilemap:backPaint(painter)
end
-- 绘制动态层
function clsTileMap:activePaint(painter)
	self.tilemap:activePaint(painter)
end
-- 设定位置
function clsTileMap:position(ox,oy)
	self.tilemap:position(ox,oy)
end
-- 获取地图显示横坐标	
function clsTileMap:ox()
		return self.tilemap:getOx()
end
-- 获取地图显示纵坐标	
function clsTileMap:oy()
	return self.tilemap:getOy()
end