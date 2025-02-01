import { createRouter, createWebHistory } from 'vue-router'
import TaskForm from '@/views/TaskForm.vue'
import Dashboard from '@/views/Dashboard.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      component: Dashboard
    },
    {
      path: "/add",
      component: TaskForm
    }
  ],
})

export default router
