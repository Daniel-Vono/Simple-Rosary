# Simple Rosary
This is a simple Rosary app to help you pray the Rosary and other chaplets.

The following prayers are available:
 - the Joyful Mysteries
 - the Sorrowful Mysteries
 - the Glorious Mysteries
 - the Luminous Mysteries
 - the Divine Mercy Chaplet

Additionally, if you do not like the style of the provided prayers or would like to pray a different prayer you can have no accompanying text.

This app also supports praying both counter clockwise and clockwise.

**Notice:**
The app user interface gets squished on devices with small screen sizes and may not work properly.

## Building
This app was built in Android Studio on Linux.

Steps:
 1. Clone/download the project and open it in Android Studio
 2. In the root of the project create a file named `local.properties`
 3. In the file type `sdk.dir=/directory/to/Android/Sdk` (*the path to your android SDK installation*) and save the file
 4. In Andoird Studio, make a new configuration with the module `SimpleRosary.app.main` and deploy it as a default APK
 5. Save the configuration and build/run the project
