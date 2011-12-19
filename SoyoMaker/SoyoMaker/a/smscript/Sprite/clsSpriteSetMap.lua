--地图场景视图组。
clsSpriteSetMap = {}
clsSpriteSetMap.__index = clsSpriteSetMap
-- 初始化对像
function clsSpriteSetMap:new()
	local self = {}
	setmetatable(self,clsSpriteSetMap)
	self:createViewports()
	self:createTilemap()
	self:createCharacters()
	--self:createParallax()
	return self
end
-- 生成显示端口
function clsSpriteSetMap:createViewports()
	self.viewport = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())   -- 背景层
	self.viewport1 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 精灵层
    self.viewport2 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 前景层
   	self.viewport3 = clsViewport:new(0,0,globalGameEngine:getWidth(),globalGameEngine:getHeight())  -- 交互层
   	self.viewport1:setZ(30)
    self.viewport2:setZ(200)
    self.viewport3:setZ(100)
end
-- 生成地图元件
function clsSpriteSetMap:createTilemap()
	self.tilemap = clsTileMap:new()
	self.tilemap:setSprite(clsSpriteMap:new(self.viewport2),clsSpriteMap:new(self.viewport))
	self.tilemap:setup(globalGameMap.map,globalGameMap.displayX,globalGameMap.displayY,globalGameEngine:getWidth(),globalGameEngine:getHeight(),globalGameEngine:getRatedFps())
end
-- 生成远景
function clsSpriteSetMap:createParallax()
	self.window = clsWindowBase:new(self.viewport3,0,globalGameEngine:getHeight() - 128 - 24,globalGameEngine:getWidth(),128)
	self.window:setVisible(true)
	self.window:setOpenness(0)
	self.window:setTouchListener(clsTouchListener:new())
	self.window:open()
	self.window.active = true
	self.windowSelect = clsWindowSelect:new(self.viewport3,128,0,400,200,32)
	self.windowSelect:setup(1)
	self.windowSelect:setVisible(true)
	self.windowSelect:setTouchListener(clsTouchListener:new())
	self.windowSelect.active = true
	self.commands = {[0] = "物品",[1] = "特技",[2] = "装备",[3] = "状态",[4] = "存档",[5] = "结束"}
	self.windowCommand = clsWindowCommand:new(self.viewport3,128,self.commands,1,0,32)
	self.windowCommand:setVisible(true)
	self.windowCommand:setTouchListener(clsTouchListener:new())
	self.windowCommand.active = true
	self.windowStatus = clsWindowBattleStatus:new(self.viewport3,0,200,256+16,128)
	self.windowStatus:setTouchListener(clsTouchListener:new())
	self.windowStatus:setVisible(true)
end
-- 生成角色活动块x
function clsSpriteSetMap:createCharacters()
	self.characterSprites = {}
	for k,v in pairs(globalGameMap.npcs) do
		self.characterSprites[k] = clsSpriteCharacter:new(self.viewport1,v)
	end
	self.player = clsSpriteCharacter:new(self.viewport1,globalGamePlayer)
end
-- 更新
function clsSpriteSetMap:update()
	self.tilemap:position(globalGameMap.displayX,globalGameMap.displayY)
	self.tilemap:update()
	for _,v in pairs(self.characterSprites) do
		v:update()
	end
	--self.windowSelect:update()
	--self.windowCommand:update()
	self.player:update()
	--self.window:setX(self.window:x() + 1)
	--self.window:update()
end