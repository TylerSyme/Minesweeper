<script setup lang="ts">
import {ref} from "vue";
import {backendBaseUrl} from "../main.ts";
import ExpandTransition from "./Transitions/ExpandTransition.vue";

const emit = defineEmits(["createGame"]);

enum Difficulty {
  easy = "easy",
  medium = "medium",
  hard = "hard",
  custom = "custom",
}

const boardWidth = ref<number>(10);
const numMines = ref<number>(10);
const showDifficulties = ref<boolean>(true);

function startGame(mode: Difficulty) {
  let requestUrl = backendBaseUrl + "/api/difficulty/" + mode as string;
  if (mode as string === "custom") {
    requestUrl = backendBaseUrl + "/api/custom?size=" + boardWidth.value + "&numMines=" + numMines.value;
  }
  emit("createGame", requestUrl);
}
</script>

<template>
  <div class="bg-darkgray-900 rounded-lg max-w-lg w-full p-24 bg-opacity-30 shadow-lg backdrop-blur border-darkgray-800 border">
    <h1 class="font-display text-5xl text-gray-200 text-center pb-12">Minesweeper</h1>
    <ExpandTransition>
      <div v-show="showDifficulties">
        <div v-for="difficulty in [Difficulty.easy, Difficulty.medium, Difficulty.hard]" class="py-2">
          <button
              class="block rounded w-full p-4 text-gray-200 cursor-pointer italic text-center bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border"
              @click="startGame(difficulty)">{{
              difficulty.toUpperCase()
            }}
          </button>
        </div>
      </div>
    </ExpandTransition>

    <div>
      <div class="py-2">
        <button
            class="block cursor-pointer w-full text-gray-200 text-center transition-all duration-300"
            :class="[showDifficulties ? 'rounded p-4 italic bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border' : 'text-2xl pb-4 bg-transparent border-transparent']"
            @click="showDifficulties = !showDifficulties">{{
            Difficulty.custom.toUpperCase()
          }}
        </button>
      </div>
      <ExpandTransition>
        <div v-show="!showDifficulties" class="flex flex-wrap">
          <div class="w-1/2 px-2 box-border">
            <label for="boardWidth" class="text-gray-200 pb-2 block text-center">Board width</label>
            <input type="text" id="boardWidth" v-model="boardWidth"
                   class="appearance-none max-w-full w-full text-gray-200 caret-gray-200 py-2 px-4 focus:outline-none rounded bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border"/>
          </div>
          <div class="w-1/2 px-2 box-border">
            <label for="numMines" class="text-gray-200 pb-2 block text-center">Number of mines</label>
            <input type="text" id="numMines" v-model="numMines"
                   class="appearance-none max-w-full w-full text-gray-200 caret-gray-200 py-2 px-4 focus:outline-none rounded bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border"/>
          </div>
          <div v-if="boardWidth * boardWidth < numMines" class="w-full pt-2 text-center text-red-500">
            The board must be large enough to fit all mines
          </div>
          <div class="w-full text-center pt-8">
            <button type="button" class="inline-block bg-red-600 hover:bg-red-700 text-gray-200 py-4 px-8 rounded"
                    @click="startGame(Difficulty.custom)">
              Create Game
            </button>
          </div>
        </div>
      </ExpandTransition>
    </div>

  </div>
</template>
