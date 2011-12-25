--[[
  description:背包。例：{item1={num=10},item2={num=3,rate=0.4},equip0={type=0,num=3,rate=0.3}}
  author:wp_g4
  date:2011/12/15
--]]

--结构定义
clsBag = {}
clsBag.__index = clsBag

--构造器
function clsBag:new()
  local self = {}
  setmetatable(self,clsBag)
  return self
end

function clsBag:addItem(id,num,rate)
  --先判断背包中是否有此物品
  if self["item"..id] then
    
  else
    
  end
end

