# Algorithm Visualization Dashboard

## Overview
A professional Java-based web dashboard application for visualizing searching and sorting algorithms with step-by-step iteration highlighting. Built with Spring Boot backend and modern HTML/CSS/JavaScript frontend.

## Recent Changes (November 6, 2025)
- Initial project setup with Spring Boot 3.1.5
- Implemented searching algorithms: Linear Search and Binary Search
- Implemented sorting algorithms: Bubble Sort, Selection Sort, Insertion Sort, Merge Sort, and Quick Sort
- Created professional dashboard UI with modern gradients, shadows, and responsive design
- Added color-coded visualization with different colors for array states
- Implemented CSV file upload support
- Added complexity analysis display (time/space) and performance recommendations
- Configured workflow to run on port 5000

## Project Architecture

### Backend (Java/Spring Boot)
- **AlgorithmVisualizerApplication.java**: Main Spring Boot application
- **Model Classes**:
  - `AlgorithmStep`: Represents each iteration with array state, action, and highlighting information
  - `AlgorithmResult`: Contains all steps, complexity info, and execution details
- **Service Classes**:
  - `SearchingService`: Implements Linear Search and Binary Search with iteration tracking
  - `SortingService`: Implements all sorting algorithms with detailed step tracking
- **Controller Classes**:
  - `AlgorithmController`: REST API endpoints for algorithm execution
  - `WebController`: Serves the main HTML page

### Frontend (HTML/CSS/JavaScript)
- **index.html**: Main dashboard page with navigation and sections
- **styles.css**: Professional styling with gradients, animations, and color schemes
- **app.js**: Handles API calls, visualization rendering, and user interactions

### Color Coding System
- **Gold/Yellow**: Current position/pointer
- **Red**: Elements being compared
- **Green**: Sorted elements or found target
- **Blue**: Default/unsorted elements
- **Orange**: Selected/pivot elements (for Quick Sort)

## Features
1. **Searching Algorithms**:
   - Linear Search: O(n) complexity, sequential checking
   - Binary Search: O(log n) complexity, divide-and-conquer approach

2. **Sorting Algorithms**:
   - Bubble Sort: O(n²) - Simple comparison-based sorting
   - Selection Sort: O(n²) - Finds minimum and places it
   - Insertion Sort: O(n²) - Builds sorted array incrementally
   - Merge Sort: O(n log n) - Divide-and-conquer with merging
   - Quick Sort: O(n log n) average - Partition-based sorting

3. **Visualization Features**:
   - Step-by-step iteration display
   - Color-coded array elements showing algorithm state
   - Progress bar and iteration counter
   - Previous/Next navigation through steps
   - Action descriptions for each step

4. **Analysis Features**:
   - Time complexity (best, average, worst case)
   - Space complexity
   - Execution time measurement
   - Algorithm recommendations based on input size
   - Detailed descriptions of algorithm behavior

5. **Input Options**:
   - Manual array input via text field
   - CSV file upload support
   - Pre-filled example data

## Technology Stack
- **Backend**: Java 17, Spring Boot 3.1.5, Maven
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Server**: Embedded Tomcat (Spring Boot)
- **Port**: 5000

## Color Legend
- 🟡 Current Position (Gold gradient)
- 🔴 Comparing (Red gradient)
- 🟢 Sorted/Found (Green gradient)
- 🔵 Default (Blue gradient)
- 🟠 Highlight/Pivot (Orange gradient)

## Running the Application
The application runs automatically on port 5000 via the configured workflow:
```bash
mvn spring-boot:run
```

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/com/algorithm/visualizer/
│   │   │   ├── AlgorithmVisualizerApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── AlgorithmController.java
│   │   │   │   └── WebController.java
│   │   │   ├── model/
│   │   │   │   ├── AlgorithmStep.java
│   │   │   │   └── AlgorithmResult.java
│   │   │   └── service/
│   │   │       ├── SearchingService.java
│   │   │       └── SortingService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/styles.css
│   │       │   └── js/app.js
│   │       └── templates/
│   │           └── index.html
├── pom.xml
└── replit.md
```

## User Preferences
- Professional, modern UI with gradients and animations
- Color-coded visualization for easy understanding
- Comprehensive complexity analysis
- Step-by-step iteration control
