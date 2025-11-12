# 🌱 Symbiotic City - Android App

This is an Android application wrapper for the [Symbiotic City](https://github.com/Viraj0711/Symbiotic_City.git) website, built using WebView to provide a native Android experience for the web platform.

## 📱 Features

- **Native Android WebView** - Wraps the Symbiotic City website in a native Android app
- **Offline Support** - Caching enabled for better performance
- **Pull to Refresh** - Swipe down to reload the content
- **File Upload Support** - Camera and file picker integration
- **Geolocation** - Location services enabled for map features
- **Download Manager** - Download files directly from the app
- **Back Navigation** - Hardware back button support for in-app navigation
- **Responsive** - Adapts to different screen sizes and orientations

## 🛠️ Tech Stack

- **Kotlin** - Modern Android development language
- **WebView** - For displaying web content
- **AndroidX** - Modern Android components
- **SwipeRefreshLayout** - Pull to refresh functionality
- **Material Components** - Material Design UI components

## 📋 Prerequisites

- **Android Studio** (Arctic Fox or newer)
- **JDK 8 or higher**
- **Android SDK** (API level 24+)
- **Kotlin** plugin

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd SymbioticCityApp
```

### 2. Open in Android Studio

1. Open Android Studio
2. Select **File > Open**
3. Navigate to the `SymbioticCityApp` folder
4. Click **OK**

### 3. Configure the Website URL

Open `MainActivity.kt` and update the `WEBSITE_URL` constant with your deployed website URL:

```kotlin
companion object {
    // Replace this with your deployed website URL
    private const val WEBSITE_URL = "https://your-website-url.com"
}
```

**Important:** Make sure your website is deployed and accessible via HTTPS for production use.

### 4. Update App Configuration (Optional)

You can customize the app by modifying these files:

- **App Name**: `app/src/main/res/values/strings.xml`
- **App Colors**: `app/src/main/res/values/colors.xml`
- **App Theme**: `app/src/main/res/values/themes.xml`
- **Package Name**: Update in `build.gradle` and rename the package folder

### 5. Add App Icon

Replace the default launcher icons in these folders with your custom icon:

- `app/src/main/res/mipmap-mdpi/`
- `app/src/main/res/mipmap-hdpi/`
- `app/src/main/res/mipmap-xhdpi/`
- `app/src/main/res/mipmap-xxhdpi/`
- `app/src/main/res/mipmap-xxxhdpi/`

You can use [Android Asset Studio](https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html) to generate icons.

### 6. Build and Run

1. Connect an Android device or start an emulator
2. Click the **Run** button (▶️) in Android Studio
3. Select your target device
4. Wait for the build to complete

### 7. Generate APK for Distribution

**Debug APK:**

```bash
./gradlew assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

**Release APK:**

```bash
./gradlew assembleRelease
```

Output: `app/build/outputs/apk/release/app-release.apk`

**Generate Signed APK:**

1. Go to **Build > Generate Signed Bundle / APK**
2. Select **APK**
3. Create or select your keystore
4. Fill in the keystore details
5. Choose **release** build variant
6. Click **Finish**

## 📱 Features Implemented

### WebView Configuration

- JavaScript enabled
- DOM storage enabled
- File upload support
- Geolocation support
- Download manager integration
- Cache enabled for offline support

### Permissions

- **Internet** - Required for loading web content
- **Network State** - Check connectivity
- **Camera** - For photo uploads
- **Location** - For location-based features
- **Storage** - For file downloads/uploads

### User Experience

- Swipe to refresh
- Custom error handling
- Loading progress indication
- External link handling
- Back button navigation

## 🔧 Troubleshooting

### App not loading the website

- Check your internet connection
- Verify the `WEBSITE_URL` is correct and accessible
- Ensure the website supports HTTPS

### File upload not working

- Grant storage permissions when prompted
- Check if camera permission is granted for camera uploads

### Location not working

- Enable location permissions in app settings
- Ensure location services are enabled on the device

### SSL Certificate errors

- For development, you can use HTTP (already configured)
- For production, ensure your website has a valid SSL certificate

## 📦 Project Structure

SymbioticCityApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/symbioticapp/city/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   ├── xml/
│   │   │   │   │   ├── file_paths.xml
│   │   │   │   │   └── network_security_config.xml
│   │   │   │   └── mipmap-*/
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md

## 🎨 Customization

### Change App Theme

Edit `app/src/main/res/values/themes.xml`:

```xml
<item name="colorPrimary">#YourColor</item>
<item name="colorPrimaryVariant">#YourDarkColor</item>
```

### Modify WebView Settings

Edit `MainActivity.kt` in the `setupWebView()` method to customize:

- JavaScript settings
- Cache policy
- User agent
- Zoom controls
- And more...

### Add Custom JavaScript Interface

You can inject JavaScript to communicate between the web app and native Android:

```kotlin
webView.addJavascriptInterface(WebAppInterface(this), "Android")
```

## 📄 License

This project is part of the Symbiotic City ecosystem. See the main repository for license details.

## 👨‍💻 Developer

### Viraj Jadav

- GitHub: [@Viraj0711](https://github.com/Viraj0711)
- Original Web App: [Symbiotic City](https://github.com/Viraj0711/Symbiotic_City.git)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📱 Testing

Before deploying:

1. Test on multiple Android versions (API 24+)
2. Test on different screen sizes
3. Test all permissions (camera, location, storage)
4. Test file uploads and downloads
5. Test back navigation
6. Test offline behavior
7. Test pull to refresh

## 🚀 Deployment

### Google Play Store

1. Generate a signed release APK
2. Create a Google Play Developer account
3. Create a new app listing
4. Upload the APK
5. Fill in store listing details
6. Submit for review

### Alternative Distribution

- **Direct APK** - Share the APK file directly
- **Third-party stores** - Upload to Amazon Appstore, Samsung Galaxy Store, etc.
- **Enterprise Distribution** - Use MDM solutions for internal distribution

## 📞 Support

For issues related to:

- **Android App**: Open an issue in this repository
- **Website/Backend**: Visit the [main repository](https://github.com/Viraj0711/Symbiotic_City.git)

---

Built with ❤️ for sustainable communities
