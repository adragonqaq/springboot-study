import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import vuetify from './plugins/vuetify';
// Vuetify

// Vuetify

import { aliases, mdi } from 'vuetify/iconsets/mdi'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
const app = createApp(App)

app.use(createPinia())
app.use(router)


app.mount('#app')
createApp(App)
    .use(router)
    .use(vuetify)
    .mount('#app')
