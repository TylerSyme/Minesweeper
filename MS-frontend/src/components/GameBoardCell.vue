<script setup lang="ts">
const props = defineProps<{
  displayWidth: number;
  boardCell: Cell;
}>();
</script>

<template>
  <div class="aspect-square p-1" :style="{width: props.displayWidth + '%'}">
    <!--    <div v-if="boardCell.flagged" class="bg-red-600 w-full h-full rounded"></div>-->
    <!--    <div v-else-if="boardCell.revealed" class="bg-darkgray-800 w-full h-full rounded flex justify-center items-center">-->
    <!--      <div v-if="boardCell.adjacentMines > 0" class="text-gray-200">{{boardCell.adjacentMines}}</div>-->
    <!--    </div>-->
    <!--    <div v-else class="bg-darkgray-800 w-full h-full rounded"></div>-->

    <!--    TESTING  -->
    <div class="w-full h-full rounded flex justify-center items-center"
         :class="boardCell.mine ? 'bg-red-600' : 'bg-darkgray-800'"
         @click="$emit('revealCell')"
         @contextmenu.prevent="$emit('toggleFlag')">
      <div v-if="boardCell.adjacentMines > 0" class="text-gray-200">{{ boardCell.adjacentMines }}</div>
    </div>
  </div>
</template>
