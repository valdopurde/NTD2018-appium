Mac Android Setup Guide
* Setup Mac - Xcode, Homebrew, Git
Guide: https://www.moncefbelyamani.com/how-to-install-xcode-homebrew-git-rvm-ruby-on-mac/
xcode-select --install
install command line tool
Install brew:
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew doctor
brew update
brew install git

Install Node.js and npm with Homebrew
https://changelog.com/posts/install-node-js-with-homebrew-on-os-x
brew install node

Install Java
brew update
brew tap caskroom/versions
brew cask install java8

Install text editor (example: atom)
brew cask install atom

Set JAVA_HOME
atom .profile
To the .profile file add:
export JAVA_HOME=/Library/Java/Home;

Check by typing:
$JAVA_HOME/bin/java -version

Download & Install Android Studio
Install all tools & SDK's recommended by the Studio.
Open Android Studio SDK manager
Copy Android SDK location (for setting the ANDROID_HOME)

Set ANDROID_HOME
atom .profile
To the ".profile" file add:
export ANDROID_HOME=Android SDK location you got from Android studio SKD Manager tab (Smthing like this "/Users/name/Library/Android/sdk")

Start a new Android Studio project
Tools - AVD Manager
Create a virtual device

Install Maven
Download from face




Clone git Repository containing test


NOTES
Uninstall Unlock app?
