-- 所有窗体的超级类
clsWindowBase = {}
clsWindowBase.__index = clsWindowBase
WLH = 24   -- 窗口行高（Window Line Height）
-- 构造体
function clsWindowBase:new(viewport,x,y,width,height)
	local self = {}
	setmetatable(self,clsWindowBase)
	self.viewport = viewport
	self.window = smWindow:createWindow()
	self.window:setup(viewport.viewport,x,y,width,height)
	table.insert(viewport.sprites,self)
	self.skin = smVXSkin:createSkin(width,height,globalDataSystem.skin)
	self.window:setSkin(self.skin)
	self.contents = self.window:contents()
	self.bloodBar = smBloodBar
	self.bloodBar:setup(".\\game\\image\\picture\\bloodbar.png\\",128,18)
	self.window:setOpenness(255)
	self.opening = false
    self.closing = false
    self:setAlpha(200)
    self.window:setVisible(false)
    self.active = false
	return self
end
-- 设置横坐标
function clsWindowBase:setX(x)
	self.window:setX(x)
end
-- 设置纵坐标
function clsWindowBase:setY(y)
	self.window:setY(y)
end
-- 设置纵坐标
function clsWindowBase:setZ(z)
	self.window:setZ(z)
end
-- 获取横坐标
function clsWindowBase:x()
	return self.window:x()
end
-- 获取纵坐标
function clsWindowBase:y()
	return self.window:y()
end
-- 获取深度
function clsWindowBase:z()
	return self.window:z()
end
-- 获取纵坐标
function clsWindowBase:ox()
	return 0
end
-- 获取深度
function clsWindowBase:oy()
	return 0
end
-- 设置宽度
function clsWindowBase:setWidth(width)
	self.window:setWidth(width)
end
-- 设置高度
function clsWindowBase:setHeight(height)
	self.window:setHeight(height)
end
-- 获取宽度
function clsWindowBase:width()
	return self.window:width()
end
-- 获取高度
function clsWindowBase:height()
	return self.window:height()
end
-- 设置可见性
function clsWindowBase:setVisible(visible)
	self.window:setVisible(visible)
end
-- 获取可见性
function clsWindowBase:visible()
	return self.window:visible()
end
-- 设置透明度
function clsWindowBase:setAlpha(alpha)
	self.window:setAlpha(alpha)
end
-- 获取透明度
function clsWindowBase:alpha()
	return self.window:alpha()
end
-- 设置打开度
function clsWindowBase:setOpenness(openness)
	self.window:setOpenness(openness)
end
-- 获取打开度
function clsWindowBase:openness()
	return self.window:openness()
end
-- 获取光标方块
function clsWindowBase:cursorRect()
	return self.window:cursorRect()
end
-- 设置监听器
function clsWindowBase:setTouchListener()
	self.touchListener = clsTouchListener:new()
	self.touchListener.widget = self
	table.insert(self.viewport.touchListeners,self.touchListener)
end
-- 获取文字颜色
-- 文字颜色色号（0-31）
function clsWindowBase:textColor(n)
    return self.skin:textColor(n)
end
-- 获取一般文字颜色
function clsWindowBase:normalColor()
    return self:textColor(0)
end
-- 获取系统文字颜色
function clsWindowBase:systemColor()
    return self:textColor(16)
end
-- 获取危机文字颜色
function clsWindowBase:crisisColor()
    return self:textColor(17)
end
-- 获取战斗不能文字颜色
function clsWindowBase:knockoutColor()
    return self:textColor(18)
end
-- 获取变量条背景颜色
function clsWindowBase:gaugeBackColor()
    return self:textColor(19)
end
-- 获取体力值槽颜色1
function clsWindowBase:hpGaugeColor1()
    return self:textColor(20)
end
-- 获取体力值槽颜色2
function clsWindowBase:hpGaugeColor2()
    return self:textColor(21)
end
-- 获取魔力值槽颜色1
function clsWindowBase:mpGaugeColor1()
    return self:textColor(22)
end
-- 获取魔力值槽颜色2
function clsWindowBase:mpGaugeColor2()
    return self:textColor(23)
end
-- 获取装备画面能力值上升颜色
function clsWindowBase:powerUpColor()
    return self:textColor(24)
end
-- 获取装备画面能力值下降颜色
function clsWindowBase:powerDownColor()
    return self:textColor(25)
end
-- 重绘
function clsWindowBase:refresh()
	if self:openness() == 255 and self.opening == false and self.closing == false then
		self:clear()
		self:drawFace(".\\game\\image\\face\\$姜维.png\\", 16, 16)
		self.contents:setTextSize(20)
		self.contents:setColor(smColor:getColor(255,0,0,0))
		self.contents:drawString("吾乃姜维",128,20,0)
	end
end
-- 更新
function clsWindowBase:update()
	if self.opening then
      self:setOpenness(self:openness()+48)
      if self:openness() == 255 then
      	self.opening = false 
      	self:refresh()
      end
    elseif self.closing then
      self:setOpenness(self:openness()-48)
      if self:openness() == 0 then
     	 self.closing = false 
      end
    end
    self:dispatchTouchEvent()
