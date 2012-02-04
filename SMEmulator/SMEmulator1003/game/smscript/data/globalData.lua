--[[
  description:游戏数据管理器 模块定义
  author:wp_g4
  date:2011/12/12
--]]

--初始化
globalData={}

--字段：公共模块
globalData.playerTroop={}      --玩家队伍
globalData.map={}              --地图
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
      player.headImage=smImageFactory:createImage(globalGame.PATH..player.headImageName)
    end
    player.charImageName=playerDict.charImg
    if player.charImageName then
      player.charImage=smImageFactory:createImage(globalGame.PATH..player.charImageName)
    end
    player.battlerImageName=playerDict.battlerImg
    if player.battlerImageName then
      player.battlerImage=smImageFactory:createImage(globalGame.PATH..player.battlerImageName)
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
  self.playerTroop.curDisplayPlayerId=0 --TODO 此值应该读配置
  self.updateSwitch=true
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