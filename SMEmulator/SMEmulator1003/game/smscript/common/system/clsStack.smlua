--[[
  description:栈
  author:wp_g4
  date:2011/12/28
--]]


--结构定义
clsStack = {}
clsStack.__index = clsStack

--成员变量
clsStack.top=0; --头指针

--构造器
function clsStack:new()
	local self = {}
	setmetatable(self,clsStack)
	return self
end

--push方法
function clsStack:push(value)
  self.top=self.top+1
  self[self.top] = value
end

--pop方法
function clsStack:pop()
  if top == 0 then 
    return nil
  else
    local value = self[self.top]
    self[self.top] = nil    -- to allow garbage collection
    self.top = self.top - 1
    return value
  end
end

--size方法
function clsStack:size()
  return self.top
end

--单元测试

local debug=false
if debug then
  local q1=clsStack:new()
  q1:push("a")
  q1:push("b")
  print(q1:pop())
  print(q1:size())
  print(q1:pop())
  print("--------------------")

  local q2=clsStack:new()
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
end
