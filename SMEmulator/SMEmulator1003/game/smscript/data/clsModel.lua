--[[
  description:模型基类
  author:wp_g4
  date:2011/12/12
--]]

--结构定义
clsModel = {}
clsModel.__index = clsModel

--字段定义
clsModel.id=0 --模型对象ID

--构造器
function clsModel:new()
  local self = {}
  setmetatable(self,clsModel)
  return self
end

--更新
function clsModel:update()
  --更新Model
end