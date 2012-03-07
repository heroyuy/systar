--[[
  description:地图场景
  author:wp_g4
  date:2011/12/18
--]]

--结构定义
clsSceneMap = {}
setmetatable(clsSceneMap,clsScene)
clsSceneMap.__index = clsSceneMap

--字段
clsSceneMap.curPlayer=nil      --当前player
clsSceneMap.mapBgLayer=nil     --背景
clsSceneMap.mapFgLayer=nil     --前景
clsSceneMap.spriteLayer=nil    --精灵层
clsSceneMap.aStar=nil          --A*寻路工具

--构造器
function clsSceneMap:new()
	local self = clsScene:new()
	setmetatable(self,clsSceneMap)
	return self
end

-- 开始
function clsSceneMap:onStart()
  smLog:info("地图场景启动")
  --注册接收通知
  globalNotifier:addObserver(globalConst.NotifyCMD.Character.MOVED,self,self.characterMoved)
  self.curPlayer=globalData.playerTroop:curDisplayPlayer()
  self.curPlayer.moveDelegate=self
  local curMap=globalDictionary:getMap(self.curPlayer.mapId)
  if globalData.curMap~=curMap then
    --切换地图
    self:changeMap(curMap)
  end
end

-- 更新
function clsSceneMap:update()
  --TODO 此处画面更新存在问题，效率不高，需要改进
  --计算新的窗口位置
  local viewport=self:checkViewport()
  --背景移动
  self.mapBgLayer:trackViewport(viewport)
  --前景移动
  if self.mapFgLayer then
    self.mapFgLayer:trackViewport(viewport)
  end
  --player移动
  
  local playerSprite=self.spriteLayer:childWithTag(self.curPlayer.id)
  local px,py=self:calculateCharacterLocation(self.curPlayer)
  px=px-self.curPlayer.charImage:getWidth()/4/2
  py=py+globalData.curMap.cellHeight/2-self.curPlayer.charImage:getHeight()/4
  playerSprite.x,playerSprite.y=px-viewport.x,py-viewport.y
  playerSprite.frameIndex=self.curPlayer:getCurFrameIndex()
  
  --npc移动
  --[[
  for k,v in pairs(globalData.curMap.npcs) do
    local npc=globalData:getNPC(v)
    local npcSprite=self.spriteLayer:childWithTag(npc.id)
    local nx,ny=self:calculateCharacterLocation(npc)
    nx=nx-npc.charImage:getWidth()/4/2
    ny=ny+globalData.curMap.cellHeight/2-npc.charImage:getHeight()/4
    npcSprite.x,npcSprite.y=nx-viewport.x,ny-viewport.y
    npcSprite.frameIndex=npc:getCurFrameIndex()
  end
  --]]
end

-- 退出
function clsSceneMap:onStop()
  smLog:info("地图场景退出")
  smAudioPlayer:stop()
end

function clsSceneMap:changeMap(map)
  globalData.curMap=map
  self.aStar=clsAStar:new(map.areas)
  --清除所有layer
  globalGame.rootLayer:clear()
  --加载地图图集
  for _,v in pairs(globalData.curMap.tilesets) do
    if globalData.imageSets[v.id]==nil then
      globalData.imageSets[v.id]=smImageFactory:createImage(smGameEngine:getGamePath()..v.path)
    end
  end
  --加载背景
  local bgLayers=clsList:new()
  for i=1,table.getn(globalData.curMap.layers) do
    local layer=globalData.curMap.layers[i]
    if layer.deepth<0 then
      bgLayers:add(layer)
    end
  end
  self.mapBgLayer=clsTiledMapLayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
  self.mapBgLayer.delegate=self
  local viewport=self:checkViewport()
  local params={layers=bgLayers,colNum=globalData.curMap.colNum,rowNum=globalData.curMap.rowNum,
     cellWidth=globalData.curMap.cellWidth,cellHeight=globalData.curMap.cellHeight,viewport=viewport}
  self.mapBgLayer:init(params)
  globalGame.rootLayer:addChild(self.mapBgLayer)
  --加载精灵层
  self.spriteLayer=clsUILayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
  self.spriteLayer.enabled=false
    --玩家
    local playerSprite=clsUISprite:new(self.curPlayer.charImage,
       self.curPlayer.charImage:getWidth()/4,self.curPlayer.charImage:getHeight()/4)
    playerSprite.tag=self.curPlayer.id
    playerSprite.z=self.curPlayer.row
    local px,py=self:calculateCharacterLocation(self.curPlayer)
    px=px-self.curPlayer.charImage:getWidth()/4/2
    py=py+globalData.curMap.cellHeight/2-self.curPlayer.charImage:getHeight()/4
    playerSprite.x,playerSprite.y=px-viewport.x,py-viewport.y
    self.spriteLayer:addChild(playerSprite)
    --NPC
    for k,v in pairs(globalData.curMap.npcs) do
      local npc=globalData:getNPC(v)
      npc.moveDelegate=self
      local npcSprite=clsUISprite:new(npc.charImage,
        npc.charImage:getWidth()/4,npc.charImage:getHeight()/4)
      npcSprite.tag=npc.id
      npcSprite.z=npc.row
      local nx,ny=self:calculateCharacterLocation(npc)
      nx=nx-npc.charImage:getWidth()/4/2
      ny=ny+globalData.curMap.cellHeight/2-npc.charImage:getHeight()/4
      npcSprite.x,npcSprite.y=nx-viewport.x,ny-viewport.y
      self.spriteLayer:addChild(npcSprite)
    end
    globalGame.rootLayer:addChild(self.spriteLayer)
  --加载前景
  local fgLayers=clsList:new()
  for i=1,table.getn(globalData.curMap.layers) do
    local layer=globalData.curMap.layers[i]
    if layer.deepth>0 then
      fgLayers:add(layer)
    end
  end
  if fgLayers:size()>0 then
    self.mapFgLayer=clsTiledMapLayer:new(0,0,smGameEngine:getWidth(),smGameEngine:getHeight())
    self.mapFgLayer.enabled=false
    local params={layers=fgLayers,colNum=globalData.curMap.colNum,rowNum=globalData.curMap.rowNum,
       cellWidth=globalData.curMap.cellWidth,cellHeight=globalData.curMap.cellHeight,viewport=viewport}
    self.mapFgLayer:init(params)
    globalGame.rootLayer:addChild(self.mapFgLayer)
  end
