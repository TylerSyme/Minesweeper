<script setup lang="ts">
defineProps<{
  expanded: Boolean
}>();
</script>

<template>
  <div class="relative h-8 w-16 cursor-pointer" :class="{'expanded-line': expanded}">
    <div class="bg-indigo-600 inline-block absolute w-8 h-1 left-1/2 top-1/2 transition-transform duration-200"></div>
    <div class="bg-indigo-600 inline-block absolute w-8 h-1 right-1/2 top-1/2 transition-transform duration-200"></div>
  </div>
</template>

<style scoped>
.expanded-line div:nth-child(1) {
  transform: rotate(-45deg);
  transform-origin: 0 100%;
}

.expanded-line div:nth-child(2) {
  transform: rotate(45deg);
  transform-origin: 100% 100%;
}

.expanded-line div {
  top: 80%;
  transition: transform 0.2s ease-out, top 0.2s;
}
</style>