package com.njust.hsicloud.web.model;

import org.json4s.jackson.Json;

public class DagConponent {
    private  String name;
    private String dag_json;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDag_json() {
        return dag_json;
    }

    public void setDag_json(String dag_json) {
        this.dag_json = dag_json;
    }
}
