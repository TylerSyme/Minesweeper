# Minesweeper

A Minesweeper game with an independent front end and back end that interact with each other using an API. The back end is written in Java using Spring Boot with tests in JUnit, while the front end is written in TypeScript using Vue.js and Tailwind CSS.

## Screenshots

![image](https://github.com/TylerSyme/Minesweeper/assets/26509184/105d6d57-2270-4e4e-ae19-5253daed9793)
![image](https://github.com/TylerSyme/Minesweeper/assets/26509184/cd2a5d4f-7db1-4406-adb7-e7c229068160)
![image](https://github.com/TylerSyme/Minesweeper/assets/26509184/4a392f8f-c6ba-409a-a894-ba9df844d183)

## Gameplay Features

- Multiple difficulties: easy, medium, hard and custom
- The custom difficulty allows users to set the board size and number of mines
- Displays remaining flags for the number of bombs on the board
- Displays the time spent by the user on the current board

## Gameplay Instructions

- Choose a difficulty level or create a custom game and choose your own settings
- Left-click on a cell to reveal it. If a number is present, it represents the number of adjacent mines
- Right-click on a cell to place a flag
- If you reveal a mine, the game is over and you can start a new game
- If you successfully reveal all cells that do not contain mines, you win the game

## Installation

To run the project, you need to have Java, Maven and Node.js (with npm) installed.

After downloading the project, build and run the back end using Maven. For the front end, run `npm install` from the "MS-frontend" directory to install the necessary packages, then use `npm run dev` to run it locally. Both the back end and the front end need to be running at the same time for them to interact via the API.

## Architecture

### Back end

The back end uses a typical Spring Boot project structure with model, controller and service classes. The models defines the structure and attributes of the entities. The controllers defines the API endpoints that the front end interacts with. The serice classes contain the business logic and enforce the game rules. There are also tests written in JUnit associated with the service class.

### Front end

The front end contains a directory for types (*.d.ts files) that correspond the the model classes in the back end. The components are split between regular components and effects that are just used for animations.
