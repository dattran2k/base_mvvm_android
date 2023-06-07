- No modular, use for 'Not HUGE' project
- Java 18
- Navigation Component
- Coroutines - flow
- Retrofit2 
- Hilt & Dagger
- MVVM
- Debugging : Chucker, Timber
- Data Store

- git flow
  -https://danielkummer.github.io/git-flow-cheatsheet/index.html
  
 ## Explanation some case
 ### Why ViewModel state  difference than this project from Google : https://github.com/dattran2k/nowinandroid/tree/main/feature
 * My explain for "NowInAndroid" project here : https://github.com/dattran2k/now_in_android_flow_explain
 * Data  of "now in android" project is listen from local (Room, DataStore) using Flow
 * check https://developer.android.com/topic/libraries/architecture/datastore?hl=vi
 * and https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow?hl=vi#0
 * that's mean if database data changed, flow will emit new data, and ViewModel state ( homeUiState ) will able to collect that
 * so how can that update new data ?
 * 1. UI action or not, it can be sync new data when open app ->
 * 2. Request API for new data ->
 * 3. New data will be save in Room or DataStore ->
 * 4. Flow Room or DataStore will emit new value ->
 * 5. Flow in ViewModel that collect from that flow will map and produce new UI state .
 * Check out : https://github.com/dattran2k/nowinandroid/blob/main/feature/foryou/src/main/java/com/google/samples/apps/nowinandroid/feature/foryou/ForYouViewModel.kt

 
