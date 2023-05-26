<script setup lang="ts">
import {ref} from "vue";
import GameBoard from "./GameBoard.vue";
import StartScreen from "./StartScreen.vue";

enum GameState {
  startScreen = "startScreen",
  inGame = "inGame",
  gameOverLost = "gameOverLost",
  gameOverWon = "gameOverWon"
}

const gameState = ref<GameState>(GameState.startScreen);
const board = ref<Board | undefined>(undefined);

function createGame(requestUrl) {
  fetch(requestUrl, {
    headers: {
      "Content-Type": "application/json"
    }
  }).then(response => {
    if (response.ok) {
      return response.json();
    }
    throw new Error(response.statusText);
  }).then(data => {
    board.value = data;
    gameState.value = GameState.inGame;
  }).catch(error => console.log(error));
}
</script>

<template>
  <template v-if="gameState === GameState.startScreen">
    <StartScreen @create-game="createGame"/>
  </template>
  <template v-else>
    <GameBoard :initialBoard="board" @mine-exploded="() => gameState = GameState.gameOverLost"
               @game-won="() => gameState = GameState.gameOverWon"/>
    <div v-show="gameState === GameState.gameOverLost || gameState === GameState.gameOverWon"
         class="absolute top-0 left-0 right-0 bottom-0 flex justify-center items-center">
      <div class="bg-red-600 p-8 rounded">
        <p v-if="gameState === GameState.gameOverLost" class="text-gray-200">Mine exploded...</p>
        <p v-else-if="gameState === GameState.gameOverWon" class="text-gray-200">Game over - you win</p>
      </div>
    </div>
  </template>
</template>
