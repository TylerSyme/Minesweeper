<script setup lang="ts">
import {onMounted, ref} from "vue";
import GameBoardCell from "./GameBoardCell.vue";
import {backendBaseUrl} from "../main.ts";

const props = defineProps<{
  initialBoard: Board
}>();
let board = ref<Board | undefined>(undefined);
let flagsLeft = ref<number>(0);

const emit = defineEmits(["mineExploded", "gameWon"]);

onMounted(() => {
  board.value = props.initialBoard;
  flagsLeft.value = props.initialBoard.numMines;
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
    if (board.value) {
      board.value.cells[coordinate.x][coordinate.y].flagged = data;
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
  }).catch(error => console.log(error));
}
</script>

<template>
  <div class="bg-darkgray-900 rounded max-w-xl w-full p-8">
    <div class="flex justify-center items-center pb-4 text-gray-200">
      <span>Total mines: {{board?.numMines}}</span>
      <span class="pl-8">Flags left: {{flagsLeft}}</span>
    </div>
    <div class="flex flex-wrap">
      <template v-for="(row, rowIndex) in board?.cells" :key="rowIndex">
        <template v-for="(cell, colIndex) in row" :key="colIndex">
          <GameBoardCell :board-cell="cell" :display-width="100 / board?.width" @toggle-flag="toggleFlag" @reveal-cell="revealCell"/>
        </template>
      </template>
    </div>
  </div>
</template>
