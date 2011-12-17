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
clsLayer.backgroundColor=nil
clsLayer.backgroundImage = null;
clsLayer.visibility = false;
clsLayer.enabled = true;
clsLayer.delegate=nil
clsLayer.children=nil

--构造器
function clsLayer:new(x,y,width,height)
  local self = {}
  self.x=x
  self.y=y
  self.width=width
  self.height=height
  clsLayer.children=clsList:new()
  setmetatable(self,clsLayer)
  return self
end

--添加子Layer
function clsLayer:addChild(layer)
  self.children:add(layer)
end

--移除子Layer
function clsLayer:removeChild(layer)
  self.children:removeObject(layer)
end

--移除子Layer
function clsLayer:remove(index)
  self.children:remove(index)
end

--绘制Layer
function clsLayer:paint(painter)
  --绘制自身
  self:paintLayer(painter)
  --绘制子Layer
  self:paintChildren(painter)
end

--绘制自身
function clsLayer:paintLayer(painter)
  
end

--绘制子Layer
function clsLayer:paintChildren(painter)
  
end
