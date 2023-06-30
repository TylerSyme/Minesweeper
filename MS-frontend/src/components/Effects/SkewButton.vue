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
  text-transform: uppercase;
  letter-spacing: 2px;
  transform: skew(-15deg);
  background: linear-gradient(150deg, rgba(215, 111, 190, 0.9), rgba(215, 111, 190, 0.4), rgba(47, 90,  188, 0.4), rgba(47, 90,  188, 0.9));
  transition: 0.2s ease-in;
}

.skew-btn:hover {
  background: linear-gradient(150deg, rgba(215, 111, 190, 1), rgba(215, 111, 190, 0.8), rgba(47, 90,  188, 0.8), rgba(47, 90,  188, 1));
  /*box-shadow: 0 0 10px #2196f3, 0 0 40px #2196f3, 0 0 80px #2196f3;*/
  transition: 0.2s ease-out;
}

.skew-btn span:nth-child(1), .skew-btn span:nth-child(2) {
  position: absolute;
  bottom: -11px;
  right: -11px;
  background: linear-gradient(150deg, rgba(215, 111, 190, 0.4), rgba(47, 90,  188, 0.4), rgba(47, 90,  188, 0.9));
}

.skew-btn span:nth-child(1) {
  height: 3px;
  width: 80%;
}

.skew-btn span:nth-child(2) {
  height: 80%;
  width: 3px;
}

.skew-btn span:nth-child(1):after, .skew-btn span:nth-child(2):after {
  content: "";
  position: absolute;
  bottom: 0;
  right: 0;
  background: linear-gradient(150deg, rgba(215, 111, 190, 0.7), rgba(47, 90,  188, 0.7), rgba(47, 90,  188, 0.9));
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