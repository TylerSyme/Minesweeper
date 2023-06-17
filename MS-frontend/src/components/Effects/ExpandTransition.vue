<script setup lang="ts">
function resetElementHeight(element) {
  element.style.height = element.scrollHeight + "px";
}

function clearElementHeight(element) {
  element.style.height = "";
}
</script>

<template>
  <Transition name="expand" @enter="resetElementHeight" @after-enter="clearElementHeight"
              @before-leave="resetElementHeight" @after-leave="clearElementHeight">
    <slot></slot>
  </Transition>
</template>

<style>
.expand-enter-active,
.expand-leave-active {
  will-change: height, opacity;
  overflow: hidden;
}

.expand-enter-active {
  transition: height 0.3s ease, opacity 0.4s ease;
}

.expand-leave-active {
  transition: height 0.4s ease, opacity 0.3s ease;
}

.expand-enter-from,
.expand-leave-to {
  height: 0 !important;
  opacity: 0;
}
</style>