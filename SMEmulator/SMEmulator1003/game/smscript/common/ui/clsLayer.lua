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

function clsLayer:addChild(layer)
  self.children:add(layer)
end

function clsLayer:removeChild(layer)
  self.children:removeObject(layer)
end

function clsLayer:remove(index)
  self.children:remove(index)
end
