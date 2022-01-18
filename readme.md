###### Introduction

This is code challenge. 
See requirements in  Android Coding Challenge.pdf

### Hint for testers

1. Database is cleared each time app is re-started
2. In order to query Pixabay API, please, add your personal key into :feature-images build.gradle file
```groovy
buildConfigField("String", "TOKEN", "\"<token goes here>\"")
```

### Implementation description

###### Used libraries
* Jetpack libraries (viewModel, fragment-ktx, constraintLayout, recyclerView navigation)
* RxJava 3 + RxAndroid + RxKotlin
* Retrofit 2 + OkHttp + Moshi
* Room
* Hilt 
* JUnit4 + Mockk
###### Used solutions
* application is modularized feature-wise.
* common module created to keep reusable code.
* maven dependency management done with the help of [gradle platform module](https://docs.gradle.org/current/userguide/java_platform_plugin.html)
* MVVM + data-binding
* dependency injection
* clean architecture (domain/data/ui separation within feature module)
   - each layer has its own model which are converted subsequently: dto -> dbo -> domain -> view model
      - dto reflects all data that is provided by API. Uses its own set of Annotations (Moshi)
      - dbo reflects the way it is stored in db. Uses its own set of Annotations (Room).  
            Might also have different data types and simplified data structure. Is a subset of dto data
      - domain model reflects the subset of app's data necessary for particular business logic step
      - view model reflects how domain data is presented
   - it might to seem like over engineering, but only on the short run. Just imagine all above requirements merged into one class...
* database as single source of data:
   - UI reactively observes Db state
   - if Db lacks data the remote data is requested (see repository implementation)
* ActivityDelegate used to propagate messages, navigation and loading events 
  triggered in view models to where they can be consumed - Activity. ActivityDelegate is safe
  as it does not keep reference to any context and serves only as a relay
###### Not done but "must have" in real app
* proper exception handling
   - e.g. mapping API exceptions to domain exceptions with meaningful for the user messages
* pagination for images list
