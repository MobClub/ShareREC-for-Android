@echo off

copy cpp-empty-test\proj.android\bin\*.apk .
rd /q /s cpp-empty-test\proj.android\gen
rd /q /s cpp-empty-test\proj.android\bin

copy EraserDemo\bin\*.apk .
rd /q /s EraserDemo\gen
rd /q /s EraserDemo\bin

copy GLESDemo\bin\*.apk .
rd /q /s GLESDemo\gen
rd /q /s GLESDemo\bin

copy my-gdx-game\bin\*.apk .
rd /q /s my-gdx-game\gen
rd /q /s my-gdx-game\bin

copy SystemRecorderDemo\bin\*.apk .
rd /q /s SystemRecorderDemo\gen
rd /q /s SystemRecorderDemo\bin