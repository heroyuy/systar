--[[
  description:A*寻路节点
  author:wp_g4
  date:2011/12/27
--]]

--结构定义
clsAStarNode={}
clsAStarNode.__index=clsAStarNode

--字段
clsAStarNode.row = 0
clsAStarNode.col = 0
clsAStarNode.areaId = 0
clsAStarNode.fValue = 0
clsAStarNode.hValue = 0
clsAStarNode.gValue = 0
clsAStarNode.parentNode = nil
    
-- 构造
function clsAStarNode:new(row,col,areaId)
  local self={}
  setmetatable(self,clsAStarNode)
  self.row=row
  self.col=col
  self.areaId=areaId
  return self
end

--[[
  description:A*寻路工具类
  author:wp_g4
  date:2011/12/27
--]]

-- 结构定义
clsAStar={}
clsAStar.__index=clsAStar

--字段
clsAStar.nodeList=nil  --map
clsAStar.openList=nil  --map
clsAStar.closeList=nil --map
clsAStar.mapData=nil   --array[][]

-- 构造
function clsAStar:new(mapData)
  local self={}
  setmetatable(self,clsAStar)
  self.nodeList=clsHashMap:new()
  self.openList=clsHashMap:new()
  self.closeList=clsHashMap:new()
  self:setMapData(mapData)
  return self
end

--获取行列对应的key(key的格式为 [行号_列号]，比如一个方格行号为2，列号为12，则key为 "2_12")
function clsAStar:getKey(row,col)
  return row.."_"..col
end

--设置节点数据
function clsAStar:setMapData(mapData)
  if mapData then
    self.nodeList:clear()
    self.mapData = mapData
    for i=1,table.getn(mapData) do
      for j=1,table.getn(mapData[i]) do
        if mapData[i][j] ~= -1 then
          self.nodeList:put(self:getKey(i,j), clsAStarNode:new(i, j, mapData[i][j]))
        end
      end
    end 
  end
end

--搜索从起点到终点的可通行路径
function clsAStar:searchPath(startRow,startCol,endRow,endCol)
  --确定实际起点和终点
  if self.mapData[startRow][startCol] ~= self.mapData[endRow][endCol] then
    --起点和终点不在一个区的时候需要重新确定终点
    local nearestNode = self:getNearestNode(self.mapData[startRow][startCol], endRow, endCol)
    endRow = nearestNode.row
    endCol = nearestNode.col
  end
  --清空open列表和colse列表
  self.openList:clear()
  self.closeList:clear()
  --所有node的 gValue,fValue归0，parentNode归null，计算hValue。
  for k,node in pairs(self.nodeList) do
    node.gValue = 0
    node.fValue = 0
    node.hValue =(math.abs(endRow - node.row)) + (math.abs(endCol - node.col))
    node.parentNode = null
  end
  --将起点添加到open表
  local startNode = self:getNode(startRow, startCol)
  self:openNode(startNode)
  local endNode = self:getNode(endRow, endCol)
  while not self:isOpen(endNode) do
    --如果终点已经被添加到open列表则搜索成功
    --获取open列表中的最优点，即fValue最小的点
    local optimalNode = self:getOptimalNode()
    --获取最优点可直接到达的点
    local accessibleNodeList = self:getAccessibleNodeList(optimalNode)
    for _,node in pairs(accessibleNodeList) do
      self:calculateNode(node, optimalNode)
    end
    self:closeNode(optimalNode)
  end
  --获取路径，从终点开始沿父结点寻找,直到起点
  local tempPathStack = clsStack:new()
  local temp = endNode
  while temp and temp~=startNode do
    tempPathStack:push(temp)
    temp = temp.parentNode
  end
  local pathList = clsList:new()
  while tempPathStack:size() > 0 do
    pathList:add(tempPathStack:pop())
  end
  local paths=clsList:new()
  for i=1,pathList:size() do
    local path={}
    path.row = pathList:get(i).row
    path.col = pathList:get(i).col
    paths:add(path)
  end
  return paths
end

--搜索从起点到终点的可通行路径
function clsAStar:searchDirection(startRow,startCol,endRow,endCol)
  local t=smGameEngine:getSystemMilliTime()
  local paths=self:searchPath(startRow, startCol, endRow, endCol)
  local directions=clsQueue:new()
  local sRow = 0
  local sCol = 0
  local eRow = 0
  local eCol = 0
  local str=""
  for i=1,paths:size() do
    --确定相邻两点
    if i==1 then
      sRow = startRow
      sCol = startCol
    else
      sRow = paths:get(i - 1).row
      sCol = paths:get(i - 1).col
    end
    eRow = paths:get(i).row
    eCol = paths:get(i).col
    --转换为方向
    local offsetRow = eRow - sRow
    local offsetCol = eCol - sCol
    local direction=-1
    if offsetRow == -1 then
      --上
      direction = 0
    elseif offsetRow == 0 then
      --同一行，水平移动
      if offsetCol == -1 then
        --左
        direction = 2
      elseif offsetCol == 1 then
        --右
        direction = 3
      end
    elseif offsetRow == 1 then
      --下
      direction = 1
    end
    str=str.." "..direction
    directions:offer(direction) 
  end
  t=smGameEngine:getSystemMilliTime()-t
  smLog:info("寻路耗时:"..t.."ms")
  smLog:info("寻路结果:"..str)
  return directions
