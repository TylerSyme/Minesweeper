<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import GameBoardCell from "./GameBoardCell.vue";
import {backendBaseUrl} from "../main.ts";

const props = defineProps<{
  initialBoard: Board
}>();
const emit = defineEmits(["backHome", "mineExploded", "gameWon"]);
const board = ref<Board>(props.initialBoard);
const flagCount = ref<number>(0);
const secondsPast = ref(0);
const displayTime = computed(() => {

  let seconds: number = secondsPast.value % 60;
  let minutes: number = Math.trunc(secondsPast.value / 60);

  if (secondsPast.value < 60) {
    return secondsPast.value + "s";
  } else if (secondsPast.value < 60 * 60) {
    return minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);
  } else if (secondsPast.value < 99 * 60 * 60) {
    return Math.trunc(minutes / 60) + ":" + (minutes % 60 < 10 ? "0" + minutes % 60 : minutes % 60) + ":" + (seconds < 10 ? "0" + seconds : seconds);
  } else return "--:--:--";
});

function updateTime() {
  secondsPast.value++;
  setTimeout(updateTime, 1000);
}

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
      emit("mineExploded");
    } else if (data.gameWon) {
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

onMounted(() => updateTime());
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
    <div class="flex flex-wrap">
      <template v-for="(row, rowIndex) in board.cells" :key="rowIndex">
        <template v-for="(cell, colIndex) in row" :key="colIndex">
          <GameBoardCell :board-cell="cell" :display-width="100 / board.width" @toggle-flag="toggleFlag"
                         @reveal-cell="revealCell"/>
        </template>
      </template>
    </div>
  </div>
</template>
