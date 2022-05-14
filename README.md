# Android-Game-Engine-Example-2

This app demonstrates using Androids canvas to create a basic game loop.

#### What the app does:
- Shows images of Mud Kip heads bouncing up and down

### Warnings
This app has a few notable flaws
- This project does not follow any SOLID principles or Design patterns. Traverse at your own risk!
- **This project does not use a hardware accelerated canvas!** This WILL cause performance problems when scaling up the number of transparent images (png) displayed on the screen. Instead, look at the [SuprSeed](https://github.com/red-dragon65/SuprSeed) projects `MainView` class to see how to get a hardware accelerated canvas from a `SurfaceView`.
