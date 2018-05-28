# Mac Android Testing setup

* Install Command line tools, Homebrew, Git
* Install npm + nodejs + appium
* Install Java and set JAVA_HOME
* Install Android Studios, set ANDROID_HOME and create virtual device (API 23)
* Install Maven
* Clone Test repository https://github.com/valdopurde/NTD2018-appium
* Launch appium 1.7
* Launch test
```
PLATFORM_NAME="android" APPIUM_VERSION="1.7.2" NAME="MyReaction" PLATFORM_VERSION="4.4" DEVICE="Android Emulator" APP="/Developing/Android/SignedApp/MyReaction.apk" mvn clean install
```

## Detailed guide:

### Install command line tool
```
xcode-select --install
```
### Install brew:
```
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
### Install Git
```
brew doctor
brew update
brew install git
```
### Install Node.js and npm with Homebrew
help: https://changelog.com/posts/install-node-js-with-homebrew-on-os-x
```
brew install node
```
### Install Java
```
brew update
brew tap caskroom/versions
brew cask install java8
```
### Install Appium
```
npm install -g appium@1.7.2
```
### Install text editor (example: atom)
```
brew cask install atom
```
### Set JAVA_HOME
atom .profile
To the .profile file add:
export JAVA_HOME=/Library/Java/Home;

Check by typing:
```
$JAVA_HOME/bin/java -version
```
### Install Android Studio
Install all tools & SDK's recommended by the Studio.
Open Android Studio SDK manager
Copy Android SDK location (for setting the ANDROID_HOME)

### Set ANDROID_HOME
```
atom .profile
```
To the ".profile" file add:
* export ANDROID_HOME=Android SDK location you got from Android studio SKD Manager tab (Smthing like this     "/Users/name/Library/Android/sdk")

### Create Virtual Android device
Tools - AVD Manager
Create a virtual device API level 23 or lower

### Install Maven
Download: http://www-eu.apache.org/dist/maven/maven-3/
```
atom .profile
```
To the ".profile" file add:
* export PATH=/opt/apache-maven-3.5.3/bin:$PATH;

### Clone Test repository https://github.com/valdopurde/NTD2018-appium
```
git clone https://github.com/valdopurde/NTD2018-appium
```
Time to check how it all works
* Launch appium in Terminal #1
* Launch test in Terminal #2

### Launch command:
```
PLATFORM_NAME="android" APPIUM_VERSION="1.7.2" NAME="MyReaction" PLATFORM_VERSION="4.4" DEVICE="Android Emulator" APP="/Developing/Android/SignedApp/MyReaction.apk" mvn clean install
```

### Error debubging:
Lock/Unlock issue:
* Uninstall Unlock app could work.
