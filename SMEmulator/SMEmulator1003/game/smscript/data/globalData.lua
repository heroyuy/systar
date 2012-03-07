--[[
  description:游戏数据管理器 模块定义
  author:wp_g4
  date:2011/12/12
--]]

--[[
   数据结构说明：
   字典类型的数据在游戏过程中不会修改，不需要区分“新游戏”和“加载游戏”；非字典数据在游戏过程中会变化，游戏存档时
需要保存，“新游戏”和“加载游戏”时也不一样。
 1、vocation      字典
 2、player        非字典
 3、skill         字典
 4、item          字典
 5、equip         字典
 6、enemy         非字典（但游戏存档时不需要保存，游戏设计时战斗中不应该有存档）
 7、enemytroop    字典
 8、status        字典
 9、map           字典
 10、npc          非字典
 11、animation    字典
 12、config       字典
--]]


--初始化
globalData={}

--字段：公共模块
globalData.playerTroop={}      --玩家队伍
globalData.proxy={}            --外部代理
--字段
globalData.curMap=nil          --地图
globalData.npcs=nil            --npc列表
globalData.imageSets={}        --地图图集
globalData.updateSwitch=false  -- 模型更新开关

--字段：私有
globalData.gameType=-1         --游戏类型（-1 新游戏   0-n 游戏存档）

--============游戏加载、保存============
--新游戏：从字典中初始化游戏
function globalData:newGame()
  smLog:info("newGame");
  self.gameType=-1
  --初始化PlayerTroop
  for _,v in ipairs(globalDictionary.config.playersIndex) do
    smLog:info("new player")
    local player=clsPlayer:new()
    local playerDict=globalDictionary.players[v]
    --直接取值字段值
    player.id=playerDict.index
    player.name=playerDict.name
    player.desc=playerDict.intro
    player.headImageName=playerDict.headImg
    if player.headImageName then
      player.headImage=smImageFactory:createImage(smGameEngine:getGamePath()..player.headImageName)
    end
    player.charImageName=playerDict.charImg
    if player.charImageName then
      player.charImage=smImageFactory:createImage(smGameEngine:getGamePath()..player.charImageName)
    end
    player.battlerImageName=playerDict.battlerImg
    if player.battlerImageName then
      player.battlerImage=smImageFactory:createImage(smGameEngine:getGamePath()..player.battlerImageName)
    end
    player.vocationId=playerDict.vocationIndex
    player.level=playerDict.startLev
    --查表获取字段值
    player.exp=playerDict.expList[player.level]
    player.hp=playerDict.maxHpList[player.level]
    player.sp=playerDict.maxSpList[player.level]
    player.str=playerDict.strList[player.level]
    player.agi=playerDict.agiList[player.level]
    player.int=playerDict.intList[player.level]
    player.vit=playerDict.vitList[player.level]
    player.dex=playerDict.dexList[player.level]
    player.luck=playerDict.lucList[player.level]
    --查配置获取字段值
    player.mapId=globalDictionary.config.curMapIndex
    player.row=globalDictionary.config.row
    player.col=globalDictionary.config.col
    player.face=globalDictionary.config.face
    player.moveDelegate=self
    self.playerTroop.players[player.id]=player
  end
  self.playerTroop.curDisplayPlayerId=globalDictionary.config.playersIndex[1] --TODO 此值应该读配置
  --初始化NPC信息表
  self.npcs={}
  --打开Model更新开关
  self.updateSwitch=true
end

--加载游戏:从第index个存档初始化游戏
function globalData:loadGame(index)
end

--保存游戏:保存游戏到第index个存档
function globalData:saveGame(index)
end

--============NPC相关操作============
--获取指定id的NPC
function globalData:getNPC(id)
  if self.npcs[id]==nil then
    --指定id的NPC不存在
    local npc=clsNPC:new()
    local npcDict=nil
    if self.gameType==-1 then
      --新游戏
      smLog:info("getNPC:"..id)
      npcDict=globalDictionary.npcs[id]
    else
      --加载存档
      npcDict=nil  --TODO 从存档加载NPC信息
    end
    npc.id=npcDict.index
    npc.mapId=npcDict.mapIndex
    npc.row=npcDict.row
    npc.col=npcDict.col
    npc.name=npcDict.name
    npc.charImageName=npcDict.charImg
    if npc.charImageName then
      npc.charImage=smImageFactory:createImage(smGameEngine:getGamePath()..npc.charImageName)
    end
    npc.headImageName=npcDict.headImg
    if npc.headImageName then
      npc.headImage=smImageFactory:createImage(smGameEngine:getGamePath()..npc.headImageName)
    end
    npc.face=npcDict.face
    npc.moveType=npcDict.moveType
    npc.speedLevel=npcDict.speedLevel
    npc.penetrable=npcDict.penetrable
    npc.moveDelegate=self
    self.npcs[id]=npc
  end
  return self.npcs[id]
end

--更新游戏模型
function globalData:update()
  if not self.updateSwitch then
    --如果更新开关未开启直接返回
    return
  end
  --更新玩家
  if self.playerTroop:curDisplayPlayer() then
    self.playerTroop:curDisplayPlayer():update()
  end
  --更新当前地图上的NPC
  if self.curMap then
    for k,v in pairs(self.curMap.npcs) do
      local npc=self:getNPC(v)
      npc:update()
    end
  end
end

--============character的moveDelegate============
--检查目的地是否可达
function globalData:checkCell(character,row,col)
  --检查地图边界
  if row<0 or row>self.curMap.rowNum-1 or col<0 or col>self.curMap.colNum-1 then
    return false
  end
  --检查地图通行度
  if self.curMap.areas[row+1][col+1]==-1 then
    return false
  end
  --检查目标位置是否有其它不可穿透的character
  local curRow=nil
  local curCol=nil
    --(1)、检查玩家位置
    curRow,curCol=self.playerTroop:curDisplayPlayer():getHoldingCell()
    if curRow==row and curCol==col and character~= self.playerTroop:curDisplayPlayer() then
      return false
    end
    --(2)、检查npc位置
    for k,v in pairs(self.curMap.npcs) do
      local npc=self:getNPC(v)
      curRow,curCol=npc:getHoldingCell()
      if curRow==row and curCol==col and character~= npc then
      return false
      end
    end  
  return true
end

--获取当前玩家的位置
function globalData:curPlayerLocation()
  return self.playerTroop:curDisplayPlayer():getHoldingCell()
end


