
local g_bgbig_rect = 
{
{ name = "bgbig3.png", rect = cc.rect(2, 2, 948, 291) },
{ name = "bubble1.png", rect = cc.rect(558, 335, 92, 28) },
{ name = "bubble2.png", rect = cc.rect(404, 482, 92,  28) },
{ name = "bubble3.png", rect = cc.rect(2, 461, 150,  46) },
{ name = "cloud1.png", rect = cc.rect(292, 362, 86,  43) },
{ name = "cloud2.png", rect = cc.rect(154, 459, 160, 50) },
{ name = "cloud3.png", rect = cc.rect(292, 295,176,  65) },
{ name = "jumpbutton10.png", rect = cc.rect(147, 295, 143, 81) },
{ name = "jumpbutton11.png", rect = cc.rect(2, 378, 143, 81) },
{ name = "jumpbutton20.png", rect = cc.rect(2, 295, 143, 81) },
{ name = "jumpbutton21.png", rect = cc.rect(147, 378, 143, 79) },
{ name = "objects0001.png", rect = cc.rect(470, 295, 88, 38) },
{ name = "objects0002.png", rect = cc.rect(468, 362, 88, 38) },
{ name = "objects0003.png", rect = cc.rect(558, 365, 86, 28) },
{ name = "objects0004.png", rect = cc.rect(494, 438, 86, 34) },
{ name = "objects0005.png", rect = cc.rect(478, 402, 86, 34) },
{ name = "objects0006.png", rect = cc.rect(404, 444, 88, 36) },
{ name = "objects0007.png", rect = cc.rect(292, 407, 92,  40) },
{ name = "objects0008.png", rect = cc.rect(386, 404, 90,  38) },
{ name = "objects0009.png", rect = cc.rect(574, 474, 74,  34) },
{ name = "objects0010.png", rect = cc.rect(498, 474, 74,  34) },
{ name = "objects0011.png", rect = cc.rect(316, 449, 86,  40) },
{ name = "objects0012.png", rect = cc.rect(380, 362, 86,  40) }
}

local g_bgbig_count = table.getn(g_bgbig_rect)

local g_rank_01_anim_rect = 
{
{ name = "rank_01_anim0001.png", rect = cc.rect(2, 2, 142, 161) },
{ name = "rank_01_anim0002.png", rect = cc.rect(146, 2, 142, 163) },
{ name = "rank_01_anim0003.png", rect = cc.rect(290, 2, 142, 167) },
{ name = "rank_01_anim0004.png", rect = cc.rect(2, 171, 142, 169) },
{ name = "rank_01_anim0005.png", rect = cc.rect(146, 171, 142, 173) },
{ name = "rank_01_anim0006.png", rect = cc.rect(290, 171, 142, 175) },
{ name = "rank_01_anim0007.png", rect = cc.rect(2, 348, 142, 173) },
{ name = "rank_01_anim0008.png", rect = cc.rect(146, 348, 142, 171) },
{ name = "rank_01_anim0009.png", rect = cc.rect(290, 348, 142, 169) },
{ name = "rank_01_anim0010.png", rect = cc.rect(2, 523, 142, 167) },
{ name = "rank_01_anim0011.png", rect = cc.rect(146, 523, 142, 167) },
{ name = "rank_01_anim0012.png", rect = cc.rect(290, 523, 142, 165) },
{ name = "rank_01_anim0013.png", rect = cc.rect(2, 692, 142, 163) },
{ name = "rank_01_anim0014.png", rect = cc.rect(146, 692, 142, 161) },
{ name = "rank_01_anim0015.png", rect = cc.rect(290, 692, 142, 161) }
}

local g_rank_01_count = table.getn(g_rank_01_anim_rect)

