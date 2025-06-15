"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
require("./assets/main.css");
var vue_1 = require("vue");
var App_vue_1 = require("./App.vue");
// 引入 Element Plus 和样式
var element_plus_1 = require("element-plus");
require("element-plus/dist/index.css");
var app = (0, vue_1.createApp)(App_vue_1.default);
app.use(element_plus_1.default);
app.mount('#app');
