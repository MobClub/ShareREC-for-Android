#include "HelloWorldScene.h"
#include "AppMacros.h"
#include "sharerec_for_cocos2d.h"

USING_NS_CC;
using namespace cn::sharerec;

Scene* HelloWorld::scene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    HelloWorld *layer = HelloWorld::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HelloWorld::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
    
    auto visibleSize = Director::getInstance()->getVisibleSize();
    auto origin = Director::getInstance()->getVisibleOrigin();

    /////////////////////////////
    // 2. add a menu item with "X" image, which is clicked to quit the program
    //    you may modify it.

    // add a "close" icon to exit the progress. it's an autorelease object
    auto closeItem = MenuItemImage::create(
                                        "CloseNormal.png",
                                        "CloseSelected.png",
                                        CC_CALLBACK_1(HelloWorld::menuCloseCallback,this));
    
    closeItem->setPosition(origin + Vec2(visibleSize) - Vec2(closeItem->getContentSize() / 2));

    // create menu, it's an autorelease object
    auto menu = Menu::create(closeItem, nullptr);
    menu->setPosition(Vec2::ZERO);
    this->addChild(menu, 1);
    
    /////////////////////////////
    // 3. add your codes below...

    // add a label shows "Hello World"
    // create and initialize a label
    
    label = Label::createWithTTF("Hello World", "fonts/arial.ttf", TITLE_FONT_SIZE);
    
    // position the label on the center of the screen
    xOrigin = origin.x + label->getContentSize().width / 2;
	yOrigin = origin.y + label->getContentSize().height;
	xMax = origin.x + visibleSize.width - label->getContentSize().width / 2;
	yMax = origin.y + visibleSize.height - label->getContentSize().height;
    label->setPosition(xOrigin, yOrigin);

    // add the label as a child to this layer
    this->addChild(label, 1);

    // add "HelloWorld" splash screen"
    auto sprite = Sprite::create("HelloWorld.png");

    // position the sprite on the center of the screen
    sprite->setPosition(Vec2(visibleSize / 2) + origin);

    // add the sprite as a child to this layer
    this->addChild(sprite);

	// init demo menus
	initShareRECMenus();

	// start game logic
	xOffset = 0;
	yOffset = 0;
	xStep = 1;
	yStep = 1;
	this->schedule(schedule_selector(HelloWorld::updateGame));
	
    return true;
}

void HelloWorld::menuCloseCallback(Ref* sender)
{
    Director::getInstance()->end();

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
}

void HelloWorld::initShareRECMenus() {
    auto visibleSize = Director::getInstance()->getVisibleSize();
    auto origin = Director::getInstance()->getVisibleOrigin();
	
	// add start REC menu
	startLabel = Label::createWithTTF("Start REC", "fonts/arial.ttf", TITLE_FONT_SIZE / 2);
	startLabel->setTextColor(Color4B(0, 0, 0, 255));
	auto startItem = MenuItemLabel::create(startLabel, CC_CALLBACK_1(HelloWorld::menuStartRECCallback, this));
	startMenu = Menu::create(startItem, nullptr);
    startMenu->setPosition(Vec2(
		origin.x + startLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - startLabel->getContentSize().height));
    this->addChild(startMenu, 1);

	// add stop REC menu
	stopLabel = Label::createWithTTF("Stop REC", "fonts/arial.ttf", TITLE_FONT_SIZE / 2);
	stopLabel->setTextColor(Color4B(255, 0, 0, 255));
	auto stopItem = MenuItemLabel::create(stopLabel, CC_CALLBACK_1(HelloWorld::menuStopRECCallback, this));
	stoptMenu = Menu::create(stopItem, nullptr);
    stoptMenu->setPosition(Vec2(
		origin.x - stopLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - stopLabel->getContentSize().height));
    this->addChild(stoptMenu, 1);

	// add show video center menu
	vcLabel = Label::createWithTTF("Video Center", "fonts/arial.ttf", TITLE_FONT_SIZE / 2);
	vcLabel->setTextColor(Color4B(0, 0, 0, 255));
	auto vcItem = MenuItemLabel::create(vcLabel, CC_CALLBACK_1(HelloWorld::menuVedioCenterCallback, this));
	vcMenu = Menu::create(vcItem, nullptr);
    vcMenu->setPosition(Vec2(
		origin.x + vcLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - vcLabel->getContentSize().height * 2));
    this->addChild(vcMenu, 1);

	// add show user profile menu
	upLabel = Label::createWithTTF("User Profile", "fonts/arial.ttf", TITLE_FONT_SIZE / 2);
	upLabel->setTextColor(Color4B(0, 0, 0, 255));
	auto upItem = MenuItemLabel::create(upLabel, CC_CALLBACK_1(HelloWorld::menuUserProfileCallback, this));
	upMenu = Menu::create(upItem, nullptr);
    upMenu->setPosition(Vec2(
		origin.x + upLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - upLabel->getContentSize().height * 3));
    this->addChild(upMenu, 1);

}