end
-- 处理触屏事件
function clsWindowBase:dispatchTouchEvent()
	if self.touchListener ~= nil and self.active then
		if self.touchListener.event.type ~= -1 then
			if self:openness() == 255 then
				self:close()
			elseif self:openness() == 0 then
				self:open()
			end
		end
		self.touchListener.event:setup(-1,-1,-1)
	end
end
-- 打开
function clsWindowBase:open()
	if self:openness() < 255 then
		self.opening = true 
	end
    self.closing = false
   
end
-- 关闭
function clsWindowBase:close()
	if self:openness() > 0 then
		self.closing = true 
	end
    self.opening = false
end
-- 清除内容
function clsWindowBase:clear()
	self.window:clear()
end
-- 绘制图标
function clsWindowBase:drawIcon(iconName, x, y,enabled)
	local alpha = (enabled and 255) or 128
	self.contents:drawImage(smImage:createImage(iconName),x,y,alpha,0)
end
-- 绘制头像
function clsWindowBase:drawFace(faceName, x, y)
	self.contents:drawImage(smImage:createImage(faceName),x,y,0)
end
-- 绘制角色头像
function clsWindowBase:drawActorFace(actor, x, y)
    self:drawFace(".\\game\\image\\face\\"..actor:headImg(), x, y)
end
-- 绘制血条
function clsWindowBase:drawBar(hp,maxHp,x,y,type)
	self.contents:drawImage(self.bloodBar:frame(),x,y,0)
	if type == 0 then
		self.contents:drawImage(self.bloodBar:hpBar(hp,maxHp),x+1,y,0)
	elseif type == 1 then
		self.contents:drawImage(self.bloodBar:spBar(hp,maxHp),x+1,y,0)
	elseif type == 2 then
		self.contents:drawImage(self.bloodBar:yellowBar(hp,maxHp),x,y,0)
	elseif type == 3 then
		self.contents:drawImage(self.bloodBar:greenBar(hp,maxHp),x,y,0)
	elseif type == 4 then
		self.contents:drawImage(self.bloodBar:anotherBar(hp,maxHp),x,y,0)
	end
end
-- 绘制角色名字
function clsWindowBase:drawActorName(actor,x,y)
	self.contents:setColor(self:hpColor(actor))
	self.contents:setTextSize(18)
	self.contents:drawString(actor:name(),x,y,0)
end
-- 绘制HP
function clsWindowBase:drawActorHp(actor,x,y)
	self:drawBar(actor:hp(),actor:maxHp(),x,y+18,0)
	self.contents:setTextSize(16)
	self.contents:setColor(self:systemColor())
	self.contents:drawString(globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):hp(),x,y,0)
	self.contents:setColor(self:hpColor(actor))
	
	self.contents:drawString(actor:hp().."/"..actor:maxHp(),x+64,y+17,3)
end
-- 绘制SP
function clsWindowBase:drawActorSp(actor,x,y)
	self:drawBar(actor:sp(),actor:maxSp(),x,y+16,1)
	self.contents:setTextSize(16)
	self.contents:setColor(self:systemColor())
	self.contents:drawString(globalDataManager:getModel(const.DATA_MANAGER_CONFIG,0):sp(),x,y,0)
	self.contents:setColor(self:spColor(actor))
	self.contents:drawString(actor:sp().."/"..actor:maxSp(),x+64,y+17,3)
end
-- 绘制物品名字
function clsWindowBase:drawItemName(item,x,y,itemWidth)
	if item:isA() == "Item" then
		self:drawIcon(".\\game\\image\\icon\\item\\"..item:icon(),x,y)
	elseif item:isA() == "Equip" then
		self:drawIcon(".\\game\\image\\icon\\equip\\"..item:icon(),x,y)
	end
	self.contents:setTextSize(14)
	self.contents:setColor(self:normalColor())
	self.contents:drawString(item:name(),28 + x,y + 2,0)
	self.contents:drawString(":"..globalGameParty:itemNumber(item),x + itemWidth - 64,y + 2,0)
end
--  绘制行走图
function clsWindowBase:drawCharacter(characterName, x, y)
	if characterName == nil then
		return 
	end
	local ima = smImage:createImage(characterName)
	local cw,ch
	cw = ima:getWidth()/3
	ch = ima:getHeight()/4
	self.contents:drawImage(smImage:copyImage(ima,0,32,cw,ch),x,y,0)
end
-- 绘制解释
function clsWindowBase:drawIntro(item,x,y)
	self:clear()
	self.contents:setColor(self:normalColor())
	self.contents:setTextSize(14)
	self.contents:drawString(item:intro(),x,y,0)
end
-- 获取体力文字颜色
function clsWindowBase:hpColor(actor)
	if actor:hp() == 0 then
		return self:knockoutColor()
	end
	if actor:hp() < actor:maxHp() / 4 then
		return self:crisisColor()
	end
	return self:normalColor()
end
-- 获取魔力文字颜色
function clsWindowBase:spColor(actor)
	if actor:sp() < actor:maxSp() / 4 then
   		return self:crisisColor() 
    end
    return self:normalColor()
end
-- 绘图
function clsWindowBase:paint(painter)
	self.window:paint(painter)
end