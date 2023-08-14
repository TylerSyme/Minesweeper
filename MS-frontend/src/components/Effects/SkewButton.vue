<template>
  <button type="button" class="skew-btn">
    <span></span>
    <span></span>
    <slot></slot>
  </button>
</template>

<style scoped>
.skew-btn {
  position: relative;
  display: block;
  width: 100%;
  max-width: 200px;
  padding: 1rem 2rem;
  font-size: 1.25rem;
  text-align: center;
  color: #f4f4f4;
  border: 1px solid #f0075b;
  background: transparent;
  text-transform: uppercase;
  letter-spacing: 2px;
  transform: skew(-15deg);
  transition: 0.2s ease-in;
}

.skew-btn:hover {
  color: #1b1422;
  background: #f0075b;
  box-shadow: 0 0 10px #f0075b, 0 0 40px #f0075b, 0 0 80px #f0075b;
  transition: 0.2s ease-out;
}

.skew-btn span, .skew-btn span {
  position: absolute;
  bottom: -11px;
  right: -11px;
}

.skew-btn span:nth-child(1) {
  height: 3px;
  width: 80%;
  background: linear-gradient(90deg, transparent, #4f46e5);
}

.skew-btn span:nth-child(2) {
  height: 80%;
  width: 3px;
  background: linear-gradient(transparent, #4f46e5);
}

.skew-btn span:after {
  content: "";
  position: absolute;
  bottom: 0;
  right: 0;
  background: #4f46e5;
  transition: width 0.3s ease-in, height 0.3s ease-in;
}

.skew-btn span:nth-child(1):after {
  height: 3px;
  width: 0;
}

.skew-btn span:nth-child(2):after {
  height: 0;
  width: 3px;
}

.skew-btn:hover span:nth-child(1):after {
  width: 100%;
  transition: width 0.15s ease-out;
}

.skew-btn:hover span:nth-child(2):after {
  height: 100%;
  transition: height 0.15s ease-out;
}
</style>