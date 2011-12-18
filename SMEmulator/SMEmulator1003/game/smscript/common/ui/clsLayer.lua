--[[
  description:层。所有组件的父类
  author:wp_g4
  date:2011/12/16
--]]

--类结构定义
clsLayer={}
clsLayer.__index=clsLayer

--字段
clsLayer.x=0
clsLayer.y=0
clsLayer.width=0
clsLayer.height=0
clsLayer.tag=0
clsLayer.backgroundColor=nil
clsLayer.backgroundImage = null;
clsLayer.visibility = true;
clsLayer.enabled = true;
clsLayer.clipBounds=true;
clsLayer.delegate=nil
clsLayer.children=nil
clsLayer.focusLayer=nil

--构造器
function clsLayer:new(x,y,width,height)
  local self = {}
  setmetatable(self,clsLayer)
  self.x=x
  self.y=y
  self.width=width
  self.height=height
  self.children=clsList:new()
  return self
end

--添加子Layer
function clsLayer:addChild(layer)
  self.children:add(layer)
end

--移除子Layer
function clsLayer:remove(index)
  self.children:remove(index)
end

--移除子Layer
function clsLayer:removeChild(layer)
  self.children:removeObject(layer)
end

--移除所有子Layer
function clsLayer:removeAll()
  self.children:removeAll()
end

--绘制Layer（lua层不应该调用此方法）
function clsLayer:paint(painter)
  --绘制自身
  self:paintLayer(painter)
  --绘制子Layer
  self:paintChildren(painter)
end

--绘制自身（lua层不应该调用此方法）
function clsLayer:paintLayer(painter)
  if self.backgroundColor then
    painter:setColor(self.backgroundColor);
    painter:fillRect(0, 0, self.width, self.height);
  end
  if self.backgroundImage then
    painter:drawImage(self.backgroundImage, 0, 0, 0);
  end
end

--绘制子Layer（lua层不应该调用此方法）
function clsLayer:paintChildren(painter)
  for _,layer in ipairs(self.children) do
    --smLog:info(layer:toString())
    if layer.visibility then
      --设置坐标系
      painter:setBasePoint(layer.x,layer.y)
      --设置裁剪区
      local clip=nil;
      if layer.clipBounds then
        clip=painter:getClip()
        painter:clipRect(0,0,layer.width,layer.height)
      end
      --绘制子Layer
      layer:paint(painter)
      --还原裁剪区
      if layer.clipBounds then
        painter:forceClip(clip)
      end
      --还原坐标系
      painter:setBasePoint(-layer.x,-layer.y)
    end
  end
      
end

--处理触屏事件（lua层不应该调用此方法）
function clsLayer:onTouch(x,y,type)
  if self.delegate then
    self.delegate:onTouch(self,x,y,type)
  end
end

--事件分发（lua层不应该调用此方法）
function clsLayer:dispatchEvent(x,y,type)
  if type==globalUIConst.touchEventType.DOWN then
    --如果是DOWN事件，则从子组件中寻找焦点组件
    self.focusLayer = self:searchFocusLayer(x,y)
  end
  if self.focusLayer and self.focusLayer.enabled then
    --找到焦点组件
    self.focusLayer:dispatchEvent(x-self.focusLayer.x,y-self.focusLayer.y,type)
  else
    --未找到焦点组件
    self:onTouch(x,y,type)
  end
  if type==globalUIConst.touchEventType.UP then
    --如果是UP事件，则清除焦点
    self.focusLayer = nil
  end
end

--更新Layer相关元素（lua层不应该调用此方法）
function clsLayer:update()
end

--toString
function clsLayer:toString()
  local str="clsLayer:["
  str=str.."x="..self.x.." y="..self.y.." w="..self.width.." h="..self.height
  str=str.."]"
  return str
end

--搜索焦点子组件，没有则返回nil
function clsLayer:searchFocusLayer(x,y)
  local focus = nil;
  --从上到下依次探查
  for i=self.children:size(),1,-1 do
    local layer=self.children:get(i)
    if layer.visibility and layer.enabled then
      --子Layer可见并且可以接收事件
      if x >= layer.x and x <= layer.x + layer.width and y >= layer.y and y <= layer.y + layer.height then
        --命中
        focus = layer;
        break;
      end
    end
  end
  return focus
end
