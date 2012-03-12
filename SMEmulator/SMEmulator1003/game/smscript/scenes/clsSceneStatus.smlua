--[[
  description:状态场景
  author:wp_g4
  date:2012/03/12
--]]

--结构定义
clsSceneStatus = {}
setmetatable(clsSceneStatus,clsScene)
clsSceneStatus.__index = clsSceneStatus

--构造器
function clsSceneStatus:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneStatus)
	return self
end

-- 开始
function clsSceneStatus:onStart()
  smLog:info("状态场景启动")
end


-- 更新
function clsSceneStatus:update()
  --smLog:info("状态场景更新")
end


-- 退出
function clsSceneStatus:onStop()
  smLog:info("状态场景退出")
end

