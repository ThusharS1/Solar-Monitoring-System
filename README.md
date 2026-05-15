# Surya-Shakti Solar Monitor — Android Studio Setup Guide

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
