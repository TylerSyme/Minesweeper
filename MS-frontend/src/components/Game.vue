<script setup lang="ts">
import {ref} from "vue";
import GameBoard from "./GameBoard.vue";
import StartScreen from "./StartScreen.vue";
import ExpandTransition from "./Effects/ExpandTransition.vue";
import SkewButton from "./Effects/SkewButton.vue";

enum GameState {
  startScreen = "startScreen",
  inGame = "inGame",
  gameOverLost = "gameOverLost",
  gameOverWon = "gameOverWon"
}

const gameState = ref<GameState>(GameState.startScreen);
const board = ref<Board | undefined>(undefined);

function createGame(requestUrl: string) {
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
    <GameBoard :initialBoard="board" @back-home="() => gameState = GameState.startScreen"
               @mine-exploded="() => gameState = GameState.gameOverLost"
               @game-won="() => gameState = GameState.gameOverWon"/>
    <div v-show="gameState === GameState.gameOverWon || gameState === GameState.gameOverLost"
         class="fixed top-0 left-0 right-0 bottom-0 flex justify-center items-center">
      <ExpandTransition>
        <div class="bg-darkgray-900/50 rounded-lg p-20 shadow-lg backdrop-blur-sm border-darkgray-800 border">
          <h3 class="font-display text-3xl text-gray-200 text-center tracking-wide">
            {{ gameState === GameState.gameOverWon ? "You won!" : "Game over..." }}</h3>
          <p class="text-gray-200 text-center pt-4">{{ gameState === GameState.gameOverWon ? "Congratulations!" : "Try again" }}</p>
          <div class="flex justify-center pt-16">
            <SkewButton @click="() => gameState = GameState.startScreen">New Game</SkewButton>
          </div>
        </div>
      </ExpandTransition>
    </div>
  </template>
</template>
