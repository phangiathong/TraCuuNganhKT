package com.academy.tracuunganhkt;

public class Economics {

    private String id;
    private int level;
    private String name;
    private String content;

    public Economics(String id, int level, String name) {
        this.id = id;
        this.level = level;
        this.name = name;
    }

    public Economics(String id, int level, String name, String content) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
