<script setup lang="ts">
import Game from "./components/Game.vue";
</script>

<template>
  <div
      class="min-w-screen min-h-screen bg-darkgray-800 flex justify-center items-center overflow-auto bg-fixed bg-center bg-no-repeat bg-cover"
      :style="{backgroundImage: `url('/background.png')`}">
    <Game/>
  </div>
</template>