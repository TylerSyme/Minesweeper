<script setup lang="ts">
import {ref} from "vue";
import GameBoardCell from "./GameBoardCell.vue";
import {backendBaseUrl} from "../main.ts";

const props = defineProps<{
  initialBoard: Board
}>();
const board = ref<Board | undefined>(props.initialBoard);
const flagsLeft = ref<number>(props.initialBoard.numMines as number);

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
    console.log("toggleFlag data: " + data);
    // board.value?.cells[coordinate.x][coordinate.y].flagged = data;
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
    console.log("revealCell data: ");
    console.log(data as RevealResponse);
    if (data.isMine) {
      // TODO: mine exploded
      console.log("Mine exploded");
    } else {
      // TODO: reveal cell
      // TODO: stuff if game is won
      if (data.gameWon) {
        console.log("You win");
      }
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
