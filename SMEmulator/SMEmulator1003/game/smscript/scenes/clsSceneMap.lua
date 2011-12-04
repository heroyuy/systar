clsSceneMap = {}
setmetatable(clsSceneMap,clsScene)
clsSceneMap.__index = clsSceneMap
function clsSceneMap:new()
	local self = {}
	self = clsScene:new()
	setmetatable(self,clsSceneMap)
	return self
end
-- 开始
function clsSceneMap:onStart()
  smLog:info("地图场景启动")
  clsSceneMap:initMap()
end
-- 处理触屏
function clsSceneMap:onTouch(x,y,type)
	if type == 0 then
	smLog:info("地图场景-按下事件")
	end
end


-- 更新
function clsSceneMap:update()
smLog:info("地图场景更新")
smLog:info(collectgarbage("count").."Kb")
end

-- 绘图
function clsSceneMap:paint(painter)
smLog:info("地图场景绘制")
painter:drawImage(g_mapBg,0,0,0)
end

-- 退出
function clsSceneMap:onStop()
  smLog:info("地图场景退出")
end

function clsSceneMap:initMap()
  smLog:info("init map")
  --读地图数据
  g_mapData=smDataManager:getMap(0)
  local row=g_mapData:getRowNum()
  local col=g_mapData:getColNum()
  local width=g_mapData:getCellWidth()
  local height=g_mapData:getCellHeight()
  smLog:info("row:"..row.." col:"..col.." width:"..width.." height:"..height)
  --创建bufferImage
  g_mapBg=smImageFactory:createImage(smGameEngine:getWidth(),smGameEngine:getHeight())
  g_bufferPainter=g_mapBg:getPainter()
  g_imageSet={}
  local layerNum=g_mapData:getLayerNum()
  local rowNum=smGameEngine:getHeight()/height
  local colNum=smGameEngine:getWidth()/width
  smLog:info("layerNum:"..layerNum.." rowNum:"..rowNum.." colNum:"..colNum)
  --绘制缓冲图
  for i=0,layerNum-1 do
    local layer=g_mapData:getLayer(i)
    smLog:info(layer:toString())
    for j=0,rowNum-1 do
      for k=0,colNum-1 do
        local cell=layer:getCell(j,k)
        smLog:info(cell:toString())
        local imageSetId=cell:getImageSetId()
        local tiledIndex=cell:getTiledIndex()
          --加载图集
          smLog:info("imageSetId:"..imageSetId)
          if imageSetId~=-1 and g_imageSet[imageSetId]==nil then
            smLog:info("加载图集")
            local imageSet=g_mapData:getImageSet(imageSetId)
            smLog:info("--")
            local path=imageSet:getPath()
            smLog:info(".\\game"..path)
            g_imageSet[imageSetId]=smImageFactory:createImage(".\\game"..path)
            smLog:info(g_imageSet[imageSetId]:toString())
          end
          --绘制
          if imageSetId~=-1 then
            local imgColNum=g_imageSet[imageSetId]:getWidth()/width
            local sx=math.mod(tiledIndex,imgColNum)*width
            local sy=math.floor(tiledIndex/imgColNum)*width
            g_bufferPainter:drawImage(g_imageSet[imageSetId],sx,sy,width,height,k*width,j*height,0)
          end
      end
    end
  end
end