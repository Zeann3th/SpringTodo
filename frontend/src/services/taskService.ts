import type { Task } from '@/types/Task';
import axios from 'axios';

const client = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
});

export default {
  getTasks() {
    return client.get('/tasks').then(response => response.data);
  },
  createTask(task: Task) {
    return client.post('/tasks', task).then(response => response.data);
  }
};

