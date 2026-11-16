# AutoHub

Я пишу Android-приложение для своего гаража: в нём можно хранить автомобили, а дальше я хочу добавить учёт заправок, обслуживания и расходов. Код я писал сам, без ИИ. На этом проекте я разбирался с Compose, локальной базой и тем, как разделить интерфейс, состояние и работу с данными.

Сейчас в приложении можно добавлять, редактировать и удалять автомобили, отмечать избранные и открывать карточки с их данными. Я добавил светлую и тёмную темы, русский и английский языки. При первом запуске пустая база заполняется демонстрационными автомобилями.

Для расходов, заправок и обслуживания я уже подготовил сущности, DAO и репозитории. Полных экранов для этих сценариев пока нет, эту часть я ещё не закончил.

## Как устроен код

Я использую Kotlin, Jetpack Compose, Material 3, Navigation Compose, ViewModel, Coroutines/Flow, Room, KSP, Hilt и Preferences DataStore. Версии лежат в [каталоге Gradle](gradle/libs.versions.toml).

Экраны получают состояние из ViewModel, ViewModel обращаются к репозиториям, а репозитории работают с Room DAO. Зависимости базы и настроек передаю через Hilt. Модели для интерфейса отделены от сущностей базы.

- [MyGarageApp.kt](app/src/main/java/com/dima/mygarage/ui/MyGarageApp.kt): главный экран и навигация.
- [ui/garage](app/src/main/java/com/dima/mygarage/ui/garage): гараж, его состояние и формы автомобилей.
- [data/repository](app/src/main/java/com/dima/mygarage/data/repository): работа с данными.
- [data/local](app/src/main/java/com/dima/mygarage/data/local): Room, сущности, DAO и конвертеры.
- [data/di](app/src/main/java/com/dima/mygarage/data/di) и [data/settings](app/src/main/java/com/dima/mygarage/data/settings): зависимости и настройки приложения.

## Запуск

Открой проект в Android Studio с поддержкой Android Gradle Plugin 9.2, установи Android SDK 36.1 и выполни Gradle Sync. В [настройках Gradle](gradle/gradle-daemon-jvm.properties) указан JDK 21. Приложение запускается на Android 7.0 / API 24 и новее.

Debug APK можно собрать из корня проекта:

```bash
./gradlew :app:assembleDebug
```

В Windows используй `gradlew.bat`. APK появится в `app/build/outputs/apk/debug/app-debug.apk`.

Это ещё учебный проект в разработке. Серверную синхронизацию я не добавлял. Тесты пока остались стартовыми примерами из Android-шаблона, сценарии работы с гаражом ими не покрыты.
