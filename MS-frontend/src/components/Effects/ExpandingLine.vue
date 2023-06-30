<script setup lang="ts">
defineProps<{
  expanded: Boolean
}>();
</script>

<template>
  <div class="expanding-line" :class="{triangle: expanded}">
    <div class="bg-indigo-600"></div>
    <div class="bg-indigo-600"></div>
  </div>
</template>

<style scoped>
.expanding-line {
  position: relative;
  height: 2rem;
  width: 4rem;
  cursor: pointer;
}

.expanding-line div {
  display: inline-block;
  position: absolute;
  width: 2rem;
  height: 4px;
  top: 50%;
  transition: transform 0.2s ease-out;
}

.expanding-line div:nth-child(1) {
  left: 50%
}

.expanding-line div:nth-child(2) {
  right: 50%
}

.expanding-line.triangle div:nth-child(1) {
  transform: rotate(-45deg);
  transform-origin: 0 100%;
}

.expanding-line.triangle div:nth-child(2) {
  transform: rotate(45deg);
  transform-origin: 100% 100%;
}

.expanding-line.triangle div {
  top: 80%;
  transition: transform 0.2s ease-out, top 0.2s;
}
</style>