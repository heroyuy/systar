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
clsUISprite.image=nil     --图像

--构造器
function clsUISprite:new(image)
  if type(image)=="string" then
    image=smImageFactory:createImage(image)
  end
  local self = clsUILayer:new(0,0,image:getWidth(),image:getHeight())
  setmetatable(self,clsUISprite)
  self.image=image
  return self
end

--绘制自身
function clsUISprite:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
  --绘制图片
  if self.image then
    painter:drawImage(self.image,self.width/2,self.height/2,smUIConst.anchor.HV)
  end
end

function clsUISprite:toString()
  local str="clsUISprite:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end
