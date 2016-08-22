#ifndef __HELLOWORLD_SCENE_H__
#define __HELLOWORLD_SCENE_H__

#include "cocos2d.h"
USING_NS_CC;


class HelloWorld : public cocos2d::Layer
{
public:
    virtual bool init() override;

    static cocos2d::Scene* scene();

    // a selector callback
    void menuCloseCallback(Ref* sender);

    // implement the "static create()" method manually
    CREATE_FUNC(HelloWorld);

private:
	void initShareRECMenus();
	void updateGame(float dt);
	void menuStartRECCallback(Ref* sender);
	void menuStopRECCallback(Ref* sender);
	void menuVedioCenterCallback(Ref* sender);
	void menuUserProfileCallback(Ref* sender);
	static void onStateChange(int state);

private:
	Label* label;
	Label* startLabel;
	Label* stopLabel;
	Label* vcLabel;
	Label* upLabel;
	Menu* startMenu;
	Menu* stoptMenu;
	Menu* vcMenu;
	Menu* upMenu;

	float xOrigin;
	float xMax;
	float xOffset;
	int xStep;

	float yOrigin;
	float yMax;
	float yOffset;
	int yStep;
};

#endif // __HELLOWORLD_SCENE_H__
