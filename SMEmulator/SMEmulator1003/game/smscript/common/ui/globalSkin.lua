globalSkin={}

globalSkin.bg=nil
globalSkin.body=nil
globalSkin.frame={}
globalSkin.arrow={}
globalSkin.selectedBg={}
globalSkin.cursor={}
globalSkin.textColor={}

function globalSkin:init(skinPath)
  local img=smImageFactory:createImage(skinPath)
  self.bg=img:clip(0,0,64,64)
  self.body=img:clip(0,64,64,64)
  
  self.frame[1]=img:clip(64,0,16,16)   --左上
  self.frame[2]=img:clip(80,0,32,16)   --上
  self.frame[3]=img:clip(112,0,16,16)  --右上
  self.frame[4]=img:clip(64,16,16,32)  --左
  self.frame[5]=img:clip(112,16,16,32) --右
  self.frame[6]=img:clip(64,48,16,16)  --左下
  self.frame[7]=img:clip(80,48,32,16)  --下
  self.frame[8]=img:clip(112,48,16,16) --右下
  
  self.arrow[1]=img:clip(88,16,16,8)   --上
  self.arrow[2]=img:clip(80,24,8,16)   --下
  self.arrow[3]=img:clip(88,40,16,8)   --左
  self.arrow[4]=img:clip(104,24,8,16)  --右
  
  self.selectedBg[1] = img:clip(64,64,8,8)   --左上
  self.selectedBg[2] = img:clip(72,64,16,8)  --上
  self.selectedBg[3] = img:clip(88,64,8,8)   --右上
  self.selectedBg[4] = img:clip(64,72,8,16)  --左
  self.selectedBg[5] = img:clip(72,72,16,16) --中
  self.selectedBg[6] = img:clip(88,72,8,16)  --右
  self.selectedBg[7] = img:clip(64,88,8,8)   --左下
  self.selectedBg[8] = img:clip(72,88,16,8)  --下
  self.selectedBg[9] = img:clip(88,88,8,8)   --右下
  
  self.cursor[1]=img:clip(96,64,16,16)   --左上
  self.cursor[2]=img:clip(112,64,16,16)  --右上
  self.cursor[3]=img:clip(96,80,16,16)   --左下
  self.cursor[4]=img:clip(112,80,16,16)   --右下
  
  --字色，先行后列
  for i=1,4 do
   for j=1,8 do
     self.textColor[i.."_"..j]=img:getRGB(64+j*8+4, 96+i*8+4)
   end
  end
  
end

function globalSkin:createBg(width,height)
  return self.bg:scale(width,height)
end

function globalSkin:createBody(width,height)
  return self.body.scale(width,height)
end

function globalSkin:createFrame(width,height)
  local img=smImageFactory:createImage(width,height)
  local painter=img:getPainter()
  --四个角
    --左上
    painter:drawImage(self.frame[1],0,0,globalUIConst.anchor.LT)
    --右上
    painter:drawImage(self.frame[3],width,0,globalUIConst.anchor.RT)
    --左下
    painter:drawImage(self.frame[6],0,height,globalUIConst.anchor.LB)
    --右下
    painter:drawImage(self.frame[8],width,height,globalUIConst.anchor.RB)
  --四条边
    --上
    painter:drawImage(self.frame[2]:scale(width-2*16,16),16,0,globalUIConst.anchor.LT)
    --左
    painter:drawImage(self.frame[4]:scale(16,height-2*16),0,16,globalUIConst.anchor.LT)
    --右
    painter:drawImage(self.frame[5]:scale(16,height-2*16),width,16,globalUIConst.anchor.RT)
    --下
    painter:drawImage(self.frame[7]:scale(width-2*16,16),16,height,globalUIConst.anchor.LB)
  return img
end

function globalSkin:createSelectedBg(width,height)
  local img=smImageFactory:createImage(width,height)
  local painter=img:getPainter()
  --四个角
    --左上
    painter:drawImage(self.selectedBg[1],0,0,globalUIConst.anchor.LT)
    --右上
    painter:drawImage(self.selectedBg[3],width,0,globalUIConst.anchor.RT)
    --左下
    painter:drawImage(self.selectedBg[7],0,height,globalUIConst.anchor.LB)
    --右下
    painter:drawImage(self.selectedBg[9],width,height,globalUIConst.anchor.RB)
  --四条边
    --上
    painter:drawImage(self.selectedBg[2]:scale(width-2*8,8),8,0,globalUIConst.anchor.LT)
    --左
    painter:drawImage(self.selectedBg[4]:scale(8,height-2*8),0,8,globalUIConst.anchor.LT)
    --右
    painter:drawImage(self.selectedBg[6]:scale(8,height-2*8),width,8,globalUIConst.anchor.RT)
    --下
    painter:drawImage(self.selectedBg[8]:scale(width-2*8,8),8,height,globalUIConst.anchor.LB)
  --中间
    painter:drawImage(self.selectedBg[5]:scale(width-2*8,height-2*8),8,8,globalUIConst.anchor.LT)
  return img
end