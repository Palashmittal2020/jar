# Jar Assignment

readme_text = """# Dev_Assignment — Onboarding Animation (Android, Compose)

Implements the onboarding animation per **“Dev_Assignment Onboarding animation.fig”**, powered by the JSON at:  
`https://myjar.app/_assets/shared/education-metadata.json`

Matches the prototype’s choreography using **Jetpack Compose animations** and a clean, testable architecture.

---

## Features

- Onboarding screen with **Compose** animations (fade/slide).
- **Ktor** networking module that fetches and parses remote education metadata.
- **Koin** DI for modules (networking, repository, ViewModel).
- **Clean Architecture** layers (data/domain/presentation).
- Image loading via **Glide** (Compose interop).
- Robust states: loading, error (retry), and empty data handling.
- `Application` class wires Koin & app-level setup.

---

## Tech Stack

- **Language**: Kotlin (JDK 17)
- **UI**: Jetpack Compose
- **Animations**: `animateContentSize`, `AnimatedVisibility`, `animateAsState`, `tween`, `animateFloatAsState`
- **Images**: Glide
- **Networking**: Ktor Client (+ JSON serialization)
- **DI**: Koin
- **Arch**: Clean Architecture (Domain ↔ Data ↔ Presentation with MVVM/MVI-style state)
- **Async**: Coroutines + Flow

---