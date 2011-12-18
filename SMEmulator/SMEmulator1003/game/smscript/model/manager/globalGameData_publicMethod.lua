--[[
  description:游戏数据管理器 公共方法定义
  author:wp_g4
  date:2011/12/18
--]]

--新游戏：从字典中初始化游戏
function globalGameData:newGame()
  --初始化PlayerTroop
  for _,v in ipairs(globalDictionary.config.playersIndex) do
    local player=clsPlayer:new()
    local playerDict=globalDictionary.players[v+1]
    --直接取值字段
    player.id=playerDict.index
    player.name=playerDict.name
    player.desc=playerDict.intro
    player.headImageName=playerDict.headImg
    player.headImage=smImageFactory:create(player.headImageName)
    player.charImageName=playerDict.charImg
    player.charImage=smImageFactory:create(player.charImageName)
    player.battlerImageName=playerDict.battlerImg
    player.battlerImage=smImageFactory:create(player.battlerImageName)
    player.vocationId=playerDict.vocationIndex
    player.level=playerDict.startLev
    --查表获取字段
    
  end
end

--加载游戏:从第index个存档初始化游戏
function globalGameData:loadGame(index)
end