
local g_green_background_rect = 
{
{ name = "bottom_line.png", rect = cc.rect(0, 554, 854, 70) },
{ name = "green_background.png", rect = cc.rect(0, 0, 854, 480) },
{ name = "top_line.png", rect = cc.rect(0, 482, 854, 70) },
}

local g_star_getting_rect = 
{
{ name = "star_animation_010001.png", rect = cc.rect(0, 0, 68, 68) },
--[[{ name = "star_animation_010002.png", rect = cc.rect(68, 0, 68, 68) },
{ name = "star_animation_010003.png", rect = cc.rect(136, 0, 68, 68) },
{ name = "star_animation_010004.png", rect = cc.rect(0, 68, 68, 68) },
{ name = "star_animation_010005.png", rect = cc.rect(68, 68, 68, 68) },
{ name = "star_animation_010006.png", rect = cc.rect(136, 68, 68, 68) },
{ name = "star_animation_010007.png", rect = cc.rect(0, 136, 68, 68) },
{ name = "star_animation_010008.png", rect = cc.rect(68, 136, 68, 68) },
{ name = "star_animation_010009.png", rect = cc.rect(136, 136, 68, 68) },
{ name = "star_animation_010010.png", rect = cc.rect(0, 204, 68, 68) },
{ name = "star_animation_010011.png", rect = cc.rect(68, 204, 68, 68) },
{ name = "star_animation_010012.png", rect = cc.rect(136, 204, 68, 68) },
{ name = "star_animation_010013.png", rect = cc.rect(0, 272, 68, 68) },--]]

}

local g_green_background_count = table.getn(g_green_background_rect)
local g_bgbigSpriteFrames = {}
local g_star_getting_count = table.getn(g_star_getting_rect)
local g_starGettingFrames = {}

local g_scale = 480.0/1024.0*1.4
local universal_SpriteFrames = {}
local startButton
local g_level = 1

local function initPublicSpriteFrame()
	local bgSpriteFrame = cc.SpriteFrame:create("universal_button_big.png",cc.rect(0,0,256,512))
	local bg_texture = bgSpriteFrame:getTexture()
	for index = 1,universal_button_count do
		universal_SpriteFrames[index] = cc.SpriteFrame:createWithTexture(bg_texture,universal_button_rect[index].rect)
	end
	
	bgSpriteFrame = cc.SpriteFrame:create("star_getting.png",cc.rect(0,0,256,512))
	bg_texture = bgSpriteFrame:getTexture()
	for index = 1,g_star_getting_count do
		g_starGettingFrames[index] = cc.SpriteFrame:createWithTexture(bg_texture,g_star_getting_rect[index].rect)
	end
end


-- handing touch events
local touchBeginPoint = 0
local function onTouchBegan(touch, event)
	local location = touch:getLocation()
	cclog("onTouchBegan: %0.2f, %0.2f", location.x, location.y)
	local startButtonrect = startButton:getTextureRect()
	local rect = cc.rect( startButton:getPositionX() - startButtonrect.width/2,
			startButton:getPositionY() - startButtonrect.height/2,
			startButtonrect.width,startButtonrect.height)
	if cc.rectContainsPoint(rect, location) then
		cclog("rect.x = %d,y=%d,w=%d,h=%d",rect.x,rect.y,rect.width,rect.height)
		onReplaceStartScene()
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


local function createOverAnimation()
	initPublicSpriteFrame()
	local bgSpriteFrame = cc.SpriteFrame:create("green_background.png",cc.rect(0,0,1024,1024))
	local bg_texture = bgSpriteFrame:getTexture()
	for index = 1,g_green_background_count do
		g_bgbigSpriteFrames[index] = cc.SpriteFrame:createWithTexture(bg_texture,g_green_background_rect[index].rect)
	end
end


local function onKeyReleased(code, event)
	if code == cc.KeyCode.KEY_BACK then
	cc.Director:getInstance():endToLua()
	elseif code == cc.KeyCode.KEY_HOME then
	cc.Director:getInstance():endToLua()
	end
end

local function createOverLayBack()
	
	createOverAnimation()
	
	--´´½¨ÓÎÏ·µ×²ã±³¾°
	local layerFarm = cc.Layer:create()
	local rect = cc.rect(0,0,_G.visibleSize.width,_G.visibleSize.height)
	local bg = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[2])
	local bgrect = bg:getTextureRect()
	bg:setPosition(_G.origin.x + _G.visibleSize.width / 2, _G.origin.y + _G.visibleSize.height / 2)
	bg:setScale(rect.width/bgrect.width,rect.height/bgrect.height)
	layerFarm:addChild(bg)
	
	local bgTop = cc.Sprite:createWithSpriteFrame(g_bgbigSpriteFrames[1])
	bgrect = bgTop:getTextureRect()
	bgTop:setPosition(_G.origin.x + _G.visibleSize.width / 2, _G.origin.y + _G.visibleSize.height / 2)
	bgTop:setScale(g_scale)
	layerFarm:addChild(bgTop)
	
	local startId = getIndexByArrayInfo( "replay.png" , _G.universal_button_rect )
	startButton = cc.Sprite:createWithSpriteFrame(universal_SpriteFrames[startId])
	bgrect = startButton:getTextureRect()
	startButton:setPosition(_G.origin.x + _G.visibleSize.width / 2, _G.origin.y + _G.visibleSize.height / 3)
	layerFarm:addChild(startButton)
	
	local index
	for index = 1,g_level  do
		local sprite = cc.Sprite:createWithSpriteFrame(g_starGettingFrames[1])
		bgrect = sprite:getTextureRect()
		sprite:setPosition(_G.origin.x + _G.visibleSize.width / 2  - ( (g_level + 1)*(10 + bgrect.width) / 2 ) + index*(10 + bgrect.width), 
							_G.origin.y + _G.visibleSize.height * 2 / 3)
		layerFarm:addChild(sprite)
	end

	
	--´¥ÃþÊÂ¼þ¼àÌý
	local listener = cc.EventListenerTouchOneByOne:create()
	listener:registerScriptHandler(onTouchBegan,cc.Handler.EVENT_TOUCH_BEGAN )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)
	
	local listener = cc.EventListenerKeyboard:create()
	listener:registerScriptHandler(onKeyReleased, cc.Handler.EVENT_KEYBOARD_RELEASED )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)

	return layerFarm
end


function onReplaceOverScene(level)
	g_level = level
	cc.Director:getInstance():purgeCachedData()
	local sceneGame = cc.Scene:create()
	sceneGame:addChild(createOverLayBack())
    cc.Director:getInstance():replaceScene(sceneGame)
end