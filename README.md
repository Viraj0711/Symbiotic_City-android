# Symbiotic City - Android App

Android application and dedicated backend for Symbiotic City sustainability platform.

## 📱 Project Structure

```text
SymbioticApp/
├── android-backend/     # Node.js/Express backend (port 3002)
├── SymbioticCityApp/    # Android app (Kotlin, MVVM)
└── .gitignore
```

## 🚀 Quick Start

### Backend Setup

1. Navigate to backend directory:

cd android-backend

1. Install dependencies:

```bash
npm install
```

1. Create `.env` file (already configured):

```env
PORT=3002
DATABASE_URL=postgresql://postgres:viraj7930@db.nzobbewbakkeydvzhggb.supabase.co:5432/postgres
JWT_SECRET=9Aj1Txenum7CuFz2Eum+lajzqRfcuQSOtboczT+XeW9SuYdkRHJOIRQI68XyKGvUHm/ZObaMjsgWrZaJQBPphg==
NODE_ENV=development
```

1. Start the server:

```bash
node server.js
```

Or double-click `START.bat` on Windows.

### Android App Setup

1. Open `SymbioticCityApp` in Android Studio

1. Update API endpoint in `RetrofitClient.kt` if needed:
   - Emulator: `http://10.0.2.2:3002/api/`
   - Physical device: `http://YOUR_IP:3002/api/`

1. Build and run the app

## 🎨 Features

- **Authentication**: Login/Register with JWT
- **Projects**: Browse and view sustainability projects
- **Events**: Discover community events
- **Marketplace**: Green products and services
- **Profile**: User management and settings

## 🔑 Test Credentials

Email: <test@symbioticapp.com>
Password: test123

## 🛠️ Tech Stack

**Android:**

- Kotlin
- MVVM Architecture
- Retrofit 2.9.0 + OkHttp
- Material Design 3
- DataStore Preferences
- LiveData & ViewModel

**Backend:**

- Node.js + Express
- PostgreSQL (Supabase)
- JWT Authentication
- bcryptjs

## 📦 Build APK

```bash
cd SymbioticCityApp
./gradlew assembleDebug
```

APK location: `app/build/outputs/apk/debug/app-debug.apk`

## 🎨 Design

The app follows the website's mobile-responsive design with:

- Background: `#E2EAD6` (sage green)
- Cards: `#B3C893` with 16dp radius
- Primary: `#4EA685` (teal-green)
- Modern Material Design 3 components

## 📄 License

See LICENSE file.
