-- 卡马克地图缓冲
clsCarBufferMap = {}
clsCarBufferMap.__index = clsCarBufferMap
-- 构造体
function clsCarBufferMap:new(titleMap)
	local self = {}
	setmetatable(self,clsCarBufferMap)
	self.scrWidth = globalScreenWidth
	self.scrHeight = globalScreenHeight
	self.titleMap = titleMap
	self.cellWidth = self.titleMap.map.cellWidth
	self.cellHeight = self.titleMap.map.cellHeight
	local width = (self.scrWidth - self.cellWidth)/2 
	local height = (self.scrHeight - self.cellHeight)/2
	local tmp = 0
	while tmp < width do
		tmp = tmp + self.cellWidth
	end
	self.widthNum = (tmp * 2) / self.cellWidth + 3
	self.buffPlusX = (self.widthNum * self.cellWidth - self.scrWidth)/2
	self.buffWidth = self.buffPlusX * 2 + self.scrWidth
	tmp = 0
	while tmp < height do
		tmp = tmp + self.cellHeight
	end
	self.heightNum = (tmp * 2) / self.cellHeight + 3
	self.buffPlusY = (self.heightNum * self.cellHeight - self.scrHeight)/2
	self.buffHeight = self.buffPlusY * 2 + self.scrHeight
	return self
