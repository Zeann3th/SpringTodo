export interface Task {
  title: string;
  description: string;
  status: "PENDING" | "IN_PROGRESS" | "COMPLETED" | "LATE" | null;
  dueDate: string | null;
  completedAt: string | null;
  priority: number
}

