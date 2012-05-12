-- 战斗显示用精灵，clsGameBattler 类的实例监视、活动块的状态的视图。
clsSpriteBattler = {}
setmetatable(clsSpriteBattler,clsSpriteBase)
clsSpriteBattler.__index = clsSpriteBattler
-- 构造体
function clsSpriteBattler:new(viewport,battler)
	local self = {}
	self = clsSpriteBase:new(viewport)
	self.updateF = self.update
	setmetatable(self,clsSpriteBattler)
	-- 常量
	self.WHITEN    = 1                      -- 闪光（开始行动）
  	self.BLINK     = 2                      -- 明灭（伤害）
  	self.APPEAR    = 3                      -- 出现（出现、复苏）
  	self.DISAPPEAR = 4                      -- 消失（逃跑）
  	self.COLLAPSE  = 5                      -- 倒下（无法战斗）
  	-- 属性
  	self.battler = battler                  -- 模块（战斗者）
  	self.battlerVisible = false             -- 可见性（战斗者）
  	self.effectType = 0                     -- 效果类型
    self.effectDuration = 0                 -- 效果剩馀时间
	return self
end
-- 释放
function clsSpriteBattler:dispose()
end
-- 更新画面
function clsSpriteBattler:update()
	
	if self.battler == nil then
      self:clearImage() -- 清除图片
    else
      self.useSprite = self.battler:isUseSprite()
      if self.useSprite then
        self:setX(self.battler:screenX())
        self:setY(self.battler:screenY())
        self:setZ(self.battler:screenZ())
        self:updateBattlerImage()
      end
      self:setupNewEffect()
      self:updateEffect()
    end
    self:updateF() -- 继承父类更新
end
-- 更新传送源图档片
function clsSpriteBattler:updateBattlerImage()
	if self.battler:battleImg() ~= self.battleImg or
    	self.battler:battleTone() ~= self.battleTone then
      	self.battleImg = self.battler:battleImg()
      	self.battleTone = self.battler:battleTone()
      	self:setImage(smImage:createImage(self.battleImg))
      	self:setTone(self.battleTone:red(),self.battleTone:green(),self.battleTone:blue())
     	self:srcRect():set(0,0,self:image():getWidth(),self:image():getHeight())
      	self:setOx(self:width() / 2)
      	self:setOy(self:height() / 2)
      	self:setVisible(true)
      	if (self.battler:isDead() or self.battler.hidden) then
      		self:setAlpha(0)
    	end
    end
    
end
-- 设置新的效果
function clsSpriteBattler:setupNewEffect()
	if self.battler.whiteFlash then
      self.effectType = self.WHITEN
      self.effectDuration = 6
      self.battler.whiteFlash = false
    end
    if self.battler.blink then
      self.effectType = self.BLINK
      self.effectDuration = 7
      self.battler.blink = false
    end
    if not self.battlerVisible and self.battler:isExist() then
      self.effectType = self.APPEAR
      self.effectDuration = 15
      self.battlerVisible = true
    end
    if self.battlerVisible and self.battler.hidden then
      self.effectType = self.DISAPPEAR
      self.effectDuration = 15
      self.battlerVisible = false
    end
    if self.battler.collapse then
    	self.effectType = self.COLLAPSE
    	self.effectDuration = 15
    	self.battler.collapse = false
    	self.battlerVisible = false
    end
    if self.battler.aniIndex ~= -1 then
      self:startAnimation(globalDataLoader:getAnimation(self.battler.aniIndex), self.battler.aniMirror)
      self.battler.aniIndex = -1
    end
end
-- 更新效果
function clsSpriteBattler:updateEffect()
	if self.effectDuration > 0 then
      self.effectDuration = self.effectDuration - 1
      if self.effectType == self.WHITEN then
        self:updateWhiten()
      elseif self.effectType == self.BLINK then
        self:updateBlink()
      elseif self.effectType == self.APPEAR then
        self:updateAppear()
      elseif self.effectType == self.DISAPPEAR then
        self:updateDisappear()
      elseif self.effectType == self.COLLAPSE then
        self:updateCollapse()
      end
    end
end
-- 更新闪光效果
function clsSpriteBattler:updateWhiten()
    local alpha = 255 - (6 - self.effectDuration) * 11
    if self.effectDuration ~= 0 then
    	self:setAlphaTone(alpha,255,255,128)
    elseif self.effectDuration == 0 then
    	self:setAlphaTone(255,0,0,0)
    end
end
-- 更新明灭效果
function clsSpriteBattler:updateBlink()
    --self:setAlphaTone(255,0,0,0)
    self:setVisible(self.effectDuration % 3 < 2)
end
-- 更新出现效果
function clsSpriteBattler:updateAppear()
	local alpha = (15 - self.effectDuration) * 17
	if alpha > 255 then
		alpha = 255
	end
	self:setAlphaTone(alpha,0,0,0)
end
-- 更新消失效果
function clsSpriteBattler:updateDisappear()
	local alpha = 255 - (15 - self.effectDuration) * 17
	self:setAlphaTone(alpha,0,0,0)
end
-- 更新倒下效果
function clsSpriteBattler:updateCollapse()
	local alpha = 255 - (15 - self.effectDuration) * 17
	if self.effectDuration ~= 0 then
		self:setAlphaTone(alpha,255,0,0)
	elseif self.effectDuration == 0 then
		self:setAlpha(0)
	end
end