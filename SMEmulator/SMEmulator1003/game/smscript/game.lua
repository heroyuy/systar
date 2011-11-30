function onStart()
--smLog:info("onStart");
img=smImageFactory:createImage("game/image/tileset/001-Grassland01.png");
end

function onTouch(x,y,type)
--smLog:info("onTouch-> x:"..x.." y="..y.." type:"..type);
img:clear(0,0,100,100);
img2=smImageFactory:createImage("game/image/battler/001-Fighter01.png");
p=img:getPainter();
p:drawImage(img2,0,0,0);
end

function update()
--smLog:info("update");
end

function paint(painter)
smLog:info("paint");
painter:setColor(0xabcdef);
painter:fillRect(0,0,800,480);
painter:setColor(0xfefefe);
painter:setTextSize(50);
painter:drawString("HelloWorld!",1,1,0);
painter:setColor(0xefefef);
painter:drawString("HelloWorld!",0,430,0);
painter:drawImage(img,0,0,0);
end

function onStop()
--smLog:info("onStop");
end
