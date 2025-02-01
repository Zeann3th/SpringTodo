<script setup lang="ts">
import { useQuery } from '@tanstack/vue-query'
import taskService from '@/services/taskService'
import Card from '@/components/Card.vue'
import { RouterLink } from 'vue-router'

const { isLoading, isError, data: tasks, error } = useQuery({
  queryKey: ['task'],
  queryFn: taskService.getTasks,
})

</script>

<template>
  <div class="max-w-4xl mx-auto p-6">
    <h1 class="text-3xl font-bold text-gray-800 text-center mb-6">Tasks</h1>

    <p v-if="isLoading" class="text-center text-gray-500">Loading...</p>
    <p v-else-if="isError" class="text-center text-red-500">Error: {{ error }}</p>

    <div v-else class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
      <Card v-for="task in tasks" :key="task.id" :task="task" />
    </div>

    <RouterLink to="/add"
      class="fixed bottom-4 right-4 flex items-center justify-center bg-blue-600 text-white rounded-full w-16 h-16 shadow-lg hover:bg-blue-700 transition-colors">
      <span class="text-3xl">+</span>
    </RouterLink>
  </div>
</template>
