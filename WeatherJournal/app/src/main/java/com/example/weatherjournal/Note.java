package com.example.weatherjournal;

public class Note {
    private long id;
    private String content;
    private String time;
    private int tag;

    public Note(){
    }

    public Note(String content, String time, int tag){
        this.content = content;
        this.time = time;
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", tag=" + tag +
                '}';
    }
}
