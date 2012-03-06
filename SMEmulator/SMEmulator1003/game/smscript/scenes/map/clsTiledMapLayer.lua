--[[
  description:地图层。
  author:wp_g4
  date:2011/12/18
--]]

--类结构定义
clsTiledMapLayer={}
setmetatable(clsTiledMapLayer,clsUILayer)
clsTiledMapLayer.__index=clsTiledMapLayer

--重定向父类paintLayer(painter)方法
clsTiledMapLayer.paintLayerF=clsTiledMapLayer.paintLayer

--字段
clsTiledMapLayer.bufferedImage=nil    --缓冲图
clsTiledMapLayer.bufferedPainter=nil  --缓冲画笔
clsTiledMapLayer.bufferBound=1        --缓冲边缘宽度（单位：单元格）

clsTiledMapLayer.layers=nil           --贴图层数据
clsTiledMapLayer.colNum=0             --贴图列数
clsTiledMapLayer.rowNum=0             --贴图行数
clsTiledMapLayer.cellWidth=32         --贴图单元格宽度
clsTiledMapLayer.cellHeight=32        --贴图单元格高度

clsTiledMapLayer.bufferRow=nil        --缓冲区起始行号（单位：单元格）
clsTiledMapLayer.bufferCol=nil        --缓冲区起始列号（单位：单元格）
clsTiledMapLayer.bufferColNum=nil     --缓冲区宽度（单位：单元格）
clsTiledMapLayer.bufferRowNum=nil     --缓冲区高度（单位：单元格）

clsTiledMapLayer.viewport=nil         --当前相机取景区域（视口）（单位：像素）

--构造器
function clsTiledMapLayer:new(x,y,width,height)
  local self = clsUILayer:new(x,y,width,height)
  setmetatable(self,clsTiledMapLayer)
  return self
end

--设置帖图数据
--参数为 {layers={...},colNum=10,rowNum=10,cellWidth=32,cellHeight=32,viewport={...}}
function clsTiledMapLayer:init(params)
  self.layers=params.layers
  self.colNum=params.colNum
  self.rowNum=params.rowNum
  self.cellWidth=params.cellWidth
  self.cellHeight=params.cellHeight
  self.viewport=params.viewport
  --计算缓冲区大小
  self.bufferColNum=(math.ceil(self.width/self.cellWidth)+self.bufferBound)
  self.bufferRowNum=(math.ceil(self.height/self.cellHeight)+self.bufferBound)
  --创建缓冲
  self.bufferedImage=smImageFactory:createImage(self.bufferColNum*self.cellWidth,self.bufferRowNum*self.cellHeight)
  self.bufferedPainter=self.bufferedImage:getPainter()
  --根据当前视口计算缓冲区起始行、列号
  self.bufferCol=math.floor(self.viewport.x/self.cellWidth)
  self.bufferRow=math.floor(self.viewport.y/self.cellHeight)
  --绘制缓冲图
  local paintRect={col=0,row=0,colNum=self.bufferColNum,rowNum=self.bufferRowNum}
  self:refreshBuffer(paintRect,false)
end

--绘制自身
function clsTiledMapLayer:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
  --绘图
  painter:drawImage(self.bufferedImage,self.viewport.x-self.bufferCol*self.cellWidth,self.viewport.y-self.bufferRow*self.cellHeight,
     self.viewport.width,self.viewport.height,0,0,smUIConst.anchor.LT)
end

function clsTiledMapLayer:onTouch(x,y,type)
  if self.delegate and type==smUIConst.touchEventType.DOWN then
    smLog:info("地图被点击,物理坐标: x="..x.." y="..y)
    self.delegate:mapTapped(self,math.floor((self.viewport.y+y)/self.cellHeight)+1,math.floor((self.viewport.x+x)/self.cellWidth)+1)
  end
  return true   --为简化delegate的编写，此处永远返回true
end

