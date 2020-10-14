import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private LocalDateTime endDate;
    private String category;
    private String description;
    private String name;
    private Priority priority;
    private int points;
    private int id;

    public Task(LocalDateTime endDate, String category, String description, String name, Priority priority, int points, int id) {
        this.endDate = endDate;
        this.category = category;
        this.description = description;
        this.name = name;
        this.priority = priority;
        this.points = points;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Task{" +
                "endDate=" + endDate +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", points=" + points +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return points == task.points &&
                id == task.id &&
                Objects.equals(endDate, task.endDate) &&
                Objects.equals(category, task.category) &&
                Objects.equals(description, task.description) &&
                Objects.equals(name, task.name) &&
                priority == task.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, category, description, name, points, id);
    }
}
