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

clsMapLayer.bufferRow=nil          --缓冲区起始行号
clsMapLayer.bufferCol=nil          --缓冲区起始列号
clsMapLayer.bufferWidth=nil        --缓冲区宽度
clsMapLayer.bufferHeight=nil       --缓冲区高度

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
  --背景
  painter:drawImage(self.bufferedBgImage,0,0,globalUIConst.anchor.LT)
  --player、NPC
  --前景
  painter:drawImage(self.bufferedFgImage,0,0,globalUIConst.anchor.LT)
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

--切换地图
function clsMapLayer:changeMap(curMap)
  --计算缓冲区大小
  self.bufferWidth=(math.ceil(self.width/curMap.cellWidth)+self.bufferBound)*curMap.cellWidth
  self.bufferHeight=(math.ceil(self.height/curMap.cellHeight)+self.bufferBound)*curMap.cellHeight
  --创建缓冲前后景
  self.bufferedBgImage=smImageFactory:createImage(self.bufferWidth,self.bufferHeight)
  self.bufferedBgPainter=self.bufferedBgImage:getPainter()
  self.bufferedFgImage=smImageFactory:createImage(self.bufferWidth,self.bufferHeight)
  self.bufferedFgPainter=self.bufferedBgImage:getPainter()
  --根据player当前位置计算缓冲区起始行、列号
  self.bufferCol=self.player.col-math.floor(self.bufferWidth/curMap.cellWidth/2)
  self.bufferRow=self.player.row-math.floor(self.bufferHeight/curMap.cellHeight/2)
  if self.bufferCol<0 then
    self.bufferCol=0
  end
  if self.bufferCol>curMap.colNum-self.bufferWidth/curMap.cellWidth then
    self.bufferCol=curMap.colNum-self.bufferWidth/curMap.cellWidth
  end
  if self.bufferRow<0 then
    self.bufferRow=0
  end
  if self.bufferRow>curMap.rowNum-self.bufferHeight/curMap.cellHeight then
    self.bufferRow=curMap.rowNum-self.bufferHeight/curMap.cellHeight
  end
  --绘制缓冲图
  for i=1,table.getn(curMap.layers) do
    local layer=curMap.layers[i]
    for j=self.bufferRow,self.bufferRow+self.bufferHeight/curMap.cellHeight-1 do
      for k=self.bufferCol,self.bufferCol+self.bufferWidth/curMap.cellWidth-1 do
        --smLog:info("j="..j.." k="..k)
        local cell=layer[j+1][k+1]
        local imageSetId=cell[1]
        local tiledIndex=cell[2]
        if imageSetId~=-1 and globalGameData.map.imageSets[imageSetId]==nil then
          smLog:info("加载图集")
          local imageSet=curMap.tilesets[imageSetId]
          local path=imageSet.path
          smLog:info(globalGame.PATH..path)
          globalGameData.map.imageSets[imageSetId]=smImageFactory:createImage(globalGame.PATH..path)
          smLog:info(globalGameData.map.imageSets[imageSetId])
        end
        if imageSetId~=-1 then
          local imgColNum=globalGameData.map.imageSets[imageSetId]:getWidth()/curMap.cellWidth
          local imgsx=math.mod(tiledIndex,imgColNum)*curMap.cellWidth
          local imgsy=math.floor(tiledIndex/imgColNum)*curMap.cellWidth
          if layer.deepth<0 then
            --背景
            self.bufferedBgPainter:drawImage(globalGameData.map.imageSets[imageSetId],imgsx,imgsy,curMap.cellWidth,curMap.cellHeight,
               (k-self.bufferCol)*curMap.cellWidth,(j-self.bufferRow)*curMap.cellHeight,globalUIConst.anchor.LT)
          elseif layer.deepth>0 then
            --前景
            self.bufferedFgPainter:drawImage(globalGameData.map.imageSets[imageSetId],imgsx,imgsy,curMap.cellWidth,curMap.cellHeight,
               (k-self.bufferCol)*curMap.cellWidth,(j-self.bufferRow)*curMap.cellHeight,globalUIConst.anchor.LT)
          end
        end
      end
    end
  end
  self.map=curMap
end

function clsMapLayer:toString()
  local str="clsMapLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end