--更新
function clsTiledMapLayer:trackViewport(viewport)
  self.viewport=viewport
  --根据窗口坐标和缓冲坐标判断是否需要更新缓冲
  if self.viewport.x<self.bufferCol*self.cellWidth then
    --缓冲左越界
      --smLog:info("--left--")
      --(1)复制可用区域
      self.bufferedImage:copyArea(0,0,(self.bufferColNum-1)*self.cellWidth,self.bufferRowNum*self.cellHeight,self.cellWidth,0)
      --(2)修正缓冲区坐标(左移)
      self.bufferCol=self.bufferCol-1
      --(3)重绘新区域
      local refreshBufferRect={col=0,row=0,colNum=1,rowNum=self.bufferRowNum}
      self:refreshBuffer(refreshBufferRect,true)
  elseif self.viewport.x+self.width>(self.bufferCol+self.bufferColNum)*self.cellWidth then
    --缓冲右越界
      --smLog:info("--right--")
      --(1)复制可用区域
      self.bufferedImage:copyArea(self.cellWidth,0,(self.bufferColNum-1)*self.cellWidth,self.bufferRowNum*self.cellHeight,0,0)
      --(2)修正缓冲区坐标(右移)
      self.bufferCol=self.bufferCol+1
      --(3)重绘新区域
      local refreshBufferRect={col=self.bufferColNum-1,row=0,colNum=1,rowNum=self.bufferRowNum}
      self:refreshBuffer(refreshBufferRect,true)
  elseif self.viewport.y<self.bufferRow*self.cellHeight then
    --缓冲上越界
      --smLog:info("--up--")
      --(1)复制可用区域
      self.bufferedImage:copyArea(0,0,self.bufferColNum*self.cellWidth,(self.bufferRowNum-1)*self.cellHeight,0,self.cellHeight)
      --(2)修正缓冲区坐标(左移)
      self.bufferRow=self.bufferRow-1
      --(3)重绘新区域
      local refreshBufferRect={col=0,row=0,colNum=self.bufferColNum,rowNum=1}
      self:refreshBuffer(refreshBufferRect,true)
  elseif self.viewport.y+self.height>(self.bufferRow+self.bufferRowNum)*self.cellHeight then
    --缓冲下越界
      --smLog:info("--down--")
      --(1)复制可用区域
      self.bufferedImage:copyArea(0,self.cellHeight,self.bufferColNum*self.cellWidth,(self.bufferRowNum-1)*self.cellHeight,0,0)
      --(2)修正缓冲区坐标(下移)
      self.bufferRow=self.bufferRow+1
      --(3)重绘新区域
      local refreshBufferRect={col=0,row=self.bufferRowNum-1,colNum=self.bufferColNum,rowNum=1}
      self:refreshBuffer(refreshBufferRect,true)
  end
end

--绘制缓冲区
function clsTiledMapLayer:refreshBuffer(rect,clearFlag)
  --清除
  if clearFlag then
    self.bufferedImage:clear(rect.col*self.cellWidth,rect.row*self.cellHeight,
       rect.colNum*self.cellWidth,rect.rowNum*self.cellHeight)
  end
  --绘制
  for i=1,table.getn(self.layers) do
    local layer=self.layers[i]
    for j=rect.row,rect.row+rect.rowNum-1 do
      for k=rect.col,rect.col+rect.colNum-1 do
        --smLog:info("j="..j.." k="..k)
        local row=self.bufferRow+j+1
        local col=self.bufferCol+k+1
        if row>=1 and row<=self.rowNum and col>=1 and col<=self.colNum then
          local cell=layer[row][col]
          local imageSetId=cell[1]
          local tiledIndex=cell[2]
          if imageSetId~=-1 then
            local imgColNum=globalData.imageSets[imageSetId]:getWidth()/self.cellWidth
            local imgsx=math.mod(tiledIndex,imgColNum)*self.cellWidth
            local imgsy=math.floor(tiledIndex/imgColNum)*self.cellWidth
            self.bufferedPainter:drawImage(globalData.imageSets[imageSetId],imgsx,imgsy,self.cellWidth,self.cellHeight,
                 k*self.cellWidth,j*self.cellHeight,smUIConst.anchor.LT)
          end
        end
      end
    end
  end
end

function clsTiledMapLayer:toString()
  local str="clsTiledMapLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end


