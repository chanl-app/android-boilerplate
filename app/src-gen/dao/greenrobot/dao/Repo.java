package dao.greenrobot.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "REPO".
 */
public class Repo {

    private Long id;
    private String name;
    private String description;
    private String html_url;

    public Repo() {
    }

    public Repo(Long id) {
        this.id = id;
    }

    public Repo(Long id, String name, String description, String html_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.html_url = html_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

}
