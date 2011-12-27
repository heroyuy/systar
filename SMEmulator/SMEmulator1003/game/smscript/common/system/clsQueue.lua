--[[
  description:队列
  author:wp_g4
  date:2011/12/14
--]]


--结构定义
clsQueue = {}
clsQueue.__index = clsQueue

--成员变量
clsQueue.first=0; --头指针
clsQueue.last=-1;  --尾指针

--构造器
function clsQueue:new()
	local self = {}
	setmetatable(self,clsQueue)
	return self
end

--offer方法
function clsQueue:offer(value)
  local last = self.last + 1
  self.last = last
  self[last] = value
end

--poll方法
function clsQueue:poll()
  local first = self.first
  if first > self.last then 
    return nil
  else
    local value = self[first]
    self[first] = nil    -- to allow garbage collection
    self.first = first + 1
    return value
  end
end

--size方法
function clsQueue:size()
  local first = self.first
  if first > self.last then 
    return 0
  else
    return self.last-self.first+1
  end
end

--单元测试

local debug=false
if debug then
  local q1=clsQueue:new()
  q1:offer("a")
  q1:offer("b")
  print(q1:poll())
  print(q1:size())
  print(q1:poll())
  print("--------------------")

  local q2=clsQueue:new()
  q2:offer("a2")
  print(q2:poll())
  print(q2:size())
  print(q2:poll())
  print("--------------------")

  q1:offer("c")
  q1:offer({age=12})
  print(q1:size())
  print(q1:poll())
  local t=q1:poll()
  print(t.age)
end
