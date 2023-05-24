<script setup lang="ts">
import {ref} from "vue";

const board = ref<Board | undefined>(undefined);

enum Difficulty {
  easy = "easy",
  medium = "medium",
  hard = "hard",
  custom = "custom",
}

const selectedDifficulty = ref<Difficulty | undefined>(undefined);
const boardWidth = ref<number>(1);
const numMines = ref<number>(1);
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
      <div v-if="difficulty === 'custom' && selectedDifficulty === 'custom'" class="flex pb-4">
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
      </div>
    </div>
    <div class="text-center">
      <button type="button" class="bg-red-600 hover:bg-red-700 text-gray-200 py-4 px-8 mt-4 rounded">Start</button>
    </div>
  </div>
  <div v-else></div>
</template>
