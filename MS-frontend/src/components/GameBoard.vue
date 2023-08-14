<script setup lang="ts">
import {computed, ref} from "vue";
import GameBoardCell from "./GameBoardCell.vue";
import {backendBaseUrl} from "../main.ts";

const props = defineProps<{
  initialBoard: Board
}>();
const emit = defineEmits(["backHome", "mineExploded", "gameWon"]);
const board = ref<Board>(props.initialBoard);
const flagCount = ref<number>(0);
const playerWon = ref<boolean>(false);
const secondsPast = ref(0);
const secondsInterval = setInterval(() => secondsPast.value++, 1000);
const displayTime = computed(() => {

  let seconds: number = secondsPast.value % 60;
  let minutes: number = Math.trunc(secondsPast.value / 60) % 60;

  if (secondsPast.value < 60) {
    return secondsPast.value + "s";
  } else if (secondsPast.value < 60 * 60) {
    return minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);
  } else if (secondsPast.value < 99 * 60 * 60) {
    return Math.trunc(secondsPast.value / (60 * 60)) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
  } else return "--:--:--";
});

function toggleFlag(coordinate: Coordinate) {
  fetch(backendBaseUrl + "/api/flag", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(coordinate)
  }).then(response => {
    if (response.ok) {
      return response.json();
    }
    throw new Error(response.statusText);
  }).then(data => {
    (board.value as Board).cells[coordinate.x][coordinate.y].flagged = data;
    if ((board.value as Board).cells[coordinate.x][coordinate.y].revealed) {
      return;
    }
    if (data) {
      flagCount.value++;
    } else {
      flagCount.value--;
    }
  }).catch(error => console.log(error));
}

function revealCell(coordinate: Coordinate) {
  fetch(backendBaseUrl + "/api/reveal", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(coordinate)
  }).then(response => {
    if (response.ok) {
      return response.json();
    }
    throw new Error(response.statusText);
  }).then(data => {
    // Update board for revealed cells
    board.value = data.updatedBoard;
    if (data.isMine) {
      clearInterval(secondsInterval);
      emit("mineExploded");
    } else if (data.gameWon) {
      clearInterval(secondsInterval);
      playerWon.value = true;
      emit("gameWon");
    }
    // Update flag count
    let newFlagCount: number = 0;
    board.value.cells.forEach(row => {
      row.forEach(cell => {
        if (cell.flagged && !cell.revealed) {
          newFlagCount++;
        }
      });
    });
    flagCount.value = newFlagCount;
  }).catch(error => console.log(error));
}
</script>

<template>
  <div class="bg-darkgray-900 rounded-lg p-16 pt-8 bg-opacity-30 shadow-lg backdrop-blur border-darkgray-800 border scroll-p-16" :style="{width: `${board.width * 48}px`}">
    <div class="flex items-center pb-8 text-gray-200">
      <div class="flex-1"><span class="material-symbols-outlined p-1 cursor-pointer" @click="$emit('backHome')">arrow_back</span></div>
      <div class="flex-none flex">
        <span class="h-full max-h-8 flex items-center"><img src="/mine.png" alt="Number of mines" class="h-full max-h-8 w-auto inline-block mr-1"/><span>{{ board.numMines }}</span></span>
        <span class="flex items-center h-full max-h-8 pl-8"><img src="/flag.png" alt="Flags placed" class="h-full max-h-8 w-auto inline-block mr-1"/><span>{{ flagCount }}</span></span>
      </div>
      <div class="flex-1 text-right">{{ displayTime }}</div>
    </div>
    <div class="flex flex-wrap relative neon-gradient" :class="{'neon-gradient-active': playerWon}">
      <template v-for="(row, rowIndex) in board.cells" :key="rowIndex">
        <template v-for="(cell, colIndex) in row" :key="colIndex">
          <GameBoardCell :board-cell="cell" :display-width="100 / board.width" @toggle-flag="toggleFlag"
                         @reveal-cell="revealCell"/>
        </template>
      </template>
    </div>
  </div>
</template>

<style scoped>
.neon-gradient:before, .neon-gradient:after {
  content: "";
  position: absolute;
  inset: 50%;
  z-index: -1;
  transition: 1s ease-out;
}

.neon-gradient-active:before, .neon-gradient-active:after {
  background: linear-gradient(135deg, #f0075b, transparent 40%, transparent 60%, #4f46e5);
  inset: -4px;
}

.neon-gradient-active:after {
  filter: blur(20px);
}
</style>
