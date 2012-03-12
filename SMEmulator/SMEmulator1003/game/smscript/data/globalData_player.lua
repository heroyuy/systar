--[[
  description:游戏数据管理器 Player队伍定义
  author:wp_g4
  date:2011/12/18
--]]

globalData.playerTroop={}      --玩家队伍

globalData.playerTroop.players={}  --Player列表
globalData.playerTroop.curDisplayPlayerId=0   --当前显示的Player的ID

function globalData.playerTroop:curDisplayPlayer()
  return self.players[self.curDisplayPlayerId]
end

