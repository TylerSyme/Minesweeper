<script setup lang="ts">
import {ref} from "vue";

const props = defineProps<{
  initialBoard: Board
}>();
const board = ref<Board | undefined>(props.initialBoard);
const flagsLeft = ref<number>(props.initialBoard.numMines as number);
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
          <div class="bg-blue-400 aspect-square" :style="{width: (100/board?.width) + '%'}">X</div>
        </template>
      </template>
    </div>
  </div>
</template>
