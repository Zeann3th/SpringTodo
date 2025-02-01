<script setup lang="ts">
import { ref } from 'vue';
import { useMutation } from '@tanstack/vue-query';
import taskService from '@/services/taskService';
import type { Task } from '@/types/Task';
import { useRouter } from 'vue-router';

const router = useRouter();
const title = ref('');
const description = ref('');
const priority = ref(1);
const dueDate = ref(null);

const mutation = useMutation({
  mutationFn: async (task: Task) => {
    console.log('Submitting task:', task);
    return taskService.createTask(task);
  },
  onSuccess: () => {
    console.log('Task created successfully');
    title.value = '';
    description.value = '';
    priority.value = 1;
    dueDate.value = null;
    router.push('/');
  },
  onError: (error) => {
    console.error('Error submitting form:', error);
    alert('Failed to submit task. Please try again.');
  }
});

const submitForm = () => {
  if (!title.value || !description.value) {
    alert('Title and Description are required.');
    return;
  }

  mutation.mutate({
    title: title.value,
    description: description.value,
    priority: priority.value,
    dueDate: dueDate.value || null,
    status: null,
    completedAt: null
  });
};
</script>

<template>
  <div class="max-w-xl mx-auto p-6 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-semibold text-gray-800 mb-4">Add New Task</h2>
    <form @submit.prevent="submitForm" class="space-y-4">
      <div>
        <label class="block text-gray-700">Title</label>
        <input v-model="title" type="text" class="w-full p-2 border rounded" required />
      </div>
      <div>
        <label class="block text-gray-700">Description</label>
        <textarea v-model="description" class="w-full p-2 border rounded"></textarea>
      </div>
      <div>
        <label class="block text-gray-700">Priority</label>
        <select v-model="priority" class="w-full p-2 border rounded">
          <option :value="1">Low</option>
          <option :value="2">Medium</option>
          <option :value="3">High</option>
        </select>
      </div>
      <div>
        <label class="block text-gray-700">Due Date (optional)</label>
        <input v-model="dueDate" type="date" class="w-full p-2 border rounded" />
      </div>
      <button type="submit" :disabled="!mutation.isIdle"
        class="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700 disabled:bg-blue-400">
        {{ mutation.isIdle ? 'Submit' : mutation.isPending ? 'Submitting...' : 'Submitted' }}
      </button>
    </form>
  </div>
</template>
