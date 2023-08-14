import { createApp } from 'vue'
import './style.css'
// @ts-ignore
import App from './App.vue'

export const backendBaseUrl: string = "http://localhost:8080";

createApp(App).mount('#app')
