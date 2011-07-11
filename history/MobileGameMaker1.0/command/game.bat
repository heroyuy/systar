@echo off
cd product
jar cfm Game.jar MANIFEST.MF audio/*.* engine/*.* META-INF/*.* image/*.* data/*.* control/*.* game/*.* model/*.* system/*.* view/*.*
exit