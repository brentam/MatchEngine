# Coding Match Service

## Overview

This project is a Kotlin-based web service designed to match workers with suitable job opportunities. It provides a REST API that takes worker_id information and returns a list of the most relevant jobs



## Prerequisites

* **JDK 21:** Required for running the Kotlin application.
* **Gradle:** Used for build automation and dependency management.
* **IntelliJ IDEA (Recommended):** Provides a convenient development environment with Gradle integration.

## Setup

1.  **Clone the repository:**

    ```sh
    git clone [https://github.com/brentam/MatchEngine.git](https://github.com/brentam/MatchEngine.git)
    cd coding-match-service
    ```

2.  **Open the project in IntelliJ IDEA:**

    * Open IntelliJ IDEA.
    * Select `File > Open...` and choose the project directory.
    * IntelliJ IDEA will automatically detect the Gradle project and import it.

3.  **Build the project:**

    ```sh
    ./gradlew build
    ```

4.  **Run the application:**

    ```sh
    ./gradlew bootRun
    ```

5.  **Run the tests:**

    ```sh
    ./gradlew test
    ```

## Call the rest API
```sh
curl -X GET "http://localhost:8080/matches/8" -H "accept: application/json"
```

## Configuration

The application configuration is located in `src/main/resources/application.yaml`. You can modify the following settings: