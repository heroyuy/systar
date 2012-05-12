-- 战斗场景视图组
clsSpriteSetBattle = {}
clsSpriteSetBattle.__index = clsSpriteSetBattle
-- 构造体
function clsSpriteSetBattle:new()
	local self = {}
	setmetatable(self,clsSpriteSetBattle)
	self.reader = {}
	self.reader.enemyActive = false
	self.reader.enemyIndex = -1
	self:createViewports()
	self:createBattleback()
	self:createShadow()
	self:createEnemies()
	return self
end
-- 建立显示端口
function clsSpriteSetBattle:createViewports()
	self.viewport1 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 精灵层
    self.viewport2 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 前景层
   	self.viewport3 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 交互层
   	self.viewport1:setZ(0)
    self.viewport2:setZ(50)
    self.viewport3:setZ(100)
end
-- 生成战斗背景活动块
function clsSpriteSetBattle:createBattleback()
	self.battlebackSprite = clsSpriteBase:new(self.viewport1)
	self.battlebackSprite:setImage(globalGameTemp.backgroundImage)
	self.battlebackSprite:setOx(self.battlebackSprite:image():getWidth()/2)
	self.battlebackSprite:setOy(self.battlebackSprite:image():getHeight()/2)
	self.battlebackSprite:setX(globalGameEngine:getWidth()/2)
	self.battlebackSprite:setY(globalGameEngine:getHeight()/2)
	self.battlebackSprite:setVisible(true)
end
-- 生成阴影
function clsSpriteSetBattle:createShadow()
	self.shadowSprite = clsSpriteBase:new(self.viewport1)
	self.shadowSprite:setImage(smImage:createImage(".\\game\\image\\picture\\BattleFloor.png"))
	self.shadowSprite:setOx(self.shadowSprite:image():getWidth()/2)
	self.shadowSprite:setOy(self.shadowSprite:image():getHeight()/2)
	self.shadowSprite:setX(globalGameEngine:getWidth()/2)
	self.shadowSprite:setY(globalGameEngine:getHeight()/2 + 32)
	self.shadowSprite:setVisible(true)
end
-- 生成敌人活动块
function clsSpriteSetBattle:createEnemies()
	self.enemySprites = {}
	for _,enemy in pairs(globalGameTroop:members()) do
      table.insert(self.enemySprites,clsSpriteBattler:new(self.viewport2, enemy))
    end
end
-- 设置敌人精灵活动性
function clsSpriteSetBattle:setEnemyActive(active)
	for _,sprite in pairs(self.enemySprites) do
		if sprite.battler.hidden or sprite.battler:isDead() then
			sprite.active = false
		else
			sprite.active = active
		end
		self.reader.enemyActive = active
	end
end
-- 获取敌人精灵活动性
function clsSpriteSetBattle:enemyActive()
	return self.reader.enemyActive
end
-- 设置敌人精灵侦听器
function clsSpriteSetBattle:setTouchListener()
	for _,sprite in pairs(self.enemySprites) do
		sprite:setTouchListener()
	end
end
-- 获取被触屏敌人编号
function clsSpriteSetBattle:getEnemyTouchIndex()
	for _,sprite in pairs(self.enemySprites) do
		if sprite.touchListener ~= nil and sprite.touchListener.event.type == 0 and self.reader.enemyActive then
			sprite.touchListener.event:setup(-1,-1,-1)
			self.reader.enemyIndex = sprite.battler:position()
		end
	end
	return self.reader.enemyIndex
end
function clsSpriteSetBattle:setEnemyTouchIndex(index)
	self.reader.enemyIndex = index
end
-- 更新
function clsSpriteSetBattle:update()
	for _,sprite in pairs(self.enemySprites) do
		sprite:update()
	end
end