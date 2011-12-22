--[[
  description:精灵。
  author:wp_g4
  date:2011/12/22
--]]

--类结构定义
clsSprite={}
setmetatable(clsSprite,clsLayer)
clsSprite.__index=clsSprite

--重定向父类paintLayer(painter)方法
clsSprite.paintLayerF=clsSprite.paintLayer

--字段
clsSprite.image=nil     --图像

--构造器
function clsSprite:new(image)
  if type(image)=="string" then
    image=smImageFactory:createImage(image)
  end
  smLog:info("sprite:"..tostring(image))
  local self = clsLayer:new(0,0,image:getWidth(),image:getHeight())
  setmetatable(self,clsSprite)
  self.image=image
  return self
end

local rate=0.1
local tag="add"
--绘制自身
function clsSprite:paintLayer(painter)
  --调用父类的paintLayer方法
  self:paintLayerF(painter)
  --绘制图片
  if self.image then
    painter:drawImage(self.image:scale(rate*self.width,rate*self.height):alpha(rate),self.width/2,self.height/2,globalUIConst.anchor.HV)
  end
  if tag=="add" then
    rate=rate+0.02
    if rate>=1 then
      tag="del"
    end
  elseif tag=="del" then
    rate=rate-0.02
    if rate<=0.1 then
      tag="add"
    end
  end
end

function clsSprite:toString()
  local str="clsSprite:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end
