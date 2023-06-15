<script setup lang="ts">
const props = defineProps<{
  displayWidth: number;
  boardCell: Cell;
}>();
</script>

<template>
  <div class="aspect-square bg-opacity-70 backdrop-blur bg-darkgray-800 select-none"
       :style="{width: props.displayWidth + '%'}">
    <div v-if="boardCell.flagged" class="bg-darkgray-900 w-full h-full"
         @click="$emit('revealCell', boardCell.coordinate)"
         @contextmenu.prevent="$emit('toggleFlag', boardCell.coordinate)">
      <img src="/flag.png" alt="Flag" class="w-full h-auto p-1"/>
    </div>
    <div v-else-if="boardCell.revealed && boardCell.mine" @contextmenu.prevent
         class="w-full h-full">
      <img src="/mine.png" alt="Mine" class="w-full h-auto"/>
    </div>
    <div v-else-if="boardCell.revealed" @contextmenu.prevent
         class="w-full h-full flex justify-center items-center">
      <div v-if="boardCell.adjacentMines > 0" class="text-gray-200">{{ boardCell.adjacentMines }}</div>
    </div>
    <div v-else class="bg-darkgray-900 w-full h-full"
         @click="$emit('revealCell', boardCell.coordinate)"
         @contextmenu.prevent="$emit('toggleFlag', boardCell.coordinate)"></div>
  </div>
</template>
