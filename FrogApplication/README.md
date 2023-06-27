# Coding Challenge

**Submission criteria**: A zip of the project which can be built and run.

# Brief
Language: kotlin
**To build the code for the given tasks, follow these steps**

1)Set up the project, and then Consider the project using Architecture such as MVVM, Design pattern such as Repository
2)Set up the necessary dependencies in the project's build.gradle
3) Design the user Interface:
   [SignupActivity]: Entering the login interface first guides the user to register, and the registration is successful and directly enters the movie list interface. The registration information is inserted into the Room database and saved.
   [LoginActivity]: First query whether the database is registered, and then keep the login status to SharedPreferences after successful login.
   [BiometricActivity]:fingerprint authentication
   [MoveActivity]:fetch movie list
4）##Implement the Network request:##
   The code leverages Retrofit, OkHttp, Executors, Moshi, and KotlinJsonAdapterFactory libraries to create a network service interface (FrogAPIService) for making API calls to the OMDb API.
   In the code, Retrofit is used to create an instance of the FrogAPIService interface and define the base URL for API requests.
   The code creates an instance of OkHttpClient to be used by Retrofit for making network requests.
   ## Repository Pattern ##
   UserRepository and MovieRepository are classes that encapsulate network request API and database processing calls.
   Implement a JSON parser to parse the API response and map it to the data model.
4) ViewModel:
   AuthViewModel  and MovieViewModel is a class that serves as a mediator between the UI and the Repository. The class takes advantage of Kotlin Coroutines for performing asynchronous operations in a structured and lifecycle-aware manner.
6)Update the UI:
Handle error scenarios:
7)Build and run the application:
Build the application using Android Studio.
Run the application a physical Android device(Realme-Android version13).


## Used Technologies ##

- Android MVVM Clean code architecture
- [SharedPreferences]
- [Room DataBase]
- [DataBinding]
- [Retrofit]
- [Compose Navigation]
- [ViewModel] Composable state management.
- [Coroutines]

## question ##
- 1.OMDb API requests according to the required parameters, there is always dirty data, I think it is an API problem. In this project I manually filter.
- 2.Many details have not yet been dealt with, such as the verification of mailboxes
- 3.Some classes are not very well encapsulated, for example,UserRepository and MovieRepository 
-4. Biometric authentication: I have not tested it well, especially on different devices