--[[
  description:哈希表
  author:wp_g4
  date:2011/12/19
--]]

--结构定义
clsHashMap={}
clsHashMap.__index=clsHashMap

-- 构造
function clsHashMap:new()
  local self={}
  setmetatable(self,clsHashMap)
  return self
end

-- put方法
function clsHashMap:put(key,value)
  self[tostring(key)]=value
end

-- has方法
function clsHashMap:has(key)
  return self:get(key)~=nil
end

-- get方法
function clsHashMap:get(key)
  return self[tostring(key)]
end

-- remove方法
function clsHashMap:remove(key)
  self[tostring(key)]=nil
end

-- removeAll方法
function clsHashMap:removeAll()
  for i=1,self:size() do
    table.remove(self)
  end
end

--size方法
function clsHashMap:size()
  local num=0
  for _,_ in pairs(self) do
    num=num+1
  end
  return num
  --return table.getn(self)
end

--单元测试
local debug=true
if debug then
  local hashMap=clsHashMap:new()
  hashMap:put(12,"good")
  print(hashMap:get("12"))
  local t1={}
  local t2={}
  hashMap:put(t1,"t1!")
  hashMap:put(t2,"t2!")
  print(hashMap:get(t1))
  hashMap:remove(t2)
  print(hashMap:has(t1))
  print(hashMap:has(t2))
  print(hashMap:size())
end