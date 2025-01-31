<script setup lang="ts">
const props = defineProps({
  task: {
    type: Object,
    required: true
  }
});
</script>

<template>
  <div class="max-w-md mx-auto bg-white shadow-lg rounded-lg p-6">
    <h1 class="text-2xl font-semibold text-gray-800 mb-4 text-center">{{ task.title.toUpperCase() }}</h1>
    <div class="space-y-2">
      <p class="text-gray-600">{{ task.description }}</p>
      <p class="px-3 py-1 inline-block text-sm font-semibold rounded-full"
        :class="task.status === 'Completed' ? 'bg-green-200 text-green-800' : 'bg-yellow-200 text-yellow-800'">
        {{ task.status ? task.status : 'PENDING' }}
      </p>
      <p class="text-sm text-gray-500">Due: {{ task.dueDate }}</p>
    </div>
  </div>
</template>

<style scoped></style>