local g_jumper1_rect  = 
{
{ name = "frog-jump0001.png", rect = cc.rect(2, 2, 72, 84) },
{ name = "frog-jump0002.png", rect = cc.rect(76, 2, 72, 106) },
{ name = "frog-jump0003.png", rect = cc.rect(150, 2, 76, 106) },
{ name = "frog-jump0004.png", rect = cc.rect(228, 2, 82, 96) },
{ name = "frog-jump0005.png", rect = cc.rect(312, 2, 92, 86) },
{ name = "frog-jump0006.png", rect = cc.rect(406, 2, 92, 98) },
{ name = "frog-jump0007.png", rect = cc.rect(500, 2, 92, 96) },
{ name = "frog-jump0008.png", rect = cc.rect(594, 2, 78, 84) },
{ name = "frog-jump0009.png", rect = cc.rect(674, 2, 74, 82) },
{ name = "frog-jump0010.png", rect = cc.rect(750, 2, 78, 80) },
{ name = "frog-jump0011.png", rect = cc.rect(830, 2, 74, 84) }
}

local g_jumper1_count = table.getn(g_jumper1_rect)

local g_jumper2_rect = 
{
{ name = "frog-jump0018.png", rect = cc.rect(906, 2, 92, 86) },
{ name = "frog-jump0019.png", rect = cc.rect(2, 110, 92, 98) },
{ name = "frog-jump0020.png", rect = cc.rect(96, 110, 92, 96) },
{ name = "frog-jump0021.png", rect = cc.rect(190, 110, 78, 84) },
{ name = "frog-jump0022.png", rect = cc.rect(270, 110, 74, 82) },
{ name = "frog-jump0023.png", rect = cc.rect(346, 110, 152, 88) },
{ name = "frog-jump0024.png", rect = cc.rect(500, 110, 126, 92) },
{ name = "frog-jump0025.png", rect = cc.rect(628, 110, 134, 98) },
{ name = "frog-jump0026.png", rect = cc.rect(764, 110, 144, 98) },
{ name = "frog-jump0027.png", rect = cc.rect(2, 210, 154, 98) },
{ name = "frog-jump0028.png", rect = cc.rect(158, 210, 144, 82) },
{ name = "frog-jump0029.png", rect = cc.rect(304, 210, 168, 88) },
{ name = "frog-jump0030.png", rect = cc.rect(474, 210, 168, 86) },
{ name = "frog-jump0031.png", rect = cc.rect(644, 210, 184, 88) },
{ name = "frog-jump0032.png", rect = cc.rect(830, 210, 184, 88) },
{ name = "frog-jump0033.png", rect = cc.rect(2, 310, 184, 84) },
{ name = "frog-jump0034.png", rect = cc.rect(188, 310, 184, 84) },
{ name = "frog-jump0035.png", rect = cc.rect(374, 310, 184, 76) },
{ name = "frog-jump0036.png", rect = cc.rect(560, 310, 184, 74) },
{ name = "frog-jump0037.png", rect = cc.rect(746, 310, 180, 62) },
{ name = "frog-jump0038.png", rect = cc.rect(2, 396, 180, 62) },
{ name = "frog-jump0039.png", rect = cc.rect(184, 396, 178, 62) },
{ name = "frog-jump0040.png", rect = cc.rect(364, 396, 178, 62) },
{ name = "frog-jump0041.png", rect = cc.rect(544, 396, 176, 50) },
{ name = "frog-jump0042.png", rect = cc.rect(722, 396, 178, 50) },
{ name = "frog-jump0043.png", rect = cc.rect(2, 460, 180, 40) },
{ name = "frog-jump0044.png", rect = cc.rect(184, 460, 178, 40) },
{ name = "frog-jump0045.png", rect = cc.rect(364, 460, 178, 28) },
{ name = "frog-jump0046.png", rect = cc.rect(544, 460, 174, 28) },
{ name = "frog-jump0047.png", rect = cc.rect(720, 460, 174, 28) },
{ name = "frog-jump0048.png", rect = cc.rect(2, 502, 174, 26) },
{ name = "frog-jump0049.png", rect = cc.rect(178, 502, 174, 26) },
{ name = "frog-jump0050.png", rect = cc.rect(354, 502, 174, 26) },
{ name = "frog-jump0051.png", rect = cc.rect(530, 502, 174, 26) },
{ name = "frog-jump0052.png", rect = cc.rect(706, 502, 174, 26) },
{ name = "frog-jump0053.png", rect = cc.rect(2, 530, 174, 26) },
{ name = "frog-jump0054.png", rect = cc.rect(178, 530, 176, 26) },
{ name = "frog-jump0055.png", rect = cc.rect(356, 530, 176, 26) },
{ name = "frog-jump0056.png", rect = cc.rect(534, 530, 176, 28) },
{ name = "frog-jump0057.png", rect = cc.rect(712, 530, 176, 28) },
{ name = "frog-jump0058.png", rect = cc.rect(2, 560, 152, 24) },
{ name = "frog-jump0059.png", rect = cc.rect(156, 560, 152, 26) },
{ name = "frog-jump0060.png", rect = cc.rect(310, 560, 118, 20) },
{ name = "frog-jump0061.png", rect = cc.rect(430, 560, 122, 20) },
{ name = "frog-jump0062.png", rect = cc.rect(554, 560, 126, 20) },
{ name = "frog-jump0063.png", rect = cc.rect(682, 560, 132, 22) },
{ name = "frog-jump0064.png", rect = cc.rect(816, 560, 0, 0) }
}

