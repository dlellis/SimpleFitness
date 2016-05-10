# SimpleFitness

#Install Instructions
1. You probably need a device that is running Marshmallow. An emulator can be created if you do now have one.

1. Download Android Studio for your respective platform (Linux, Windows, Mac)
http://developer.android.com/sdk/index.html

1. Install Android Studio<br>
http://developer.android.com/sdk/installing/index.html

1. Download the project from github<br>
#####On Linux and Mac:<br>
`git clone https://github.com/dlellis/SimpleFitness`<br>
#####Windows:<br>
Download zip from https://github.com/dlellis/SimpleFitness<br>

1. Start Android Studio<br>
#####On Linux command line:
`studio.sh`

  #####On Windows and Mac:
Simply navigate to the application

1. When prompted, select "import existing project"

1. Navigate to the directory that you cloned or downloaded the repo to.

1. On the mobile phone, ensure developer options are enabled.
(Lookup how to do this for your specific phone)

1. Make sure to enable the following developer options:<br>
  2. Stay Awake
  2. Enable Bluetooth HCI snoop log
  2. USB debugging

1. In Android Studio, Install SDK version 23 using SDK manager. Include build tools 23.0.3. <br>
`Tools -> Android -> SDK Manager`
1. Plug in your phone via usb and make sure it is recognized by AVD manager.<br>
`Tools -> Android -> AVD Manager`

1. At the top of android studio, select mobile next to the green arrow.
1. Click the green arrow to run. 
(You must have the device plugged in and recognized by android device manager or set up an emulator)
(You must also have the proper SDK libraries installed set in build.gradle. The SDK manager can help with this. Android Studio should error and tell you what you need.)

1. The app should open on the phone if the phone is unlocked.

1. For the wear device, you may run it from an emulator or device in the same way by selecting wear instead of mobile. The mobile and wear apps may not interact well unless you connect the phone and the watch already. Info on how to do that may be found here:
http://developer.android.com/training/wearables/apps/creating.html
http://developer.android.com/training/wearables/apps/bt-debugging.html


