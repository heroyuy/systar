--[[
  description:观察者
  author:wp_g4
  date:2012/02/21
--]]

--结构定义
clsObserver = {}
clsObserver.__index = clsObserver

--字段定义
clsObserver.target=nil
clsObserver.callFunction=nil

--构造器
function clsObserver:new(target,callFunction)
  local self = {}
  setmetatable(self,clsObserver)
  self.target=target
  self.callFunction=callFunction
  return self
end

-------------------------------------------------------------------------
-------------------------------------------------------------------------

--[[
  description:游戏通知管理器
  author:wp_g4
  date:2012/02/21
--]]

--初始化
globalNotificationCenter={}

globalNotificationCenter.observerMap=clsHashMap:new()   --观察者map

function globalNotificationCenter:addObserver(name,target,callFunction)
  if not self.observerMap:has(name) then
    self.observerMap:put(name,clsList:new())
  end
  local list=self.observerMap:get(name)
  list:add(clsObserver:new(target,callFunction))
end

function globalNotificationCenter:removeObserver(name,target)
  if self.observerMap:has(name) then
    local list=self.observerMap:get(name)
    local size=list:size()
    local index=0
    for i=1,size do
      local observer=list:get(i)
      if observer.target==target then
        index=i
        break
      end
    end
    if index~=0 then
      list:remove(index)
    end
  end
end

function globalNotificationCenter:notify(name,arg)
  if self.observerMap:has(name) then
    local list=self.observerMap:get(name)
    local size=list:size()
    for i=1,size do
      local observer=list:get(i)
      observer.callFunction(observer.target,arg)
    end
  end
end