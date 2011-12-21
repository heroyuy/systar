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
clsMapLayer.bufferBound=0          --缓冲边缘宽度（单位：单元格）

clsMapLayer.player=nil             --当前要显示的player
clsMapLayer.map=nil                --当前地图

clsMapLayer.bufferRow=nil          --缓冲区起始行号（单位：单元格）
clsMapLayer.bufferCol=nil          --缓冲区起始列号（单位：单元格）
clsMapLayer.bufferColNum=nil       --缓冲区宽度（单位：单元格）
clsMapLayer.bufferRowNum=nil       --缓冲区高度（单位：单元格）

clsMapLayer.playerX=nil            --当前player在地图上的x坐标(以player双脚中间为基准)（单位：像素）
clsMapLayer.playerY=nil            --当前player在地图上的y坐标(以player双脚中间为基准)（单位：像素）
clsMapLayer.windowX=nil            --当前窗口在地图上的x坐标（单位：像素）
clsMapLayer.windowY=nil            --当前窗口在地图上的y坐标（单位：像素）


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
  --test
  if type==globalUIConst.touchEventType.DOWN then
    self.player.col=self.player.col-1
    smLog:info("player.col:"..self.player.col)
  end
end

--更新
function clsMapLayer:updateMapLayer()
  --查询当前玩家和地图
  self.player=globalGameData.playerTroop:curDisplayPlayer()
  local curMap=globalDictionary:getMap(self.player.mapId)
  --判断是否切换地图
  if self.map~=curMap then
    --切换地图
    self.map=curMap
    self:initMap()
  end
  --计算player当前物理坐标
  self.playerX,self.playerY=self:calculatePlayerLocation()
  --计算窗口当前在地图上的物理坐标
  self.windowX,self.windowY=self:calculateWindowLocation()
  --根据窗口坐标和缓冲坐标判断是否需要更新缓冲
  if self.windowX<self.bufferCol*self.map.cellWidth then
    --缓冲左越界
    smLog:info("--left--")
      --(1)复制可用区域
      self.bufferedBgImage:copyArea(0,0,(self.bufferColNum-1)*self.map.cellWidth,self.bufferRowNum*self.map.cellHeight,self.map.cellWidth,0)
      self.bufferedFgImage:copyArea(0,0,(self.bufferColNum-1)*self.map.cellWidth,self.bufferRowNum*self.map.cellHeight,self.map.cellWidth,0)
      --(2)修正缓冲区坐标(左移)
      self.bufferCol=self.bufferCol-1
      --(3)重绘新区域
      local refreshBufferRect={col=0,row=0,colNum=1,rowNum=self.bufferRowNum}
      self:refreshBuffer(refreshBufferRect,true)
      
  end
  if self.windowX+self.width>(self.bufferCol+self.bufferColNum)*self.map.cellWidth then
    --缓冲右越界
  end
  if self.windowY<self.bufferRow*self.map.cellHeight then
    --缓冲上越界
  end
  if self.windowY+self.height>(self.bufferRow+self.bufferRowNum)*self.map.cellHeight then
    --缓冲下越界
  end
end

--切换地图
function clsMapLayer:initMap()
  --计算缓冲区大小
  self.bufferColNum=(math.ceil(self.width/self.map.cellWidth)+self.bufferBound)
  self.bufferRowNum=(math.ceil(self.height/self.map.cellHeight)+self.bufferBound)
  --创建缓冲前后景
  self.bufferedBgImage=smImageFactory:createImage(self.bufferColNum*self.map.cellWidth,self.bufferRowNum*self.map.cellHeight)
  self.bufferedBgPainter=self.bufferedBgImage:getPainter()
  self.bufferedFgImage=smImageFactory:createImage(self.bufferColNum*self.map.cellWidth,self.bufferRowNum*self.map.cellHeight)
  self.bufferedFgPainter=self.bufferedBgImage:getPainter()
  --根据player当前位置计算缓冲区起始行、列号
  self.bufferCol=self.player.col-math.floor(self.bufferColNum/2)
  self.bufferRow=self.player.row-math.floor(self.bufferRowNum/2)
  if self.bufferCol<0 then
    self.bufferCol=0
  end
  if self.bufferCol>self.map.colNum-self.bufferColNum then
    self.bufferCol=self.map.colNum-self.bufferColNum
  end
  if self.bufferRow<0 then
    self.bufferRow=0
  end
  if self.bufferRow>self.map.rowNum-self.bufferRowNum then
    self.bufferRow=self.map.rowNum-self.bufferRowNum
  end
  --绘制缓冲图
  local paintRect={col=0,row=0,colNum=self.bufferColNum,rowNum=self.bufferRowNum}
  self:refreshBuffer(paintRect,false)
