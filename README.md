# Surya-Shakti Solar Monitor — Android Studio Setup Guide

## ─── Step 1: Open Project ───────────────────────────────────────
1. Open Android Studio → "Open an Existing Project"
2. Select the `SuryaShakti` folder
3. Wait for Gradle sync to complete (~2–3 minutes first time)

## ─── Step 2: Add Gemini API Key ────────────────────────────────
1. Open `local.properties` in the root of the project
2. Add this line:
   ```
   GEMINI_API_KEY=your_actual_api_key_here
   ```
3. Get your free key at: https://makersuite.google.com/app/apikey

## ─── Step 3: Run the App ────────────────────────────────────────
1. Connect your Android phone (USB Debugging ON)
   OR use an emulator (API 24+)
2. Click the green ▶ Run button in Android Studio
3. App installs and launches automatically

## ─── Step 4: Icons (Required) ──────────────────────────────────
Android needs a launcher icon. Quick fix:
1. Right-click `res` folder → New → Image Asset
2. Choose a sun emoji or upload any icon
3. Click Next → Finish

## ─── App Screens ────────────────────────────────────────────────

| Screen       | What it does                                         |
|--------------|------------------------------------------------------|
| 🏠 Dashboard  | Solar gauge, Green Independence Score, stats, weather |
| 📝 Log        | Enter daily generation + meter readings              |
| 📊 Reports    | 30-day bar chart + total savings summary             |
| 🤖 AI Tips    | Gemini API analyses usage and gives 4 energy tips    |

## ─── Tech Stack ──────────────────────────────────────────────────
- Language:       Kotlin
- Architecture:   MVVM (ViewModel + LiveData)
- Database:       Room DB (SQLite) — fully offline
- Charts:         MPAndroidChart (PieChart gauge + BarChart)
- AI:             Google Gemini API
- Notifications:  WorkManager (daily 11 AM peak sun alert)
- UI:             Material Design 3 — Yellow/Black high-contrast theme

## ─── Project Structure ──────────────────────────────────────────
```
app/src/main/
├── java/com/suryashakti/solarmonitor/
│   ├── MainActivity.kt
│   ├── SuryaShaktiApp.kt
│   ├── data/
│   │   ├── EnergyLog.kt        ← Room Entity
│   │   ├── EnergyLogDao.kt     ← DB queries
│   │   └── AppDatabase.kt      ← Singleton DB
│   ├── ui/
│   │   ├── dashboard/          ← Home screen
│   │   ├── log/                ← Data entry + history
│   │   ├── reports/            ← Charts + savings
│   │   └── tips/               ← Gemini AI tips
│   └── workers/
│       └── PeakSunWorker.kt    ← WorkManager notification
└── res/
    ├── layout/                 ← All XML screens
    ├── navigation/nav_graph.xml
    ├── menu/bottom_nav_menu.xml
    ├── values/colors.xml       ← Theme colors
    └── drawable/               ← Icons + backgrounds
```

## ─── Internship Checklist ────────────────────────────────────────
✅ Android app built with Kotlin
✅ MVVM Architecture
✅ Room DB — offline data persistence
✅ MPAndroidChart — visual gauges and charts
✅ Gemini API — GenAI feature integration
✅ WorkManager — background notifications
✅ Material Design 3 UI
✅ 4 complete screens with navigation
