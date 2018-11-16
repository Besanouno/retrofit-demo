package basistaikwasnik.pl.retrofitdemo;

public class TaskDto {

    public TaskDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TaskDto(String title, String description, String uuid) {
        this.title = title;
        this.description = description;
        this.uuid = uuid;
    }

    private String title;
    private String description;
    private String uuid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
