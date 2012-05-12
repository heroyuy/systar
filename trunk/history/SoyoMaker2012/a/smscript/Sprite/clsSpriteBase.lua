-- 精灵类，基础显示组件之一
clsSpriteBase = {}
clsSpriteBase.__index = clsSpriteBase
-- 构造体
function clsSpriteBase:new(viewport)
	local self = {}
	setmetatable(self,clsSpriteBase)
	self.sprite = smSprite:createSprite()
	self:setup(viewport.viewport)
	self.viewport = viewport
	table.insert(viewport.sprites,self)
	self.active = false
	return self
end
-- 获得可见
function clsSpriteBase:visible()
	return self.sprite:visible()
end
-- 设置可见
function clsSpriteBase:setVisible(visible)
	self.sprite:setVisible(visible)
end
-- 设置精灵
function clsSpriteBase:setup(viewport)
	self.sprite:setup(viewport)
end
-- 设置图片
function clsSpriteBase:setImage(image)
	self.sprite:setImage(image)
end
-- 清除图片
function clsSpriteBase:clearImage()
	self.sprite:setImage()
end
-- 获取参考横坐标
function clsSpriteBase:ox()
	return self.sprite:ox()
end
-- 获取参考纵坐标	
function clsSpriteBase:oy()
	return self.sprite:oy()
end
-- 获得相对横坐标
function clsSpriteBase:x()
	return self.sprite:x()
end
-- 获得相对纵坐标
function clsSpriteBase:y()
	return self.sprite:y()
end
-- 获得深度	
function clsSpriteBase:z()
	return self.sprite:z()
end
-- 设置参考点横坐标	
function clsSpriteBase:setOx(ox)
	self.sprite:setOx(ox)
end
-- 设置参考点纵坐标	
function clsSpriteBase:setOy(oy)
	self.sprite:setOy(oy)
end
-- 设置相对横坐标	
function clsSpriteBase:setX(x)
	self.sprite:setX(x)
end
-- 设置相对纵坐标	
function clsSpriteBase:setY(y)
	self.sprite:setY(y)
end
-- 设置深度	
function clsSpriteBase:setZ(z)
	self.sprite:setZ(z)
end
-- 获取宽度
function clsSpriteBase:width()
	return self.sprite:srcRect():getWidth()
end
-- 获取高度
function clsSpriteBase:height()
	return self.sprite:srcRect():getHeight()
end
-- 获得方块
function clsSpriteBase:srcRect()
	return self.sprite:srcRect()
end
-- 获得显示端口
function clsSpriteBase:viewport()
	return self.sprite:viewport()
end
-- 放大
function clsSpriteBase:zoom(width,height)
end
-- 旋转
function clsSpriteBase:rotate(angle)
end
-- 获得图片
function clsSpriteBase:image()
	return self.sprite:image()
end
-- 设置透明度
function clsSpriteBase:setAlpha(alpha)
	self.sprite:setAlpha(alpha)
end
-- 获取透明度
function clsSpriteBase:alpha()
	return self.sprite:alpha()
end
-- 设置色调
function clsSpriteBase:setTone(red,green,blue)
	self.sprite:setTone(red,green,blue)
end
-- 获取色调
function clsSpriteBase:tone()
	return self.sprite:tone()
end
-- 设置透明度和色调
function clsSpriteBase:setAlphaTone(alpha,red,green,blue)
	self.sprite:setTone(alpha,red,green,blue)
end
-- 播放动画
function clsSpriteBase:startAnimation(animation,mirror)
end
-- 闪烁
function clsSpriteBase:flash(color, duration) 
end
-- 设置监听器
function clsSpriteBase:setTouchListener()
	self.touchListener = clsTouchListener:new()
	self.touchListener.widget = self
	table.insert(self.viewport.touchListeners,self.touchListener)
end
-- 更新方法
function clsSpriteBase:update()
	
end
-- 绘图
function clsSpriteBase:paint(painter)
	self.sprite:paint(painter)
end