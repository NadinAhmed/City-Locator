# City Locator

## Overview

City Locator is a simple Android application developed as part of the application process for the [Kilvvr](https://www.klivvr.com/) internship. The app demonstrates my ability to implement Clean Architecture principles, even in a small-scale project. The primary functionality of the app is to load and display a list of cities, allow the user to search for a specific city by name, and perform the search efficiently using the binary search algorithm.

## Demo Video

<p align="center">
  <a href="https://youtu.be/DwdZXjCLCT8">
    <img src="https://github.com/NadinAhmed/City-Locator/blob/master/city%20locator%20screen.png">
  </a>
</p>

## Features

- **Load City Data**: The app loads a list of city data from a local JSON file.
- **Data Parsing**: The JSON data is parsed into model objects using the Gson library. Each city object includes the following attributes:
  - `name`: The name of the city.
  - `country`: The country in which the city is located.
  - `_id`: A unique identifier for the city.
  - `coord`: The latitude and longitude of the city.
- **Display City List**: The parsed list of cities is displayed to the user in a scrollable list.
- **Search Functionality**: Users can search for a city by name. The search is performed using the binary search algorithm for faster lookup.
- **Open City Location in Maps**: Users can click on any city and they will be redirected to google maps where they can see its location.

## Project Structure

This project follows the Clean Architecture pattern, organizing the code into layers to ensure separation of concerns and maintainability.

### Layers

1. **Domain Layer**:
   - Contains the core business logic and models.
   - Defines interfaces for the repository.

2. **Data Layer**:
   - Responsible for data handling, including parsing JSON data and managing the repository.
   - Utilizes Gson to parse the JSON file into city model objects.

3. **Presentation Layer**:
   - Includes UI components and view models.
   - Manages user interactions and updates the UI accordingly.

### Key Components

- **City**: The data model representing a city with attributes such as name, country, id, and coordinates.
- **CityRepository**: Interface and implementation for accessing city data.
- **CityViewModel**: Manages UI-related data and handles search functionality using binary search.
- **MainActivity**: The entry point of the app, responsible for displaying the list of cities and handling user input.

## Technologies Used

- **Gson**: For parsing JSON data into Kotlin data classes.
- **Jetpack Compose**: To create the UI of the application.
- **ViewModel**: To manage UI-related data in a lifecycle-conscious way.
- **Clean Architecture**: To organize the project structure and ensure separation of concerns.
- **Dagger-Hilt**: For dependency injection.

## Contact
For any inquiries or feedback, please contact (nadinahmed316@gmail.com).

Happy coding! 🚀
