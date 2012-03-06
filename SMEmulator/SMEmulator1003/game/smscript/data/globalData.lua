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
globalData.map={}              --地图
globalData.npcs=nil            --npc列表
globalData.proxy={}            --外部代理
--字段:标识
globalData.updateSwitch=false  -- 模型更新开关


--新游戏：从字典中初始化游戏
function globalData:newGame()
  smLog:info("newGame");
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
    self.playerTroop.players[player.id]=player
  end
  self.playerTroop.curDisplayPlayerId=globalDictionary.config.playersIndex[1] --TODO 此值应该读配置
  --初始化NPC信息表
  self.npcs={}
end

--加载游戏:从第index个存档初始化游戏
function globalData:loadGame(index)
end

--更新游戏模型
function globalData:update()
  if self.updateSwitch then
    if self.playerTroop:curDisplayPlayer() then
      self.playerTroop:curDisplayPlayer():update()
    end
  end
end