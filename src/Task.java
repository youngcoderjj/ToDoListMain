public class Task {
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String task_title;

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public Task() {

    }

    public Task(int id) {
        this.id = id;
    }

    public Task(boolean status, int id, String task_title) {
        this.status = status;
        this.id = id;
        this.task_title = task_title;
    }

      public Task(boolean status, int id) {
        this.status = status;
        this.id = id;
        
    }

}
