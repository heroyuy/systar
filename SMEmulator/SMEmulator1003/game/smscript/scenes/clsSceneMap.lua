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
    smLog:info("lx:"..(lx+sx).." ly:"..(ly+sy).." px:"..g_gameData.player.x.." py:"..g_gameData.player.y)
	if type == 0 then
	  if lx+sx>g_gameData.player.x then
	    g_gameData.player.x=g_gameData.player.x+1
	  elseif lx+sx<g_gameData.player.x then
	    g_gameData.player.x=g_gameData.player.x-1
	  elseif ly+sy<g_gameData.player.y then
	    g_gameData.player.y=g_gameData.player.y-1
	  elseif ly+sy>g_gameData.player.y then
	    g_gameData.player.y=g_gameData.player.y+1
	  end
	end
end


-- 更新
function clsSceneMap:update()
  smLog:info("地图场景更新")
  --计算新的起始位置
  local px=g_gameData.player.x
  local py=g_gameData.player.y
  local nsx=px-math.floor(screenColNum/2)
  local nsy=py-math.floor(screenRowNum/2)
  if nsx<0 then
    nsx=0
  elseif nsx>mapColNum-screenColNum then
    nsx=mapColNum-screenColNum
  end
  if nsy<0 then
    nsy=0
  elseif nsy>mapRowNum-screenRowNum then
    nsy=mapRowNum-screenRowNum
  end
  --判断移动方向
  if nsx>sx then
    --右移
    --(1)清除原备份
    smLog:info("1")
    local iw=g_mapBg_backup:getWidth()
    local ih=g_mapBg_backup:getHeight()
    g_mapBg_backup:clear(0,0,iw,ih)
    --(2)备份当前地图
    smLog:info("2")
    g_mapBg_backup:getPainter():drawImage(g_mapBg,0,0,0)
    --(3)清除地图
    smLog:info("3")
    g_mapBg:clear(0,0,g_mapBg_backup:getWidth(),g_mapBg_backup:getHeight())
    --(4)从备份中绘制地图的可用部分
    smLog:info("4")
    g_bufferPainter:drawImage(g_mapBg_backup,cellWidth,0,smGameEngine:getWidth()-cellWidth,smGameEngine:getHeight(),0,0,0)
    --(5)从地图数据中绘制因移动显示出来的区域
    local colIndex=nsx+screenColNum-1
    smLog:info("nsx:"..nsx.." colIndex:"..colIndex)
    local layerNum=g_mapData:getLayerNum()
    --绘制缓冲图
    for i=0,layerNum-1 do
      local layer=g_mapData:getLayer(i)
      smLog:info(layer:toString())
      for j=nsy,nsy+screenRowNum-1 do
          local cell=layer:getCell(j,colIndex)
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
              local imgsx=math.mod(tiledIndex,imgColNum)*cellWidth
              local imgsy=math.floor(tiledIndex/imgColNum)*cellWidth
              g_bufferPainter:drawImage(g_imageSet[imageSetId],imgsx,imgsy,cellWidth,cellHeight,(colIndex-nsx)*cellWidth,(j-nsy)*cellHeight,0)
            end
        end
    end
  elseif nsx<sx then
    --左移
  elseif nsy>sy then
    --下移
  elseif nsy<sy then
    --上移
  end
  sx=nsx
  sy=nsy
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
  --取地图属性
  mapRowNum=g_mapData:getRowNum()
  mapColNum=g_mapData:getColNum()
  cellWidth=g_mapData:getCellWidth()
  cellHeight=g_mapData:getCellHeight()
  smLog:info("mapRowNum:"..mapRowNum.." mapColNum:"..mapColNum.." cellWidth:"..cellWidth.." cellHeight:"..cellHeight)
  --创建bufferImage
  g_mapBg=smImageFactory:createImage(smGameEngine:getWidth(),smGameEngine:getHeight())
  g_mapBg_backup=smImageFactory:createImage(smGameEngine:getWidth(),smGameEngine:getHeight())
  g_bufferPainter=g_mapBg:getPainter()
  --初始化图集
  g_imageSet={}
  --图层数
  local layerNum=g_mapData:getLayerNum()
  --屏幕可显示的单元格行、列数
  screenRowNum=smGameEngine:getHeight()/cellHeight
  screenColNum=smGameEngine:getWidth()/cellWidth
  smLog:info("layerNum:"..layerNum.." screenRowNum:"..screenRowNum.." screenColNum:"..screenColNum)
  --根据玩家当前位置计算起始单元格编号
  local px=g_gameData.player.x
  local py=g_gameData.player.y
  sx=px-math.floor(screenColNum/2)
  sy=py-math.floor(screenRowNum/2)
  if sx<0 then
    sx=0
  elseif sx>mapColNum-screenColNum then
    sx=mapColNum-screenColNum
  end
  if sy<0 then
    sy=0
  elseif sy>mapRowNum-screenRowNum then
    sy=mapRowNum-screenRowNum
  end
  --绘制缓冲图
  for i=0,layerNum-1 do
    local layer=g_mapData:getLayer(i)
    smLog:info(layer:toString())
    for j=sy,sy+screenRowNum-1 do
      for k=sx,sx+screenColNum-1 do
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
            local imgsx=math.mod(tiledIndex,imgColNum)*cellWidth
            local imgsy=math.floor(tiledIndex/imgColNum)*cellWidth
            g_bufferPainter:drawImage(g_imageSet[imageSetId],imgsx,imgsy,cellWidth,cellHeight,(k-sx)*cellWidth,(j-sy)*cellHeight,0)
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
  local x=(g_gameData.player.x-sx)*cellWidth
  local y=(g_gameData.player.y-sy)*cellHeight
  painter:fillRect(x,y,g_gameData.player.w,g_gameData.player.h);
end


