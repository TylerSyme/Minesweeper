<script setup lang="ts">
import {ref} from "vue";
import {backendBaseUrl} from "../main.ts";

const emit = defineEmits(["createGame"]);

enum Difficulty {
  easy = "easy",
  medium = "medium",
  hard = "hard",
  custom = "custom",
}

const selectedDifficulty = ref<Difficulty | undefined>(undefined);
const boardWidth = ref<number>(1);
const numMines = ref<number>(1);
const showDifficulties = ref<boolean>(true);

function startGame() {
  let requestUrl = backendBaseUrl + "/api/difficulty/" + selectedDifficulty.value;
  if (selectedDifficulty.value as string === "custom") {
    requestUrl = backendBaseUrl + "/api/custom?size=" + boardWidth.value + "&numMines=" + numMines.value;
  }
  emit("createGame", requestUrl);
}

function resetElementHeight(el) {
  el.style.height = el.scrollHeight + "px";
}

function clearElementHeight(el) {
  el.style.height = "";
}
</script>

<template>
  <div class="bg-darkgray-900 rounded max-w-lg w-full p-24 bg-opacity-30 shadow-lg backdrop-blur border-darkgray-900">
    <h1 class="font-display text-5xl text-gray-200 text-center pb-12">Minesweeper</h1>
    <div>
      <Transition name="expand" @enter="resetElementHeight" @after-enter="clearElementHeight" @before-leave="resetElementHeight" @after-leave="clearElementHeight">
        <div v-show="showDifficulties">
          <div v-for="difficulty in [Difficulty.easy, Difficulty.medium, Difficulty.hard]">
            <input type="radio" :id="difficulty" :value="difficulty" v-model="selectedDifficulty" class="hidden"/>
            <label :for="difficulty" class="block rounded w-full p-4 my-4 text-gray-200 cursor-pointer italic text-center bg-opacity-70 shadow-lg backdrop-blur"
                   :class="difficulty === selectedDifficulty ? 'bg-red-600' : 'bg-darkgray-800'">{{
                difficulty.toUpperCase()
              }}</label>
          </div>
        </div>
      </Transition>
    </div>

    <div>
      <input type="radio" :id="Difficulty.custom" :value="Difficulty.custom" v-model="selectedDifficulty" class="hidden" @click="showDifficulties = !showDifficulties"/>
      <label :for="Difficulty.custom" class="block rounded w-full p-4 my-4 text-gray-200 cursor-pointer italic text-center bg-opacity-70 shadow-lg backdrop-blur"
             :class="Difficulty.custom === selectedDifficulty ? 'bg-red-600 border-red-600' : 'bg-darkgray-800 border-darkgray-800'">{{
          Difficulty.custom.toUpperCase()
        }}</label>
      <div v-if="selectedDifficulty === Difficulty.custom"
           class="flex flex-wrap pb-4">
        <div class="w-1/2 px-2 box-border">
          <label for="boardWidth" class="text-gray-200 pb-2 block text-center">Board width</label>
          <input type="number" id="boardWidth" v-model="boardWidth"
                 class="max-w-full w-full bg-darkgray-800 text-gray-200 py-2 px-4 border-0 focus:outline-none rounded"/>
        </div>
        <div class="w-1/2 px-2 box-border">
          <label for="numMines" class="text-gray-200 pb-2 block text-center">Number of mines</label>
          <input type="number" id="numMines" v-model="numMines"
                 class="max-w-full w-full bg-darkgray-800 text-gray-200 py-2 px-4 border-0 focus:outline-none rounded"/>
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
</template>

<style scoped>
.expand-enter-active,
.expand-leave-active {
  will-change: height, opacity;
  transition: height 1s ease, opacity 0.3s ease;
  overflow: hidden;
}

.expand-enter-from,
.expand-leave-to {
  height: 0 !important;
  opacity: 0;
}
</style>
