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
    local lx=math.floor(x/cellWidth)
    local ly=math.floor(y/cellHeight)
    smLog:info("lx:"..lx.." ly:"..ly.." px:"..g_gameData.player.x.." py:"..g_gameData.player.y)
	if type == 0 then
	  if lx>g_gameData.player.x then
	    g_gameData.player.x=g_gameData.player.x+1
	  elseif lx<g_gameData.player.x then
	    g_gameData.player.x=g_gameData.player.x-1
	  elseif ly<g_gameData.player.y then
	    g_gameData.player.y=g_gameData.player.y-1
	  elseif ly>g_gameData.player.y then
	    g_gameData.player.y=g_gameData.player.y+1
	  end
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
clsSceneMap:paintMap(painter)
clsSceneMap:paintPlayer(painter)
end

-- 退出
function clsSceneMap:onStop()
  smLog:info("地图场景退出")
end

--初始化地图
function clsSceneMap:initMap()
  smLog:info("init map")
  --读地图数据
  g_mapData=smDataManager:getMap(0)
  mapRowNum=g_mapData:getRowNum()
  mapColNum=g_mapData:getColNum()
  cellWidth=g_mapData:getCellWidth()
  cellHeight=g_mapData:getCellHeight()
  smLog:info("mapRowNum:"..mapRowNum.." mapColNum:"..mapColNum.." cellWidth:"..cellWidth.." cellHeight:"..cellHeight)
  --创建bufferImage
  g_mapBg=smImageFactory:createImage(smGameEngine:getWidth(),smGameEngine:getHeight())
  g_bufferPainter=g_mapBg:getPainter()
  g_imageSet={}
  local layerNum=g_mapData:getLayerNum()
  local rowNum=smGameEngine:getHeight()/cellHeight
  local colNum=smGameEngine:getWidth()/cellWidth
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
            local imgColNum=g_imageSet[imageSetId]:getWidth()/cellWidth
            local sx=math.mod(tiledIndex,imgColNum)*cellWidth
            local sy=math.floor(tiledIndex/imgColNum)*cellWidth
            g_bufferPainter:drawImage(g_imageSet[imageSetId],sx,sy,cellWidth,cellHeight,k*cellWidth,j*cellHeight,0)
          end
      end
    end
  end
end

--绘制地图
function clsSceneMap:paintMap(painter)
  painter:drawImage(g_mapBg,0,0,0)
end

--绘制角色
function clsSceneMap:paintPlayer(painter)
  painter:setColor(0xabcdef)
  local x=g_gameData.player.x*cellWidth
  local y=g_gameData.player.y*cellHeight
  painter:fillRect(x,y,g_gameData.player.w,g_gameData.player.h);
end