local g_ButtonInfo = 
{
{ jumpbutton = nil, normalRes = nil, selectRes = nil, x = 480.0/6, y = 260.0, w = 143.0, h = 81.0 },
{ jumpbutton = nil, normalRes = nil, selectRes = nil, x = 480.0/6*4.5, y = 260.0, w = 143.0, h = 81.0 }
}

local g_cloudRect = 
{
{ x = 480.0/6, y = 20 },
{ x = 480.0/6 * 2.5 , y = 40 },
{ x = 480.0/6 * 4.5 , y = 60 }
}

local g_jumper2_count = table.getn(g_jumper2_rect)

local g_scale = 480.0/1024.0*1.4
local scheduler = cc.Director:getInstance():getScheduler()
local schedulerEntry = nil
local g_FlogJump_Ani = nil
local g_FlogJump_animFrames = {}
local g_FlogJJumpWater_Ani = nil
local g_FlogJJumpWater_animFrames = {}
local g_FlogNomal_Ani = nil
local g_FlogNomal_animFrames = {}
local g_bgbigSpriteFrames = {}
local g_flogSprite
local g_flogSpriteWater
local g_flogIndex = 3
local g_backButton = nil

local g_SenceFar = { frame1 = nil, frame2 = nil, offsetx = 0.0, timeCount = 0 }
local g_SenceMid = { frame1 = nil, frame2 = nil,  offsetx = 0.0, timeCount = 0 }
local g_SenceNearly = { frame = {}, countScreen = 24, offsetx = 0.0, timeCount = 0 }
local g_actionTo = nil

local sceneGame
local schedulerOver = nil

local stopMenuItem = nil
local startMenuItem = nil

local g_speed = 5


local function exitRun()
	if schedulerEntry ~= nil then
		scheduler:unscheduleScriptEntry(schedulerEntry)
	end
end

local function entryOverSence()
	if schedulerOver ~= nil then
		scheduler:unscheduleScriptEntry(schedulerOver)
	end
	exitRun()
	local level = getInteger( (g_flogIndex / 6) / (g_SenceNearly.countScreen / 5) ) + 1
	onReplaceOverScene(level)
	g_SenceFar = { frame1 = nil, frame2 = nil, offsetx = 0.0, timeCount = 0 }
	g_SenceMid = { frame1 = nil, frame2 = nil,  offsetx = 0.0, timeCount = 0 }
	g_SenceNearly = { frame = {}, countScreen = 24, offsetx = 0.0, timeCount = 0 }
	g_flogIndex = 3
