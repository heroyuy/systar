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

--push方法
function clsQueue:push(value)
  local last = self.last + 1
  self.last = last
  self[last] = value
end

--pop方法
function clsQueue:pop()
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

local q1=clsQueue:new()
q1:push("a")
q1:push("b")
print(q1:pop())
print(q1:size())
print(q1:pop())
print("--------------------")

local q2=clsQueue:new()
q2:push("a2")
print(q2:pop())
print(q2:size())
print(q2:pop())
print("--------------------")

q1:push("c")
q1:push({age=12})
print(q1:size())
print(q1:pop())
local t=q1:pop()
print(t.age)

