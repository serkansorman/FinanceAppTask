# CCFinanceGroupTask

# Description
1. Simple App that shows stocks and details.
2. Yahoo Finance API https://english.api.rakuten.net/apidojo/api/yh-finance used for the data list (market/v2/get-summary). Every 8 seconds, this screen updated by refresh data from the API.
3. On the top of the list screen, the search bar lets the user filter out stocks by the details.
4. When user press on the list item, user can get detailed info via (stock/v2/get-summary) and shows it.
5. Since there is an HTTP library restriction in the project requirements, OkHttp is used by implementing own network model mapping instead of Retrofit library.
</br>

<p><img height= "650"  width="300" src="https://user-images.githubusercontent.com/23194718/174276424-a1ea269d-7783-45e8-80c2-a809d7eb8885.gif" alt="SocialMediaAppGif" />

# Unit Test Results

 <img width="650" alt="Screen Shot 2022-11-28 at 03 23 50" src="https://user-images.githubusercontent.com/23194718/204168106-24417100-487a-4ed9-b2bc-89961f2c1139.png">

 ## Tech Stack

- **[MVVM](https://developer.android.com/jetpack/guide">MVVM)** 
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**
- **[Architecture Components](https://developer.android.com/topic/libraries/architecture/)**
- **[Koin](https://insert-koin.io/)**
- **[OkHttp](https://square.github.io/okhttp/)**
- **[Navigation Component](https://developer.android.com/jetpack/androidx/releases/navigation)** 
- **[Viewbinding](https://developer.android.com/topic/libraries/view-binding/)**
- **[Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)**
- **[Kotlin Flows](https://developer.android.com/kotlin/flow)**
- **[JUnit4](https://junit.org/junit4)**
- **[MockK](https://github.com/mockk)** 