end

local function entryStartSence()
	if schedulerOver ~= nil then
		scheduler:unscheduleScriptEntry(schedulerOver)
	end
	exitRun()
	local level = getInteger( (g_flogIndex / 6) / (g_SenceNearly.countScreen / 5) ) + 1
	onReplaceMainScene()
	g_SenceFar = { frame1 = nil, frame2 = nil, offsetx = 0.0, timeCount = 0 }
	g_SenceMid = { frame1 = nil, frame2 = nil,  offsetx = 0.0, timeCount = 0 }
	g_SenceNearly = { frame = {}, countScreen = 24, offsetx = 0.0, timeCount = 0 }
	g_flogIndex = 3
end

local function gameOver()
	schedulerOver = scheduler:scheduleScriptFunc(entryOverSence, 2.0, false)
end

local function update()
	if  g_SenceFar.timeCount > 1 then
		g_SenceFar.timeCount = 0
		
		if g_SenceFar.offsetx > _G.visibleSize.width then
			g_SenceFar.offsetx = g_SenceFar.offsetx%_G.visibleSize.width
			local tmp = g_SenceFar.frame1
			g_SenceFar.frame1 = g_SenceFar.frame2
			g_SenceFar.frame2 = tmp
		end
		local index
		for  index = 1,3 do
			g_SenceFar.frame1[index]:setPosition( g_SenceFar.offsetx + _G.origin.x + g_cloudRect[index].x,_G.origin.y + _G.visibleSize.height - g_cloudRect[index].y)
		end
		
		for  index = 1,3 do
			g_SenceFar.frame2[index]:setPosition( -_G.visibleSize.width + g_SenceFar.offsetx + _G.origin.x + g_cloudRect[index].x,_G.origin.y + _G.visibleSize.height - g_cloudRect[index].y)
		end
		g_SenceFar.offsetx = g_SenceFar.offsetx + 1
	end
	g_SenceFar.timeCount = g_SenceFar.timeCount + 1
	
	if g_SenceNearly.timeCount > 1 then
		g_SenceNearly.timeCount = 0
		if g_SenceNearly.offsetx > _G.visibleSize.width*g_SenceNearly.countScreen then
			g_SenceNearly.offsetx = g_SenceNearly.offsetx % ( _G.visibleSize.width*g_SenceNearly.countScreen )
		end
		
		local index
		local frameIndex
		local count = 6
		local onescreen = g_SenceNearly.countScreen/12
		for frameIndex = 1, g_SenceNearly.countScreen do
			for index = 1,count do
				local springboard = g_SenceNearly.frame[(frameIndex - 1)*count + index]
				springboard.sprite:setPosition(springboard.x - g_SenceNearly.offsetx,springboard.y)				
			end
		end
		
		if (g_flogSprite:getPositionX() < 0 or g_flogSprite:getPositionX() > 480.0) and g_flogSpriteWater:isVisible() == false then
			g_flogSpriteWater:setPosition(g_flogSprite:getPositionX(), g_flogSprite:getPositionY() )
			g_flogSpriteWater:setVisible(true)
			g_flogSprite:setVisible(false)
			gameOver()
		end
		if g_flogIndex < 24 then
			g_speed = 5
		elseif(g_flogIndex < 48)then
			g_speed = 6
		elseif(g_flogIndex < 72)then
			g_speed = 6.5
		elseif(g_flogIndex < 120)then
			g_speed = 7
		else
			g_speed = 7.5
		end
		
		g_flogSprite:setPosition(g_flogSprite:getPositionX() - g_speed, g_flogSprite:getPositionY() )
		g_SenceNearly.offsetx = g_SenceNearly.offsetx + g_speed
	end
	g_SenceNearly.timeCount = g_SenceNearly.timeCount + 1
