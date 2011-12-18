--[[
  description:地图层。
  author:wp_g4
  date:2011/12/18
--]]

--类结构定义
clsMapLayer={}
setmetatable(clsMapLayer,clsLayer)
clsMapLayer.__index=clsMapLayer

--重定向父类paintLayer(painter)方法
clsMapLayer.paintLayerF=clsMapLayer.paintLayer

--字段
clsMapLayer.bufferedBgImage=nil    --背景缓冲图
clsMapLayer.bufferedBgPainter=nil  --背景缓冲画笔
clsMapLayer.bufferedFgImage=nil    --前景缓冲图
clsMapLayer.bufferedFgPainter=nil  --前景缓冲画笔
clsMapLayer.player=nil             --当前要显示的player
clsMapLayer.map=nil                --当前地图

--构造器
function clsMapLayer:new(x,y,width,height)
  local self = clsLayer:new(x,y,width,height)
  setmetatable(self,clsMapLayer)
  return self
end

--绘制自身
function clsMapLayer:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
end

function clsMapLayer:onTouch(x,y,type)
  
end

--通知内容变更
function clsMapLayer:notifyRefresh()
  --更新当前player
  self.player=globalGameData.playerTroop:curDisplayPlayer()
  self.map=globalDictionary:getMap(self.player.mapId)
  --创建bufferImage
  self.bufferedBgImage=smImageFactory:createImage(self.width,self.height)
  self.bufferedBgPainter=self.bufferedBgImage:getPainter()
  self.bufferedFgImage=smImageFactory:createImage(self.width,self.height)
  self.bufferedFgPainter=self.bufferedFgImage:getPainter()
  --图层数
  local layerNum=table.getn(self.map.layers)
  --层可显示的行、列数
  local rowNum=self.width/self.map.cellHeight
  local colNum=self.height/self.map.cellWidth
  --根据玩家当前位置计算起始单元格编号
  local px=self.player.col
  local py=g_gameData.player.row
end

function clsMapLayer:toString()
  local str="clsMapLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end


