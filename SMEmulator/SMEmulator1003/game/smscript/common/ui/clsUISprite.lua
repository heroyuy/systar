--[[
  description:精灵。
  author:wp_g4
  date:2011/12/22
--]]

--类结构定义
clsUISprite={}
setmetatable(clsUISprite,clsUILayer)
clsUISprite.__index=clsUISprite

--重定向父类paintLayer(painter)方法
clsUISprite.paintLayerF=clsUISprite.paintLayer

--字段
clsUISprite.image=nil              --图像
clsUISprite.frameIndex=0           --当前帧index
clsUISprite.colNum=1               --帧列数
clsUISprite.rowNum=1               --帧行数

--构造器
function clsUISprite:new(image,frameWidth,frameHeight)
  if type(image)=="string" then
    image=smImageFactory:createImage(image)
  end
  if frameWidth==nil then
    frameWidth=image:getWidth()
  end
  if frameHeight==nil then
    frameHeight=image:getHeight()
  end
  local self = clsUILayer:new(0,0,frameWidth,frameHeight)
  setmetatable(self,clsUISprite)
  self.image=image
  self.colNum=image:getWidth()/frameWidth
  self.rowNum=image:getHeight()/frameHeight
  return self
end

--绘制自身
function clsUISprite:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
  --绘制图片
  if self.image then
    painter:drawImage(self.image,self.frameIndex%self.colNum*self.width,math.floor(self.frameIndex/self.rowNum)*self.height,self.width,self.height,0,0,smUIConst.anchor.LT)
  end
end

function clsUISprite:toString()
  local str="clsUISprite:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end