end

local function initRun()
	schedulerEntry = scheduler:scheduleScriptFunc(update, 0.0, false)
end


-- handing touch events
local touchBeginPoint = 0
local function onTouchBegan(touch, event)
	local location = touch:getLocation()
	cclog("onTouchBegan: %0.2f, %0.2f", location.x, location.y)
	local index
	for  index = 1,2 do
		local rect = cc.rect( g_ButtonInfo[index].jumpbutton:getPositionX() - g_ButtonInfo[index].w/2,
					g_ButtonInfo[index].jumpbutton:getPositionY() - g_ButtonInfo[index].h/2,
					g_ButtonInfo[index].w,g_ButtonInfo[index].h)
		if cc.rectContainsPoint(rect, location) then
			--g_ButtonInfo[index].jumpbutton:setSpriteFrame(g_ButtonInfo[index].selectRes)
			touchBeginPoint = index
			
			springboard = g_SenceNearly.frame[ g_flogIndex ]
			g_flogSprite:setPosition(springboard.sprite:getPositionX(),springboard.sprite:getPositionY() + 20)
			g_flogIndex = g_flogIndex + index
			springboard = g_SenceNearly.frame[ g_flogIndex ]
			--cclog("g_flogIndex === %d",g_flogIndex)
			--cclog("springboard.isVisible === %s",springboard.isVisible)
			if  springboard.isVisible then
				g_actionTo = cc.JumpTo:create(0.5, cc.p(springboard.sprite:getPositionX(),springboard.sprite:getPositionY() + 20), 30, 1)
				g_flogSprite:runAction(cc.Sequence:create(g_actionTo))
			else
				g_flogSpriteWater:setPosition(g_flogSprite:getPositionX(), g_flogSprite:getPositionY() )
				g_flogSpriteWater:setVisible(true)
				g_flogSprite:setVisible(false)
				gameOver()
			end

			--cclog("touchBeginPoint===%d",touchBeginPoint)
			cclog("rect.x = %d,y=%d,w=%d,h=%d",rect.x,rect.y,rect.width,rect.height)
		end
					
	end
	
	local buttonrect = g_backButton:getTextureRect()
	local rect = cc.rect( g_backButton:getPositionX() - buttonrect.width/2,
			g_backButton:getPositionY() - buttonrect.height/2,
			buttonrect.width,buttonrect.height)
	if cc.rectContainsPoint(rect, location) then
		cclog("rect.x = %d,y=%d,w=%d,h=%d",rect.x,rect.y,rect.width,rect.height)
		entryStartSence()
	end
		
	return true
end

local function onTouchMoved(touch, event)
	local location = touch:getLocation()
	--cclog("onTouchMoved: %0.2f, %0.2f", location.x, location.y)

end

local function onTouchEnded(touch, event)
	local location = touch:getLocation()
	--cclog("onTouchEnded: %0.2f, %0.2f", location.x, location.y)
	touchBeginPoint = 0
end

local function onKeyReleased(code, event)
	if code == cc.KeyCode.KEY_BACK then
	cc.Director:getInstance():endToLua()
	elseif code == cc.KeyCode.KEY_HOME then
	cc.Director:getInstance():endToLua()
	end
end

local universal_SpriteFrames = {}
local function initPublicSpriteFrame()
	local bgSpriteFrame = cc.SpriteFrame:create("universal_button_big.png",cc.rect(0,0,256,512))
	local bg_texture = bgSpriteFrame:getTexture()
	for index = 1,universal_button_count do
		universal_SpriteFrames[index] = cc.SpriteFrame:createWithTexture(bg_texture,_G.universal_button_rect[index].rect)
	end
end

