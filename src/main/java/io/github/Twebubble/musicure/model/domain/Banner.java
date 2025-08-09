package io.github.Twebubble.musicure.model.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class Banner implements Serializable {
    private Integer id;

    private String pic;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}