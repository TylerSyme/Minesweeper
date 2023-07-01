<script setup lang="ts">
import {ref} from "vue";
import {backendBaseUrl} from "../main.ts";
import ExpandTransition from "./Effects/ExpandTransition.vue";
import SkewButton from "./Effects/SkewButton.vue";
import ExpandingLine from "./Effects/ExpandingLine.vue";

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
  <div class="bg-darkgray-900 rounded-lg p-24 bg-opacity-30 shadow-lg backdrop-blur border-darkgray-800 border">
    <h1 class="font-display text-5xl text-gray-200 text-center tracking-wider">Minesweeper</h1>
    <div class="flex justify-center" :class="showDifficulties ? 'py-10' : 'pt-8 pb-4'">
      <ExpandingLine :expanded="!showDifficulties" @click="showDifficulties = !showDifficulties"/>
    </div>
    <ExpandTransition>
      <div v-show="showDifficulties" class="flex flex-col items-center gap-8">
        <template v-for="difficulty in [Difficulty.easy, Difficulty.medium, Difficulty.hard]">
          <SkewButton @click="startGame(difficulty)">{{ difficulty }}</SkewButton>
        </template>
        <SkewButton @click="showDifficulties = !showDifficulties">{{ Difficulty.custom }}</SkewButton>
      </div>
    </ExpandTransition>

    <div>
      <div class="py-2">
        <button v-if="!showDifficulties"
                class="block cursor-pointer w-full text-gray-200 text-center uppercase text-3xl tracking-wide pb-4"
                @click="showDifficulties = !showDifficulties">{{ Difficulty.custom }}
        </button>
      </div>
      <ExpandTransition>
        <div v-show="!showDifficulties">
          <div class="flex justify-center items-center pt-4 pb-2">
            <label for="boardWidth" class="w-1/2 text-gray-200 text-right pr-8">Board width</label>
            <div class="w-1/2 pl-8">
              <input type="text" id="boardWidth" v-model="boardWidth"
                     class="appearance-none max-w-[6rem] w-full select-all selection:bg-darkgray-800 selection:text-indigo-600 text-gray-200 caret-gray-200 text-right py-2 px-4 focus:outline-none rounded bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border"/>
            </div>
          </div>
          <div class="flex justify-center items-center pt-2 pb-4">
            <label for="numMines" class="w-1/2 text-gray-200 text-right pr-8">Number of mines</label>
            <div class="w-1/2 pl-8">
              <input type="text" id="numMines" v-model="numMines"
                     class="appearance-none max-w-[6rem] w-full select-all selection:bg-darkgray-800 selection:text-indigo-600 text-gray-200 caret-gray-200 text-right py-2 px-4 focus:outline-none rounded bg-opacity-70 shadow-lg backdrop-blur bg-darkgray-800 border-darkgray-800 border"/>
            </div>
          </div>
          <ExpandTransition>
            <div v-if="boardWidth * boardWidth < numMines" class="max-w-xs pt-4 pb-2 text-center text-red-500 mx-auto">
              The board must be large enough to fit all mines
            </div>
          </ExpandTransition>
          <div class="w-full pt-8 flex justify-center">
            <SkewButton @click="startGame(Difficulty.custom)">Start</SkewButton>
          </div>
        </div>
      </ExpandTransition>
    </div>

  </div>
</template>