end
-- 初始化地图
function clsCarBufferMap:initMap()
	
	self.layer = self.titleMap.map.layer
	self.imageSet = {}
	for id,set in pairs(self.titleMap.map.imageSet) do
		self.imageSet[set.id] = smImage:createImage(set.path)
	end
	self.backImage = smImage:createImage(self.buffWidth,self.buffHeight)
	self.backPainter = self.backImage:getPainter()
	self.foreImage = smImage:createImage(self.buffWidth,self.buffHeight)
	self.forePainter = self.foreImage:getPainter()
	self.activeImage = smImage:createImage(self.buffWidth*self.titleMap.class,self.buffHeight)
	self.activePainter = self.activeImage:getPainter()
	local tmpData,ima
	self.priorX = (self.titleMap.ox - self.buffPlusX - self.scrWidth/2)/self.cellWidth + 1
	self.priorY = (self.titleMap.oy - self.buffPlusY - self.scrHeight/2)/self.cellHeight + 1
	for deepth,layer in pairs(self.layer) do
		for j = 1,self.heightNum do
			for i = 1,self.widthNum do
				tmpData = layer.data[i + self.priorX - 1][j+self.priorY-1]
				if not (tmpData.index <= 0) then
					
					ima = smImage:copyImage(self.imageSet[tmpData.id],self:getX(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
					
					if deepth < 0 then
						self.backPainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
					elseif deepth > 0 then
						self.forePainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
					elseif deepth == 0 then
						for k = 1,self.titleMap.class do
							if k ~= 1 then
								ima = smImage:copyImage(self.imageSet[tmpData.id],self:getActiveX(self.imageSet[tmpData.id]:getWidth(),tmpData.index,k),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
							end
							self.activePainter:drawImage(ima,(i-1)*self.cellWidth + (k-1)*self.buffWidth,(j-1)*self.cellHeight,self.forePainter.LT)
						end
					end
				end
			end
		end
	end
end
-- 获取图元X坐标
function clsCarBufferMap:getX(width,index)
	return (index - 1) % (math.floor(width / self.cellWidth)) * self.cellWidth
end
-- 获取图元Y坐标
function clsCarBufferMap:getY(width,index)
	return math.floor((index - 1) / (math.floor(width / self.cellWidth))) * self.cellHeight
end
-- 获取动态图元X坐标
function clsCarBufferMap:getActiveX(activeWidth,index,frame)
	local width = activeWidth / self.titleMap.class
	return self:getX(width,index) + (frame - 1) * width
end
-- 重绘
function clsCarBufferMap:repaint()
	local tmp,copy,ima,tmpData,n,targetX,targetY,painter
	local copys = {}
	if self.titleMap.ox - self.titleMap.oriX > self.buffPlusX then -- 右越界重绘
		targetX = 0
	    tmp = self.titleMap.oriX + self.cellWidth/2
		while tmp - self.cellWidth/2 < self.titleMap.ox do
			tmp = tmp + self.cellWidth
		end
		targetX = tmp - self.cellWidth/2
		n = (targetX-self.titleMap.oriX)/self.cellWidth
		self.priorX = self.priorX + n
		-- 建立前后景地图
		copy = smImage:copyImage(self.backImage,targetX - self.titleMap.oriX,0,self.buffWidth - targetX + self.titleMap.oriX,self.buffHeight)
		self.backImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.backPainter = self.backImage:getPainter()
		self.backPainter:drawImage(copy,0,0,self.backPainter.LT)
		copy = smImage:copyImage(self.foreImage,targetX - self.titleMap.oriX,0,self.buffWidth - targetX + self.titleMap.oriX,self.buffHeight)
		self.foreImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.forePainter = self.foreImage:getPainter()
		self.forePainter:drawImage(copy,0,0,self.backPainter.LT)
		for k = 1,self.titleMap.class do
			copys[k] = smImage:copyImage(self.activeImage,targetX - self.titleMap.oriX + (k-1)*self.buffWidth,0,self.buffWidth - targetX + self.titleMap.oriX,self.buffHeight)
		end
		self.activeImage = smImage:createImage(self.buffWidth*self.titleMap.class,self.buffHeight)
		self.activePainter = self.activeImage:getPainter()
		for k = 1,self.titleMap.class do
		self.activePainter:drawImage(copys[k],(k-1)*self.buffWidth,0,self.activePainter.LT)
		end
		for deepth,layer in pairs(self.layer) do
			for j = 1,self.heightNum do
				for i = 1,self.widthNum do
					if self.priorX <= self.titleMap.map.rowNum - self.widthNum - 1 then
						if i <= self.widthNum and i >= self.widthNum - (n-1) then
							tmpData = layer.data[i+self.priorX-1][j+self.priorY-1]
							if not (tmpData.index <= 0) then
								ima = smImage:copyImage(self.imageSet[tmpData.id],self:getX(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
								if deepth < 0 then
									self.backPainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
								elseif deepth > 0 then
									self.forePainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.forePainter.LT)
								elseif deepth == 0 then
									for k = 1,self.titleMap.class do
										if k ~= 1 then
											ima = smImage:copyImage(self.imageSet[tmpData.id],self:getActiveX(self.imageSet[tmpData.id]:getWidth(),tmpData.index,k),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
										end
										self.activePainter:drawImage(ima,(i-1)*self.cellWidth + (k-1)*self.buffWidth,(j-1)*self.cellHeight,self.forePainter.LT)
									end
								end
							end
						end
					end
				end
			end
		end
		self.titleMap.targetX = targetX
	elseif  self.titleMap.oriX - self.titleMap.ox > self.buffPlusX then -- 左越界重绘
		targetX = 0
		tmp = self.titleMap.oriX - self.cellWidth/2
		while tmp + self.cellWidth/2 > self.titleMap.ox do
			tmp = tmp - self.cellWidth
		end
		targetX = tmp + self.cellWidth/2
		n = (self.titleMap.oriX - targetX)/self.cellWidth
		self.priorX = self.priorX - n
		-- 建立前后景地图
		copy = smImage:copyImage(self.backImage,0,0,self.buffWidth - self.titleMap.oriX + targetX,self.buffHeight)
		self.backImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.backPainter = self.backImage:getPainter()
		self.backPainter:drawImage(copy,self.titleMap.oriX - targetX,0,self.backPainter.LT)
		copy = smImage:copyImage(self.foreImage,0,0,self.buffWidth - self.titleMap.oriX + targetX,self.buffHeight)
		self.foreImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.forePainter = self.foreImage:getPainter()
		self.forePainter:drawImage(copy,self.titleMap.oriX - targetX,0,self.backPainter.LT)
		for k = 1,self.titleMap.class do
			copys[k] = smImage:copyImage(self.activeImage,(k-1)*self.buffWidth,0,self.buffWidth - self.titleMap.oriX + targetX,self.buffHeight)
		end
		self.activeImage = smImage:createImage(self.buffWidth*self.titleMap.class,self.buffHeight)
		self.activePainter = self.activeImage:getPainter()
		for k = 1,self.titleMap.class do
			self.activePainter:drawImage(copys[k],self.titleMap.oriX - targetX + (k-1)*self.buffWidth,0,self.activePainter.LT)
		end
		for deepth,layer in pairs(self.layer) do
			for j = 1,self.heightNum do
				for i = 1,self.widthNum do
					if self.priorX >= 1 then
						if i <= n and i >= 1 then
							tmpData = layer.data[i+self.priorX-1][j+self.priorY-1]
							if not (tmpData.index <= 0) then
								ima = smImage:copyImage(self.imageSet[tmpData.id],self:getX(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
								if deepth < 0 then
									self.backPainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
								elseif deepth > 0 then
									self.forePainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.forePainter.LT)
								elseif deepth == 0 then
									for k = 1,self.titleMap.class do
										if k ~= 1 then
											ima = smImage:copyImage(self.imageSet[tmpData.id],self:getActiveX(self.imageSet[tmpData.id]:getWidth(),tmpData.index,k),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
										end
										self.activePainter:drawImage(ima,(i-1)*self.cellWidth + (k-1)*self.buffWidth,(j-1)*self.cellHeight,self.forePainter.LT)
									end
								end
							end
						end
					end
				end
			end
		end
		self.titleMap.targetX = targetX
	elseif self.titleMap.oy - self.titleMap.oriY > self.buffPlusY then -- 下越界重绘
		
		targetY = 0
		tmp = self.titleMap.oriY + self.cellHeight/2
		while tmp - self.cellHeight/2 < self.titleMap.oy do
			tmp = tmp + self.cellHeight
		end
		targetY = tmp - self.cellHeight/2
		n = (targetY-self.titleMap.oriY)/self.cellHeight
		self.priorY = self.priorY + n
		-- 建立前后景地图
		copy = smImage:copyImage(self.backImage,0,targetY - self.titleMap.oriY,self.buffWidth,self.buffHeight - targetY + self.titleMap.oriY)
		self.backImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.backPainter = self.backImage:getPainter()
		self.backPainter:drawImage(copy,0,0,self.backPainter.LT)
		copy = smImage:copyImage(self.foreImage,0,targetY - self.titleMap.oriY,self.buffWidth,self.buffHeight - targetY + self.titleMap.oriY)
		self.foreImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.forePainter = self.foreImage:getPainter()
		self.forePainter:drawImage(copy,0,0,self.forePainter.LT)
		for k = 1,self.titleMap.class do
			copys[k] = smImage:copyImage(self.activeImage,(k-1)*self.buffWidth,targetY - self.titleMap.oriY,self.buffWidth,self.buffHeight - targetY + self.titleMap.oriY)
		end
		self.activeImage = smImage:createImage(self.buffWidth*self.titleMap.class,self.buffHeight)
		self.activePainter = self.activeImage:getPainter()
		for k = 1,self.titleMap.class do
			self.activePainter:drawImage(copys[k],(k-1)*self.buffWidth,0,self.activePainter.LT)
		end
		for deepth,layer in pairs(self.layer) do
			for j = 1,self.heightNum do
				for i = 1,self.widthNum do
					if self.priorY <= self.titleMap.map.colNum - self.heightNum - 1 then
						if j <= self.heightNum and j >= self.heightNum - (n-1) then
							tmpData = layer.data[i+self.priorX-1][j+self.priorY-1]
							if not (tmpData.index <= 0) then
								ima = smImage:copyImage(self.imageSet[tmpData.id],self:getX(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
								if deepth < 0 then
									self.backPainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
								elseif deepth > 0 then
									self.forePainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.forePainter.LT)
								elseif deepth == 0 then
									for k = 1,self.titleMap.class do
										if k ~= 1 then
											ima = smImage:copyImage(self.imageSet[tmpData.id],self:getActiveX(self.imageSet[tmpData.id]:getWidth(),tmpData.index,k),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
										end
										self.activePainter:drawImage(ima,(i-1)*self.cellWidth + (k-1)*self.buffWidth,(j-1)*self.cellHeight,self.activePainter.LT)
									end
								end
							end
						end
					end
				end
			end
		end
		self.titleMap.targetY = targetY
	elseif self.titleMap.oriY - self.titleMap.oy > self.buffPlusY then -- 上越界重绘
		targetY = 0
		tmp = self.titleMap.oriY - self.cellHeight/2
		while tmp + self.cellHeight/2 > self.titleMap.oy do
			tmp = tmp - self.cellHeight
		end
		targetY = tmp + self.cellHeight/2
		n = (self.titleMap.oriY - targetY)/self.cellHeight
		self.priorY = self.priorY - n
		-- 建立前后景地图
		copy = smImage:copyImage(self.backImage,0,0,self.buffWidth,self.buffHeight - self.titleMap.oriY + targetY)
		self.backImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.backPainter = self.backImage:getPainter()
		self.backPainter:drawImage(copy,0,self.titleMap.oriY - targetY,self.backPainter.LT)
		copy = smImage:copyImage(self.foreImage,0,0,self.buffWidth,self.buffHeight - self.titleMap.oriY + targetY)
		self.foreImage = smImage:createImage(self.buffWidth,self.buffHeight)
		self.forePainter = self.foreImage:getPainter()
		self.forePainter:drawImage(copy,0,self.titleMap.oriY - targetY,self.forePainter.LT)
		for k = 1,self.titleMap.class do
			copys[k] = smImage:copyImage(self.activeImage,(k-1)*self.buffWidth,0,self.buffWidth,self.buffHeight - self.titleMap.oriY + targetY)
		end
		self.activeImage = smImage:createImage(self.buffWidth*self.titleMap.class,self.buffHeight)
		self.activePainter = self.activeImage:getPainter()
		for k = 1,self.titleMap.class do
			self.activePainter:drawImage(copys[k],(k-1)*self.buffWidth,self.titleMap.oriY - targetY,self.activePainter.LT)
		end
		copys = nil
		for deepth,layer in pairs(self.layer) do
			for j = 1,self.heightNum do
				for i = 1,self.widthNum do
					if self.priorY >= 1 then
						if j <= n and j >= 1 then
							tmpData = layer.data[i+self.priorX-1][j+self.priorY-1]
							if not (tmpData.index <= 0) then
								ima = smImage:copyImage(self.imageSet[tmpData.id],self:getX(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
								if deepth < 0 then
									self.backPainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.backPainter.LT)
								elseif deepth > 0 then
									self.forePainter:drawImage(ima,(i-1)*self.cellWidth,(j-1)*self.cellHeight,self.forePainter.LT)
								elseif deepth == 0 then
									for k = 1,self.titleMap.class do
										if k ~= 1 then
											ima = smImage:copyImage(self.imageSet[tmpData.id],self:getActiveX(self.imageSet[tmpData.id]:getWidth(),tmpData.index,k),self:getY(self.imageSet[tmpData.id]:getWidth(),tmpData.index),self.cellWidth,self.cellHeight)
										end
										self.activePainter:drawImage(ima,(i-1)*self.cellWidth + (k-1)*self.buffWidth,(j-1)*self.cellHeight,self.activePainter.LT)
									end
								end
							end
						end
					end	
				end
			end
		end
		self.titleMap.targetY = targetY
	end
end