void HelloWorld::updateGame(float dt) {
	float x = xOrigin + xOffset;
	if (x <= xOrigin) {
		xStep = 1;
	} else if (x >= xMax) {
		xStep = -1;
	}

	float y = yOrigin + yOffset;
	if (y <= yOrigin) {
		yStep = 1;
	} else if (y >= yMax) {
		yStep = -1;
	}
	
	xOffset += xStep;
	yOffset += yStep;
 	label->setPosition(xOrigin + xOffset, yOrigin + yOffset);
}

void HelloWorld::menuStartRECCallback(Ref* sender) {
	if (ShareRec::isAvailable()) {
		// switch menu
		auto visibleSize = Director::getInstance()->getVisibleSize();
	    auto origin = Director::getInstance()->getVisibleOrigin();
		startMenu->setPosition(Vec2(
			origin.x - startLabel->getContentSize().width / 2, 
			origin.y + visibleSize.height - startLabel->getContentSize().height));
	    stoptMenu->setPosition(Vec2(
			origin.x + stopLabel->getContentSize().width / 2, 
			origin.y + visibleSize.height - stopLabel->getContentSize().height));
		vcMenu->setPosition(Vec2(
			origin.x - vcLabel->getContentSize().width / 2, 
			origin.y + visibleSize.height - vcLabel->getContentSize().height * 2));
	    upMenu->setPosition(Vec2(
			origin.x - upLabel->getContentSize().width / 2, 
			origin.y + visibleSize.height - upLabel->getContentSize().height * 3));	
		
		// start ShareREC
		ShareRec::startRecorder();
	}
}

void HelloWorld::menuStopRECCallback(Ref* sender) {
	// switch menu
	auto visibleSize = Director::getInstance()->getVisibleSize();
    auto origin = Director::getInstance()->getVisibleOrigin();
	startMenu->setPosition(Vec2(
		origin.x + startLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - startLabel->getContentSize().height));
    stoptMenu->setPosition(Vec2(
		origin.x - stopLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - stopLabel->getContentSize().height));
	vcMenu->setPosition(Vec2(
		origin.x + vcLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - vcLabel->getContentSize().height * 2));
    upMenu->setPosition(Vec2(
		origin.x + upLabel->getContentSize().width / 2, 
		origin.y + visibleSize.height - upLabel->getContentSize().height * 3));

	// stop ShareREC
	ShareRec::setOnRecorderStateListener(HelloWorld::onStateChange);
	ShareRec::stopRecorder();
	
}

void onPlatformSelected(const char* name, MP4* mp4) {
	// CCLog��ӵ��������ȫ������(never mind)
	__android_log_print(ANDROID_LOG_DEBUG, "cocos2d-x debug info", "menu \"%s\" has been clicked!", name);
	char* path = NULL;
	__android_log_print(ANDROID_LOG_DEBUG, "cocos2d-x debug info", "%d", mp4->getLocalPath(&path));
	__android_log_print(ANDROID_LOG_DEBUG, "cocos2d-x debug info", "Video Path: %s", path == NULL ? "NULL" : path);
}

void HelloWorld::menuVedioCenterCallback(Ref* sender) {
	ShareRec::addCustomPlatform("CustomPlatform", onPlatformSelected);
	ShareRec::showVideoCenter();
}

void HelloWorld::menuUserProfileCallback(Ref* sender) {
	ShareRec::addCustomPlatform("CustomPlatform", onPlatformSelected);
	ShareRec::showProfile();
}

// this function will be called when ShareREC is stoping and stopped
void HelloWorld::onStateChange(int state) {
	if (state == STATE_STOPPED) {
		// show share page
		ShareRec::setText("C2d Cpp Demo");
		ShareRec::addCustomAttr("score", "5000");
		ShareRec::addCustomAttr("name", "ShareRec Developer");
		ShareRec::addCustomAttr("brand", "hehe!");
		ShareRec::addCustomAttr("level", "10");
		ShareRec::addCustomPlatform("CustomPlatform", onPlatformSelected);
		ShareRec::showShare();
	}
}


