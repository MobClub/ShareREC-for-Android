cc.FileUtils:getInstance():addSearchPath("src")
cc.FileUtils:getInstance():addSearchPath("res")
-- CC_USE_DEPRECATED_API = true
require "cocos.init"
require "flogUtils"
require "flogGame"
require "flogOver"


visibleSize = cc.size(0,0)
origin = cc.size(0,0)

local flogSpriteFrames = {}

function entryVideoCenter()
	cclog("entryVideoCenter=========")
end

local function initGLView()
    local director = cc.Director:getInstance()
    local glView = director:getOpenGLView()
    if nil == glView then
        glView = cc.GLViewImpl:create("flogMain")
        director:setOpenGLView(glView)
    end

    director:setOpenGLView(glView)

    glView:setDesignResolutionSize(480, 320, cc.ResolutionPolicy.NO_BORDER)

    director:setDisplayStats(true)

    director:setAnimationInterval(1.0 / 60)

end


local function onKeyReleased(code, event)
	if code == cc.KeyCode.KEY_BACK then
	cc.Director:getInstance():endToLua()
	elseif code == cc.KeyCode.KEY_HOME then
	cc.Director:getInstance():endToLua()
	end
end


local function startCallback(tag)
	if g_startREC == 0 then
		g_startREC = 1
		sharerec.ShareRec:startRecorder()	
	end
	onReplaceStartScene()
end

local function createStartBack()
	--´´½¨ÓÎÏ·µ×²ã±³¾°
	local layerFarm = cc.Layer:create()
	local rect = cc.rect(0,0,visibleSize.width,visibleSize.height)
	local bgSpriteFrame = cc.SpriteFrame:create("background.png",cc.rect(2,2,856,481))
	local bg = cc.Sprite:createWithSpriteFrame(bgSpriteFrame)
	local bgrect = bg:getTextureRect()
	bg:setPosition(origin.x + visibleSize.width / 2, origin.y + visibleSize.height / 2)
	bg:setScale(rect.width/bgrect.width,rect.height/bgrect.height)
	layerFarm:addChild(bg)
	
	local index
	for index = 1,16 do
		flogSpriteFrames[index] = cc.SpriteFrame:create("start" .. index .. ".png",cc.rect(0,0,110,98))
	end
	local FlogJump_Ani = cc.Animation:createWithSpriteFrames(flogSpriteFrames, 0.05) 
	
	local startButton = cc.Sprite:create("start.png")
	startButton:setPosition(origin.x + visibleSize.width / 2, origin.y + visibleSize.height / 2)
	layerFarm:addChild(startButton)
	startButton:runAction(cc.RepeatForever:create(cc.Animate:create(FlogJump_Ani)) )
	
	local startButtonrect = startButton:getTextureRect()
	
	local touchBeginPoint = 0
	local function onTouchBegan(touch, event)
		local location = touch:getLocation()
		local rect = cc.rect( startButton:getPositionX() - startButtonrect.width/2,
					startButton:getPositionY() - startButtonrect.height/2,
					startButtonrect.width,startButtonrect.height)
		if cc.rectContainsPoint(rect, location) then
			cclog("rect.x = %d,y=%d,w=%d,h=%d",rect.x,rect.y,rect.width,rect.height)
			onReplaceStartScene()
		end

		return true
	end
	
	local MainMenu = cc.Menu:create()
	local startLabel = cc.Label:createWithTTF("StartRec", s_markerFeltFontPath, 24)
    startLabel:setAnchorPoint(cc.p(0.5, 0.5))
	local startMenuItem = cc.MenuItemLabel:create(startLabel)
	startMenuItem:registerScriptTapHandler(startCallback)
	MainMenu:addChild(startMenuItem, 10000, 10000)
	MainMenu:setPosition(origin.x + visibleSize.width / 2, origin.y + visibleSize.height / 4)
	layerFarm:addChild(MainMenu)
	
	--´¥ÃþÊÂ¼þ¼àÌý
	local listener = cc.EventListenerTouchOneByOne:create()
	listener:registerScriptHandler(onTouchBegan,cc.Handler.EVENT_TOUCH_BEGAN )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)
	
	local listener = cc.EventListenerKeyboard:create()
	listener:registerScriptHandler(onKeyReleased, cc.Handler.EVENT_KEYBOARD_RELEASED )
	local eventDispatcher = layerFarm:getEventDispatcher()
	eventDispatcher:addEventListenerWithSceneGraphPriority(listener, layerFarm)
	
	g_startREC = 0
	
	return layerFarm
end

function onReplaceMainScene()
	cc.Director:getInstance():purgeCachedData()
	local sceneEntry = cc.Scene:create()
	sceneEntry:addChild(createStartBack())
    cc.Director:getInstance():replaceScene(sceneEntry)
end

local function main()
	-- avoid memory leak
    collectgarbage("setpause", 100)
    collectgarbage("setstepmul", 5000)
	
	local ret = sharerec.ShareRec:isAvailable()
	cclog(" ret ===%s ",ret )

    initGLView()
	
	math.randomseed(os.time())
	
	visibleSize = cc.Director:getInstance():getVisibleSize()
	origin = cc.Director:getInstance():getVisibleOrigin()

	cclog("main-----width=" .. visibleSize.width .. "heith=" .. visibleSize.height.. "\n")

	cc.Director:getInstance():setDisplayStats(false)

	-- play background music, preload effect
    local bgMusicPath = cc.FileUtils:getInstance():fullPathForFilename("background.mp3") 
    cc.SimpleAudioEngine:getInstance():playMusic(bgMusicPath, true)
    local effectPath = cc.FileUtils:getInstance():fullPathForFilename("effect1.wav")
    cc.SimpleAudioEngine:getInstance():preloadEffect(effectPath)
	
	-- run
    local sceneEntry = cc.Scene:create()
	sceneEntry:addChild(createStartBack())
    cc.Director:getInstance():runWithScene(sceneEntry)
end

xpcall(main, __G__TRACKBACK__)