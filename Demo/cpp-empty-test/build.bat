@echo off
cocos compile -p android --no-apk -j 8 -s %~dp0 --ndk-mode release