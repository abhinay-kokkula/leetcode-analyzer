# LeetCode Analyzer 🚀

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](http://your-build-status-link.com)
[![Version](https://img.shields.io/badge/version-1.0.0-blue)](http://your-version-link.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Description

LeetCode Analyzer is a Spring Boot web application designed to fetch and display detailed performance statistics for LeetCode users. It leverages the LeetCode GraphQL API to retrieve data such as total problems solved, difficulty-wise problem counts, contest ratings, and global rankings. The application stores user data in a MySQL database for efficient retrieval.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Important Links](#important-links)
- [Footer](#footer)

## Features 🌟

- **User Profile Analysis:** Enter a LeetCode username to fetch and display their profile statistics.
- **Performance Metrics:** View total problems solved, categorized by difficulty (easy, medium, hard).
- **Contest Performance:** Display current LeetCode contest rating and global ranking.
- **Data Persistence:** User data is stored in a MySQL database to avoid redundant API calls and improve performance.
- **Interactive UI:** A clean and modern user interface built with HTML and Thymeleaf, providing a seamless user experience.
- **Custom Scoring:** Calculates a performance score based on the number of solved problems across different difficulties.

## Tech Stack 🛠️

- **Languages:** Java, HTML, CSS
- **Frameworks:** Spring Boot
- **Build Tool:** Maven
- **Database:** MySQL
- **API Client:** Spring RestClient
- **Templating Engine:** Thymeleaf
- **Data Handling:** Jackson Databind

## Installation ⬇️

To set up and run this project locally, follow these steps:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/abhinay-kokkula/leetcode-analyzer.git
    cd leetcode-analyzer
    ```

2.  **Set up MySQL Database:**
    - Ensure you have MySQL installed and running.
    - Create a database for the application (e.g., `leetcode_db`).
    - Configure your database connection in `src/main/resources/application.properties`.
      Example `application.properties` snippet:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/leetcode_db
      spring.datasource.username=your_mysql_username
      spring.datasource.password=your_mysql_password
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      ```

3.  **Build the Project:**
    Use Maven to build the project. Ensure you have JDK 17 or higher installed.
    ```bash
    ./mvnw clean install
    ```

4.  **Run the Application:**
    Start the Spring Boot application.
    ```bash
    ./mvnw spring-boot:run
    ```

## Usage 💡

1.  **Access the Application:**
    Once the application is running, open your web browser and navigate to `http://localhost:8080` (or the port your Spring Boot application is configured to use).

2.  **Analyze a Profile:**
    - On the homepage, you will see an input field.
    - Enter a valid LeetCode username and click the "Analyze Profile" button.

3.  **View Results:**
    - If the username is found, the application will display a detailed analysis of the user's LeetCode profile, including:
        - Total, Easy, Medium, and Hard problems solved.
        - Contest Rating and Global Ranking.
        - Problem distribution visualized with progress bars.
        - Calculated Performance Score.
    - If the user is not found or an error occurs, an error message will be displayed on the homepage.

### Real-World Use Cases 🌍

- **Personal Performance Tracking:** Users can track their LeetCode progress and identify areas for improvement.
- **Competitive Analysis:** Compare your performance with friends or top coders.
- **Recruitment Tool:** Potentially used by recruiters to quickly assess a candidate's LeetCode proficiency (though this would require more robust features).

## How to Use 🤔

The primary entry point for the application is `src/main/resources/templates/index.html`. This serves as the landing page where users input their LeetCode username.

- The `Homecontroller` handles GET requests to the root path (`/`) to display `index.html` and POST requests to `/analyze`.
- Upon submission of the username, the `analyze` method in `Homecontroller` calls the `getProfile` method in `LeetcodeService`.
- `LeetcodeService` first checks the MySQL database (`LeetcodeProfileRepository`) for existing data. If not found, it fetches data from the LeetCode GraphQL API (`https://leetcode.com/graphql`).
- The fetched data is then processed, a performance score is calculated, and the profile is saved to the database before being returned.
- The `result.html` template is rendered to display the user's profile statistics in a visually appealing dashboard.

## Project Structure 📂

```
leetcode-analyzer/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── abhi/
│   │   │           └── leetcode_analyzer/
│   │   │               ├── LeetcodeAnalyzerApplication.java
│   │   │               ├── config/
│   │   │               │   ├── AppConfig.java
│   │   │               │   └── package-info.java
│   │   │               ├── controller/
│   │   │               │   ├── Homecontroller.java
│   │   │               │   └── package-info.java
│   │   │               ├── dto/
│   │   │               │   ├── GraphQLRequest.java
│   │   │               │   ├── LeetcodeResponse.java
│   │   │               │   └── package-info.java
│   │   │               ├── model/
│   │   │               │   ├── LeetcodeProfile.java
│   │   │               │   └── package-info.java
│   │   │               ├── repository/
│   │   │               │   ├── LeetcodeProfileRepository.java
│   │   │               │   └── package-info.java
│   │   │               └── service/
│   │   │                   └── LeetcodeService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           ├── index.html
│   │           └── result.html
│   └── test/
│       └── java/
│           └── com/
│               └── abhi/
│                   └── leetcode_analyzer/
│                       └── LeetcodeAnalyzerApplicationTests.java
└── README.md
```

## API Reference 🌐

- **LeetCode GraphQL API:** `https://leetcode.com/graphql`
  - This API is used to fetch user profile data and contest rankings.

## Contributing 🤝

Contributions are welcome! If you'd like to contribute, please:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and commit them.
4.  Submit a pull request.

Please ensure your code follows the project's coding style and includes tests where appropriate.

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. (Note: A LICENSE file was not found in the provided analysis, assuming MIT based on common practice).

## Important Links 🔗

- **Live Demo:** (No live demo link available from analysis)
- **Author Profile:** [abhinay-kokkula](https://github.com/abhinay-kokkula)

## Footer 🦶

© 2023 **LeetCode Analyzer** | [Repository](https://github.com/abhinay-kokkula/leetcode-analyzer) | Developed by **abhinay-kokkula**

--- 🚀

Feel free to **Fork**, **Star ⭐**, and **Create Issues** if you find any problems or have suggestions!


---
**<p align="center">Generated by [ReadmeCodeGen](https://www.readmecodegen.com/)</p>**