end

--绘制缓冲区
function clsMapLayer:refreshBuffer(rect,clearFlag)
  --清除
  if clearFlag then
    self.bufferedBgImage:clear(rect.col*self.map.cellWidth,rect.row*self.map.cellHeight,
       rect.colNum*self.map.cellWidth,rect.rowNum*self.map.cellHeight)
    self.bufferedFgImage:clear(rect.col*self.map.cellWidth,rect.row*self.map.cellHeight,
       rect.colNum*self.map.cellWidth,rect.rowNum*self.map.cellHeight)
  end
  --绘制
  for i=1,table.getn(self.map.layers) do
    local layer=self.map.layers[i]
    for j=rect.row,rect.row+rect.rowNum-1 do
      for k=rect.col,rect.col+rect.colNum-1 do
        --smLog:info("j="..j.." k="..k)
        local cell=layer[self.bufferRow+j+1][self.bufferCol+k+1]
        local imageSetId=cell[1]
        local tiledIndex=cell[2]
        if imageSetId~=-1 and globalGameData.map.imageSets[imageSetId]==nil then
          smLog:info("加载图集")
          local imageSet=self.map.tilesets[imageSetId]
          local path=imageSet.path
          smLog:info(globalGame.PATH..path)
          globalGameData.map.imageSets[imageSetId]=smImageFactory:createImage(globalGame.PATH..path)
          smLog:info(globalGameData.map.imageSets[imageSetId])
        end
        if imageSetId~=-1 then
          local imgColNum=globalGameData.map.imageSets[imageSetId]:getWidth()/self.map.cellWidth
          local imgsx=math.mod(tiledIndex,imgColNum)*self.map.cellWidth
          local imgsy=math.floor(tiledIndex/imgColNum)*self.map.cellWidth
          if layer.deepth<0 then
            --背景
            self.bufferedBgPainter:drawImage(globalGameData.map.imageSets[imageSetId],imgsx,imgsy,self.map.cellWidth,self.map.cellHeight,
               k*self.map.cellWidth,j*self.map.cellHeight,globalUIConst.anchor.LT)
          elseif layer.deepth>0 then
            --前景
            self.bufferedFgPainter:drawImage(globalGameData.map.imageSets[imageSetId],imgsx,imgsy,self.map.cellWidth,self.map.cellHeight,
               k*self.map.cellWidth,j*self.map.cellHeight,globalUIConst.anchor.LT)
          end
        end
      end
    end
  end
end

--计算player当前物理坐标
function clsMapLayer:calculatePlayerLocation()
  local px=self.player.col*self.map.cellWidth+self.map.cellWidth/2
  local py=self.player.row*self.map.cellHeight+self.map.cellHeight
  --根据player当前面向和行走状态进行位置修正
  if self.player.face==0 then
    --上
    py=py-self.map.cellHeight/4*self.player.step
  elseif self.player.face==1 then
    --下
    py=py+self.map.cellHeight/4*self.player.step
  elseif self.player.face==2 then
    --左
    py=py-self.map.cellWidth/4*self.player.step
  elseif self.player.face==3 then
    --右
    py=py+self.map.cellWidth/4*self.player.step
  end
  return px,py
end

--计算window当前物理坐标
function clsMapLayer:calculateWindowLocation()
  local wx=self.playerX-self.width/2
  local wy=self.playerY-self.height/2
  if wx<0 then
    wx=0
  end
  if wx>self.map.colNum*self.map.cellWidth-self.width then
    wx=self.map.colNum*self.map.cellWidth-self.width
  end
  if wy<0 then
    wy=0
  end
  if wy>self.map.rowNum*self.map.cellHeight-self.height then
    wy=self.map.rowNum*self.map.cellHeight-self.height
  end
  return wx,wy
end

function clsMapLayer:refreshBufferedImage(rect)
  
end

function clsMapLayer:toString()
  local str="clsMapLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end


