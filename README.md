# Dynamic Island Android

A Compose-based Android Dynamic Island overlay app.

## Features
- Overlay pill/card at top center
- Notification listener support
- Foreground overlay service
- Settings screen with DataStore
- Event bus architecture
- Timer and charging placeholders
- Media-session ready structure

## Permissions
Grant these in Android settings:
- Draw over other apps
- Notification access

## Build
1. Open in Android Studio.
2. Sync Gradle.
3. Run on a device/emulator with Android 8.0+.

## Notes
- This is a solid starter implementation.
- Media, call, Bluetooth, and full event routing can be expanded from this structure.
- Android permissions and background limits are respected; some features depend on OS/device support [web:30][web:31][web:34].
