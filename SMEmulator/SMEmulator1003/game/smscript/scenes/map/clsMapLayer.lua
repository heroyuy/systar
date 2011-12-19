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
clsMapLayer.bufferBound=0          --缓冲边缘宽度（多出地图单元格尺寸的倍数）

clsMapLayer.player=nil             --当前要显示的player
clsMapLayer.map=nil                --当前地图
clsMapLayer.playerLastRow=nil      --player上次所在地图行号
clsMapLayer.playerLastCol=nil      --player上次所在地图列号
clsMapLayer.playerLastStep=nil     --player上次的行走状态（第x步）

clsMapLayer.offsetX=nil            --X方向上的偏移
clsMapLayer.offsetY=nil            --Y方向上的偏移

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
  --updateMapLayer
  self:updateMapLayer()
  --绘图
end


function clsMapLayer:onTouch(x,y,type)
  
end

--更新
function clsMapLayer:updateMapLayer()
  --查询当前玩家和地图
  self.player=globalGameData.playerTroop:curDisplayPlayer()
  local curMap=globalDictionary:getMap(self.player.mapId)
  --判断是否切换地图
  if self.map~=curMap then
    --切换地图
    self:changeMap(curMap)
  end
  --根据player位置和当前行走状态判断是否需要滚屏以及重绘缓冲
  if self.playerLastRow~=self.player.row or self.playerLastCol~=self.player.col 
     or self.playerLastStep~=self.player.step then
    
  end
end

function clsMapLayer:toString()
  local str="clsMapLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end


