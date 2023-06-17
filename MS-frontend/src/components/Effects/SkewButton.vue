<!-- The animation used is similar to this: https://codepen.io/RobinTreur/pen/dGybQW -->
<template>
  <div class="relative text-gray-50 cursor-pointer v19">
    <span class="btn-label block relative">
      <slot></slot>
    </span>
    <span class="effect1 before:bg-indigo-600 after:bg-indigo-600">
      <span class="before:bg-indigo-600 after:bg-indigo-600"></span>
    </span>
    <span class="effect2 bg-indigo-600 backdrop-blur bg-opacity-60"></span>
  </div>
</template>

<style scoped>
.v19 .btn-label {
  text-align: center;
  padding: 10px 30px;
  z-index: 10;
  line-height: 2;
  transition: all 0.225s;
  transition-delay: 0.45s;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.v19 .effect2 {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: all 0.225s;
  transform: skew(-20deg, 0deg) scaleX(1);
  transition-delay: 0.45s;
}

.v19 .effect1 {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: all 0.225s;
  transform: scale(0.9) skew(0deg, 0deg);
  transition-delay: 0.15s;
}

.v19 .effect1:before, .v19 .effect1:after {
  width: 60px;
  opacity: 0;
  content: '';
  height: 1px;
  position: absolute;
  display: block;
  transition: all 0.375s;
  transition-delay: 0.3s;
}

.v19 .effect1:before {
  top: 0;
  left: calc(50% - 30px);
}

.v19 .effect1:after {
  bottom: 0;
  right: calc(50% - 30px);
}

.v19 .effect1 span:before, .v19 .effect1 span:after {
  height: 0;
  transition-delay: 0s;
  content: '';
  position: absolute;
  display: block;
  width: 2px;
  transition: all 0.225s;
}

.v19 .effect1 span:before {
  left: 0;
  top: 0;
}

.v19 .effect1 span:after {
  right: 0;
  bottom: 0;
}

.v19:hover .effect1 {
  transform: skew(-20deg, 0deg);
}

.v19:hover .effect1:before, .v19:hover .effect1:after {
  transition-delay: 0s;
  width: 30px;
  opacity: 1;
}

.v19:hover .effect1:before {
  left: 0;
}

.v19:hover .effect1:after {
  right: 0;
}

.v19:hover .effect1 span:before, .v19:hover .effect1 span:after {
  height: 30px;
  transition-delay: 0.45s;
}

.v19:hover .effect2 {
  transform: skew(-20deg, 0deg) scaleX(0.2);
  background: transparent;
  transition-delay: 0s;
  opacity: 0;
}
</style>