end

--地图点击
function clsSceneMap:mapTapped(target,row,col)
  smLog:info("逻辑坐标: row="..row.." col="..col)
  --向player发送移动命令
  --(1)清除原有的行走命令
  self.curPlayer.moveSequence:clear()
  --(2)获取寻路起点
  local curRow=self.curPlayer.row
  local curCol=self.curPlayer.col
  --(3)起点修正
  if self.curPlayer.step~=0 then
    --player行走中
    if self.curPlayer.face==0 then
      --上
      curRow=curRow-1
    elseif self.curPlayer.face==1 then
      --下
      curRow=curRow+1
    elseif self.curPlayer.face==2 then
      --左
      curCol=curCol-1
    elseif self.curPlayer.face==3 then
      --右
      curCol=curCol+1
    end
  end
  --(4)寻路
  local directions=self.aStar:searchDirection(curRow+1,curCol+1,row,col)
  --(5)发送行走命令(TODO 可优化)
  while directions:size()>0 do
    local direction=directions:poll()
    self.curPlayer.moveSequence:offer(direction)
  end
end

--计算viewport
function clsSceneMap:checkViewport()
  local px,py=self:calculateCharacterLocation(self.curPlayer)
  local width=smGameEngine:getWidth()
  local height=smGameEngine:getHeight()
  local vx=px-width/2
  local vy=py-height/2
  if vx<0 then
    vx=0
  end
  if vx>globalData.curMap.colNum*globalData.curMap.cellWidth-width then
    vx=globalData.curMap.colNum*globalData.curMap.cellWidth-width
  end
  if vy<0 then
    vy=0
  end
  if vy>globalData.curMap.rowNum*globalData.curMap.cellHeight-height then
    vy=globalData.curMap.rowNum*globalData.curMap.cellHeight-height
  end
  return {x=vx,y=vy,width=width,height=height}
end

--计算characher当前物理坐标(characher所在单元格正中心坐标+行走修正)
function clsSceneMap:calculateCharacterLocation(character)
  local px=character.col*globalData.curMap.cellWidth+globalData.curMap.cellWidth/2
  local py=character.row*globalData.curMap.cellHeight+globalData.curMap.cellHeight/2
  --根据player当前面向和行走状态进行位置修正
  if character.face==0 then
    --上
    py=py-globalData.curMap.cellHeight/4*character.step
  elseif character.face==1 then
    --下
    py=py+globalData.curMap.cellHeight/4*character.step
  elseif character.face==2 then
    --左
    px=px-globalData.curMap.cellWidth/4*character.step
  elseif character.face==3 then
    --右
    px=px+globalData.curMap.cellWidth/4*character.step
  end
  return px,py
end

--============character的moveDelegate============
function clsSceneMap:checkCell(character,row,col)
  --检查地图边界
  if row<0 or row>globalData.curMap.rowNum-1 or col<0 or col>globalData.curMap.colNum-1 then
    return false
  end
  --检查地图通行度
  if globalData.curMap.areas[row+1][col+1]==-1 then
    return false
  end
  --检查目标位置是否有其它不可穿透的character
  local curRow=nil
  local curCol=nil
    --(1)、检查玩家位置
    curRow,curCol=self.curPlayer:getHoldingCell()
    if curRow==row and curCol==col and character~= self.curPlayer then
      return false
    end
    --(2)、检查npc位置
    for k,v in pairs(globalData.curMap.npcs) do
      local npc=globalData:getNPC(v)
      curRow,curCol=npc:getHoldingCell()
      if curRow==row and curCol==col and character~= npc then
      return false
      end
    end  
  return true
end

--============处理通知============
--character移动
function clsSceneMap:characterMoved(param)
  --如果character的行有变化需要修改对应sprite的z值
  if param.rowChanged then
    local sprite=self.spriteLayer:childWithTag(param.character.id)
    sprite:changeZ(param.character.row)
  end
  
  if param.character~=self.curPlayer then
    local npcSprite=self.spriteLayer:childWithTag(param.character.id)
    if param.direction==0 then
      --上
      npcSprite.y=npcSprite.y-globalData.curMap.cellHeight/4
    elseif param.direction==1 then
      --下
      npcSprite.y=npcSprite.y+globalData.curMap.cellHeight/4
    elseif param.direction==2 then
      --左
      npcSprite.x=npcSprite.x-globalData.curMap.cellWidth/4
    elseif param.direction==3 then
      --右
      npcSprite.x=npcSprite.x+globalData.curMap.cellWidth/4
    end
    npcSprite.frameIndex=param.character:getCurFrameIndex()
  end
  
end
