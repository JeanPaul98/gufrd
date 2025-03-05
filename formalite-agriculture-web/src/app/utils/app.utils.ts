export function  formatDateBK(dateString: string): string {
  const date = new Date(dateString);
  // Format as YYYY-MM-DDTHH:MM for input[type="datetime-local"]
  return date.toISOString().slice(0, 16);
}
