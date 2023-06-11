<script setup lang="ts">
const props = defineProps<{
  displayWidth: number;
  boardCell: Cell;
}>();
</script>

<template>
  <div class="aspect-square bg-opacity-70 backdrop-blur bg-darkgray-800 select-none"
       :style="{width: props.displayWidth + '%'}">
    <div v-if="boardCell.flagged" class="bg-red-600 w-full h-full"
         @click="$emit('revealCell', boardCell.coordinate)"
         @contextmenu.prevent="$emit('toggleFlag', boardCell.coordinate)"></div>
    <div v-else-if="boardCell.revealed && boardCell.mine" @contextmenu.prevent
         class="w-full h-full bg-cyan-500 rounded-full">
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
