<script setup lang="ts">
import {ref} from "vue";
import {backendBaseUrl} from "../main.ts";
import GameBoard from "./GameBoard.vue";

// const board = ref<Board | undefined>(undefined);
const board = ref<Board | undefined>({"cells":[[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":0,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":0,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":0,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":0,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":1,"y":4}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":1,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":1,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":1,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":2,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":2,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":2,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":2,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":3,"coordinate":{"x":2,"y":4}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":2,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":3,"coordinate":{"x":2,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":2,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":2,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":2,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":3,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":3,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":3,"y":2}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":3,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":3,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":3,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":3,"y":6}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":3,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":3,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":3,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":4,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":4,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":4,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":4,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":4,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":5,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":5,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":5,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":5,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":5,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":5,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":5,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":5,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":5,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":5,"y":9}}],[{"mine":true,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":6,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":6,"y":2}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":6,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":6,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":6,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":3,"coordinate":{"x":7,"y":1}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":7,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":7,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":7,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":7,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":0}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":8,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":4}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":5}},{"mine":true,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":8,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":8,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":8,"y":9}}],[{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":9,"y":0}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":9,"y":1}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":9,"y":2}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":9,"y":3}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":9,"y":4}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":9,"y":5}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":2,"coordinate":{"x":9,"y":6}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":1,"coordinate":{"x":9,"y":7}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":9,"y":8}},{"mine":false,"revealed":false,"flagged":false,"adjacentMines":0,"coordinate":{"x":9,"y":9}}]],"width":10,"numMines":10});

enum Difficulty {
  easy = "easy",
  medium = "medium",
  hard = "hard",
  custom = "custom",
}

const selectedDifficulty = ref<Difficulty | undefined>(undefined);
const boardWidth = ref<number>(1);
const numMines = ref<number>(1);

function startGame() {
  let requestUrl = backendBaseUrl + "/api/difficulty/" + selectedDifficulty.value;
  if (selectedDifficulty.value as string === "custom") {
    requestUrl = backendBaseUrl + "/api/custom?size=" + boardWidth.value + "&numMines=" + numMines.value;
  }

  fetch(requestUrl, {
    headers: {
      "Content-Type": "application/json"
    }
  }).then(response => {
    if (response.ok) {
      return response.json();
    } else {
      throw new Error(response.statusText);
    }
  }).then(data => board.value = data).catch(error => console.log(error));
}
</script>

<template>
  <div v-if="board === undefined" class="bg-darkgray-900 rounded max-w-lg w-full p-12">
    <h1 class="font-display text-4xl text-gray-200 text-center pb-4">Minesweeper</h1>
    <p class="text-gray-300 py-2">Select a difficulty</p>
    <div v-for="difficulty in Difficulty">
      <input type="radio" :id="difficulty" :value="difficulty" v-model="selectedDifficulty" class="hidden"/>
      <label :for="difficulty" class="block rounded w-full p-4 my-4 text-gray-200 cursor-pointer"
             :class="difficulty === selectedDifficulty ? 'bg-red-600' : 'bg-darkgray-800'">{{
          difficulty.charAt(0).toUpperCase() + difficulty.slice(1)
        }}</label>
      <div v-if="difficulty === 'custom' && selectedDifficulty as string === 'custom'" class="flex flex-wrap pb-4">
        <div class="w-1/2 px-4 box-border">
          <label for="boardWidth" class="text-gray-200 pb-2 block text-center">Board width</label>
          <input type="number" id="boardWidth" v-model="boardWidth"
                 class="max-w-full w-full bg-darkgray-800 text-gray-200 py-2 px-4 border-0 outline-0 rounded"/>
        </div>
        <div class="w-1/2 px-4 box-border">
          <label for="numMines" class="text-gray-200 pb-2 block text-center">Number of mines</label>
          <input type="number" id="numMines" v-model="numMines"
                 class="max-w-full w-full bg-darkgray-800 text-gray-200 py-2 px-4 border-0 outline-0 rounded"/>
        </div>
        <div v-if="boardWidth * boardWidth < numMines" class="w-full pt-2 text-center text-red-500">
          The board must be large enough to fit all mines
        </div>
      </div>
    </div>
    <div class="text-center">
      <button type="button" class="bg-red-600 hover:bg-red-700 text-gray-200 py-4 px-8 mt-4 rounded" @click="startGame">
        Start
      </button>
    </div>
  </div>
  <div v-else>
    <GameBoard :initialBoard="board" />
  </div>
</template>