local function createAnimation()
	
	initPublicSpriteFrame()
	
	local rank_01_spriteFrame = cc.SpriteFrame:create("rank_01_anim.png",cc.rect(0,0,512,1024))
	local rank_01_texture = rank_01_spriteFrame:getTexture()
	
	local index = 0
	for index = 1,g_rank_01_count do
		g_FlogNomal_animFrames[index] = cc.SpriteFrame:createWithTexture(rank_01_texture,g_rank_01_anim_rect[index].rect) 
	end
	g_FlogNomal_Ani = cc.Animation:createWithSpriteFrames(g_FlogNomal_animFrames, 0.05)
	
	local jumper2_spriteFrame = cc.SpriteFrame:create("jumper2.png",cc.rect(0,0,1024,1024))
	local jumper2_texture = jumper2_spriteFrame:getTexture()
	for index = 1,g_jumper1_count do
		g_FlogJump_animFrames[index] = cc.SpriteFrame:createWithTexture(jumper2_texture,g_jumper1_rect[index].rect)
	end
	g_FlogJump_Ani = cc.Animation:createWithSpriteFrames(g_FlogJump_animFrames, 0.05) 
	
	for index = 1,g_jumper2_count do
		g_FlogJJumpWater_animFrames[index] = cc.SpriteFrame:createWithTexture(jumper2_texture,g_jumper2_rect[index].rect)
	end
	g_FlogJJumpWater_Ani = cc.Animation:createWithSpriteFrames(g_FlogJJumpWater_animFrames, 0.05) 
	
	local bgbigSpriteFrame = cc.SpriteFrame:create("bgbig2.png",cc.rect(0,0,1024,512))
	local bgbig_texture = bgbigSpriteFrame:getTexture()
	for index = 1,g_bgbig_count do
		g_bgbigSpriteFrames[index] = cc.SpriteFrame:createWithTexture(bgbig_texture,g_bgbig_rect[index].rect)
	end

end

local function startCallback(tag)
	g_startREC = 1
	sharerec.ShareRec:startRecorder()
	if g_startREC == 1 then
		stopMenuItem:setVisible(true)
		startMenuItem:setVisible(false)
	else
		startMenuItem:setVisible(true)
		stopMenuItem:setVisible(false)
	end
end

local function stopCallback(tag)
	g_startREC = 0
	sharerec.ShareRec:stopRecorder()
	if g_startREC == 1 then
		stopMenuItem:setVisible(true)
		startMenuItem:setVisible(false)
	else
		startMenuItem:setVisible(true)
		stopMenuItem:setVisible(false)
	end
end


