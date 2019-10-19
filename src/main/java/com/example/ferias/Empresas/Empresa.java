package com.example.ferias.Empresas;

import java.util.List;

public class Empresa {
    private String name;
    private String stand;
    private List<String> comments_pre;
    private List<String> comments_post;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public List<String> getComments_pre() {
        return comments_pre;
    }

    public void setComments_pre(List<String> comments_pre) {
        this.comments_pre = comments_pre;
    }

    public List<String> getComments_post() {
        return comments_post;
    }

    public void setComments_post(List<String> comments_post) {
        this.comments_post = comments_post;
    }
}