end

--获取节点
function clsAStar:getNode(row,col)
  return self.nodeList:get(self:getKey(row,col))
end

--打开节点
function clsAStar:openNode(node)
  self.closeList:remove(self:getKey(node.row,node.col))
  self.openList:put(self:getKey(node.row,node.col), node)
end

--关闭节点
function clsAStar:closeNode(node)
  self.openList:remove(self:getKey(node.row,node.col))
  self.closeList:put(self:getKey(node.row,node.col), node)
end

--检查节点是否打开
function clsAStar:isOpen(node)
  return self.openList:has(self:getKey(node.row,node.col))
end

--检查节点是否关闭
function clsAStar:isClose(node)
  return self.closeList:has(self:getKey(node.row,node.col))
end

--从open列表中获取最优点，fValue最小
function clsAStar:getOptimalNode()
  local temp = nil
  for _,node in pairs(self.openList) do
    if temp==nil or temp.fValue > node.fValue then
      temp=node
    end
  end
  return temp
end

--获取指定结点可以直接到达的结点
function clsAStar:getAccessibleNodeList(node)
  local accessibleNodeList = clsList:new()
  local keyList = clsList:new()
  --上
  keyList:add(self:getKey(node.row - 1,node.col))
  --下
  keyList:add(self:getKey(node.row + 1,node.col))
  --左
  keyList:add(self:getKey(node.row,node.col - 1))
  --右
  keyList:add(self:getKey(node.row,node.col + 1))
  for _,key in pairs(keyList) do
    local temp=self.nodeList:get(key)
    if temp and (not self:isClose(temp)) then
      --如果存在这个结点并且结点未关闭
      accessibleNodeList:add(temp)
    end
  end
  return accessibleNodeList
end

--根据最优点确定其周围的一个点的属性
function clsAStar:calculateNode(node,optimalNode)
  --假设最优点为其周围的点的父结点，计算结点的fvalue
  local tempFValue = node.hValue + optimalNode.gValue + 1
  if node.parentNode == nil or tempFValue < node.fValue then
    --如果此结点还没访问过或者新的fValue更小
    node.parentNode = optimalNode
    node.gValue = optimalNode.gValue + 1
    node.fValue = node.gValue + node.hValue
    --将此结点加入open列表
    self:openNode(node)
  end
end

--获取最近点
function clsAStar:getNearestNode(areaId,endRow,endCol)
  local nearestNodes = clsList:new() --终点附近最近的点（区域ID为areaId）
  --获取最近的点
  local range = 1  --搜索范围
  while nearestNodes:size()==0 do
    --上
    if endRow - range >= 1 then
      for i=endCol - range,endCol + range do
        if i >= 1 and i <=table.getn(self.mapData[endRow - range]) and self.mapData[endRow - range][i] == areaId then
          nearestNodes:add(self:getNode(endRow - range,i))
        end
      end
    end
    --下
    if endRow + range <= table.getn(self.mapData) then
      for  i = endCol - range, endCol + range do
        if i >= 1 and i <= table.getn(self.mapData[endRow + range]) and self.mapData[endRow + range][i] == areaId then
          nearestNodes:add(self:getNode(endRow + range,i))
        end
      end
    end
    --左
    if endCol - range >= 1 then
      for i = endRow - range + 1,endRow + range - 1 do
        if i >= 1 and i <= table.getn(self.mapData) and self.mapData[i][endCol - range] == areaId then
          nearestNodes:add(self:getNode(i,endCol - range))
        end
      end
    end
    --右
    if endCol + range <= table.getn(self.mapData[1]) then
      for i = endRow - range + 1, endRow + range - 1 do
        if i >= 1 and i <= table.getn(self.mapData) and self.mapData[i][endCol + range] == areaId then
          nearestNodes:add(self:getNode(i,endCol + range))
        end
      end
    end
    range=range+1
  end
  --获取最优点
  local optimalNode=nil
  for _,node in pairs(nearestNodes) do
    if optimalNode==nil or math.abs(optimalNode.row - endRow) + math.abs(optimalNode.col - endCol)
                    > math.abs(node.row - endRow) + math.abs(node.col - endCol) then
      optimalNode = node
    end
  end
  return optimalNode
end

--单元测试
local debug=false
if debug then
  local mapData=
  {
    {-1,0,0,0},
    {1,-1,-1,-1},
    {1,1,1,1},
    {1,-1,-1,1}
  }
  local aStar=clsAStar:new(mapData)
  local paths=aStar:searchPath(4,1,2,4)
  print("--------------------------")
  for i=1,paths:size() do
    local path=paths:get(i)
    print("("..path.row..","..path.col..")")
  end
  print("--------------------------")
  local directions=aStar:searchDirection(4,1,2,4)
  while directions:size()>0 do
    local direction=directions:poll()
    print(direction)
  end
  print("--------------------------")
end
