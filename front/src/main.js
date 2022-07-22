import { createApp, h } from 'vue';
import App from './App.vue'
import router from './router/router';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';

import VueSweetalert2 from 'vue-sweetalert2';

const app = createApp({
    render: () => h(App)
});

app.use(router);
app.use(ElementPlus);
app.use(VueSweetalert2);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.mount('#app');