local function createLayBack()
	
	createAnimation()
	
	--创建游戏底层背景
	local layerFarm = cc.Layer:create()
	local rect = cc.rect(0,0,_G.visibleSize.width,_G.visibleSize.height)
	local bg = cc.Sprite:create("bgtile.png")
	local bgrect = bg:getTextureRect()
	bg:setPosition(_G.origin.x + _G.visibleSize.width / 2, _G.origin.y + _G.visibleSize.height / 2)
	bg:setScale(rect.width/bgrect.width,rect.height/bgrect.height)
	layerFarm:addChild(bg)
	
	--创建云
	local cloudSprite = {}
	local cloudSprite2 = {}
	local index
	for  index = 1,3 do
		cloudSprite[index] = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[4 + index])
		cloudSprite[index]:setPosition( _G.origin.x + g_cloudRect[index].x,_G.origin.y + _G.visibleSize.height - g_cloudRect[index].y)
		layerFarm:addChild(cloudSprite[index])
	end
	
	for  index = 1,3 do
		cloudSprite2[index] = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[4 + index])
		cloudSprite2[index]:setPosition( _G.visibleSize.width + _G.origin.x + g_cloudRect[index].x,_G.origin.y + _G.visibleSize.height - g_cloudRect[index].y)
		layerFarm:addChild(cloudSprite2[index])
	end
	g_SenceFar.frame1 = cloudSprite
	g_SenceFar.frame2 = cloudSprite2

	--创建近背景
	local bg2 = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[1])
	bg2:setPosition(_G.origin.x + _G.visibleSize.width / 2, _G.origin.y + _G.visibleSize.height - g_bgbig_rect[1].rect.height*g_scale/2 )
	bg2:setScale(g_scale)
	layerFarm:addChild(bg2)
	
	--触摸事件监听
	local listener = cc.EventListenerTouchOneByOne:create()
	listener:registerScriptHandler(onTouchBegan,cc.Handler.EVENT_TOUCH_BEGAN )
	listener:registerScriptHandler(onTouchMoved,cc.Handler.EVENT_TOUCH_MOVED )
	listener:registerScriptHandler(onTouchEnded,cc.Handler.EVENT_TOUCH_ENDED )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)

	--创建跳板
	local startId = getIndexByArrayInfo( "objects0001.png" , g_bgbig_rect )
	local count = 6

	local startx = 480.0/(count*2)
	local starty = 180
	local interval = 480.0/count
	local frameIndex
	local onescreen = g_SenceNearly.countScreen/12
	for frameIndex = 1, g_SenceNearly.countScreen do
		local frameId = getInteger( (frameIndex - 1) / onescreen )
		local tmp = math.random(1,6)
		if frameIndex > 1  then
			if g_SenceNearly.frame[ (frameIndex - 1)*count ].isVisible == false and tmp == 1  then
				tmp = tmp + 1
			end
		end
		if  frameIndex == 1 and tmp == g_flogIndex then
			g_flogIndex = g_flogIndex + 1
		end
		for index = 1,count do
			local springboard = { sprite = nil, index = 0, x = 0.0, y = 0, oneScreen = count, isVisible = true }
			springboard.index = (frameIndex - 1)*count + index 
			springboard.x = (frameIndex - 1)*_G.visibleSize.width + startx + interval*(index-1)
			springboard.y = _G.origin.y + _G.visibleSize.height - starty
			springboard.sprite = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[startId + frameId])
			springboard.sprite:setPosition(springboard.x,springboard.y)
			springboard.sprite:setScale(g_scale)
			layerFarm:addChild(springboard.sprite)
			if tmp == index then
				springboard.sprite:setVisible(false)
				springboard.isVisible = false
			end
			g_SenceNearly.frame[ springboard.index ] = springboard
		end
	end

	
	--创建青蛙
	g_flogSpriteWater = cc.Sprite:createWithSpriteFrame(g_FlogJJumpWater_animFrames[1])
	g_flogSpriteWater:runAction(cc.RepeatForever:create(cc.Animate:create(g_FlogJJumpWater_Ani)) )
	g_flogSpriteWater:setVisible(false)
	g_flogSpriteWater:setScale(g_scale)
	layerFarm:addChild(g_flogSpriteWater)
	g_flogSprite = cc.Sprite:createWithSpriteFrame(g_FlogNomal_animFrames[1])
	g_flogSprite:runAction(cc.RepeatForever:create( cc.Animate:create(g_FlogNomal_Ani) ) )
	--g_flogSprite:runAction(cc.Animate:create(g_FlogJJumpWater_Ani) )
	--local actionBy = cc.JumpBy:create(0.5, cc.p(100,0), 30, 1)
	--local actionByBack = actionBy:reverse()
	--g_flogSprite:runAction(cc.RepeatForever:create( cc.Animate:create(g_FlogJump_Ani) ) )
	--g_flogSprite:runAction(cc.RepeatForever:create(cc.Sequence:create(actionBy)))
	local springboard = g_SenceNearly.frame[ g_flogIndex ]
	g_flogSprite:setPosition(springboard.x, springboard.y + 20 )
	g_flogSprite:setScale(g_scale*0.5)
	layerFarm:addChild(g_flogSprite)
	
	--创建按钮
	g_ButtonInfo[1].normalRes = g_bgbigSpriteFrames[getIndexByArrayInfo( "jumpbutton10.png" , g_bgbig_rect )]
	g_ButtonInfo[1].selectRes = g_bgbigSpriteFrames[getIndexByArrayInfo( "jumpbutton11.png" , g_bgbig_rect )]
	g_ButtonInfo[1].jumpbutton = cc.Sprite:createWithSpriteFrame(g_ButtonInfo[1].normalRes)
	g_ButtonInfo[1].jumpbutton:setPosition( _G.origin.x + g_ButtonInfo[1].x,_G.origin.y + _G.visibleSize.height - g_ButtonInfo[1].y + 10)
	g_ButtonInfo[1].jumpbutton:setScale(g_scale)
	layerFarm:addChild(g_ButtonInfo[1].jumpbutton)
	
	g_ButtonInfo[2].normalRes = g_bgbigSpriteFrames[getIndexByArrayInfo( "jumpbutton20.png" , g_bgbig_rect )]
	g_ButtonInfo[2].selectRes = g_bgbigSpriteFrames[getIndexByArrayInfo( "jumpbutton21.png" , g_bgbig_rect )]
	g_ButtonInfo[2].jumpbutton = cc.Sprite:createWithSpriteFrame(g_ButtonInfo[2].normalRes)
	g_ButtonInfo[2].jumpbutton:setPosition( _G.origin.x + g_ButtonInfo[2].x,_G.origin.y + _G.visibleSize.height - g_ButtonInfo[2].y + 10)
	g_ButtonInfo[2].jumpbutton:setScale(g_scale)
	layerFarm:addChild(g_ButtonInfo[2].jumpbutton)
	
	local MainMenu = cc.Menu:create()
	local startLabel = cc.Label:createWithTTF("StartRec", s_markerFeltFontPath, 24)
    startLabel:setAnchorPoint(cc.p(0.5, 0.5))
	startMenuItem = cc.MenuItemLabel:create(startLabel)
	startMenuItem:registerScriptTapHandler(startCallback)
	MainMenu:addChild(startMenuItem, 10001, 10001)
	
	local stopLabel = cc.Label:createWithTTF("StopRec", s_markerFeltFontPath, 24)
	stopMenuItem = cc.MenuItemLabel:create(stopLabel)
	stopMenuItem:registerScriptTapHandler(stopCallback)
	MainMenu:addChild(stopMenuItem, 10002, 10002)
	
	MainMenu:setPosition(_G.origin.x + _G.visibleSize.width - 50 , _G.origin.y + _G.visibleSize.height - 30 )
	layerFarm:addChild(MainMenu)
	
	if g_startREC == 1 then
		stopMenuItem:setVisible(true)
		startMenuItem:setVisible(false)
	else
		startMenuItem:setVisible(true)
		stopMenuItem:setVisible(false)
	end
	
	--创建返回按钮
	local startId = getIndexByArrayInfo( "arrow.png" , _G.universal_button_rect )
	g_backButton = cc.Sprite:createWithSpriteFrame(universal_SpriteFrames[startId])
	bgrect = g_backButton:getTextureRect()
	g_backButton:setPosition(_G.origin.x + bgrect.width , _G.origin.y + _G.visibleSize.height - bgrect.height)
	layerFarm:addChild(g_backButton)
	
	--监听lay退出
	local function onNodeEvent(event)
		if event == "enter" then
		elseif event == "exit" then
		end
	end
	layerFarm:registerScriptHandler(onNodeEvent)
	
	local listener = cc.EventListenerKeyboard:create()
	listener:registerScriptHandler(onKeyReleased, cc.Handler.EVENT_KEYBOARD_RELEASED )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)

	return layerFarm
end


function onReplaceStartScene()
	cc.Director:getInstance():purgeCachedData()
	initRun()
	sceneGame = cc.Scene:create()
	sceneGame:addChild(createLayBack())
    cc.Director:getInstance():replaceScene(sceneGame)
end
