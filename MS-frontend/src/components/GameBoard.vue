<script setup lang="ts">
import {ref} from "vue";
import GameBoardCell from "./GameBoardCell.vue";
import {backendBaseUrl} from "../main.ts";

const props = defineProps<{
  initialBoard: Board
}>();
const board = ref<Board>(props.initialBoard);
const flagCount = ref<number>(0);
const emit = defineEmits(["backHome", "mineExploded", "gameWon"]);

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
</script>

<template>
  <div class="bg-darkgray-900 rounded-lg p-16 pt-8 bg-opacity-30 shadow-lg backdrop-blur border-darkgray-800 border scroll-p-16" :style="{width: `${board.width * 48}px`}">
    <div class="flex justify-between items-center pb-8 text-gray-200">
      <span class="material-symbols-outlined p-1 cursor-pointer" @click="$emit('backHome')">arrow_back</span>
      <div>
        <span><img src="/mine.png" alt="Number of mines" class="h-full max-h-8 w-auto inline-block"/>{{ board.numMines }}</span>
        <span class="pl-8"><img src="/flag.png" alt="Flags placed" class="h-full max-h-8 w-auto inline-block"/>{{ flagCount }}</span>
      </div>
      <span>Timer...</span>
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
