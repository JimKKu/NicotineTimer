import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'

// 引入 Element Plus 和样式
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App)
app.use(ElementPlus)
app.mount('#app')




// 禁用页面放大缩小
window.addEventListener('wheel', function (e) {
    if (e.ctrlKey) {
        e.preventDefault()
    }
}, { passive: false })

window.addEventListener('keydown', function (e) {
    // 禁用 Ctrl + '+' / '-' / '=' / '0'
    if (e.ctrlKey && ['+', '-', '=', '0'].includes(e.key)) {
        e.preventDefault()
    }
})


// 禁用右键
document.addEventListener('contextmenu', e => e.preventDefault())
document.addEventListener('keydown', function (e) {
    // F12
    if (e.key === 'F12') {
        e.preventDefault()
    }
    // Ctrl + Shift + I
    if (e.ctrlKey && e.shiftKey && e.key === 'I') {
        e.preventDefault()
    }
    // Ctrl + Shift + J
    if (e.ctrlKey && e.shiftKey && e.key === 'J') {
        e.preventDefault()
    }
    // Ctrl + U
    if (e.ctrlKey && e.key === 'u') {
        e.preventDefault()
    }
    // Ctrl + S (另存为)
    if (e.ctrlKey && e.key === 's') {
        e.preventDefault()
    }
})


