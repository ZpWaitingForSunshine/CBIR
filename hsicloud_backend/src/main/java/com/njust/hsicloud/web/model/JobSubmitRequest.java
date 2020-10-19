package com.njust.hsicloud.web.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSubmitRequest {

    private String action;

    private String appResource;

    private List<String> appArgs;

    private String clientSparkVersion;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAppResource() {
        return appResource;
    }

    public void setAppResource(String appResource) {
        this.appResource = appResource;
    }

    public List<String> getAppArgs() {
        return appArgs;
    }

    public void setAppArgs(List<String> appArgs) {
        this.appArgs = appArgs;
    }

    public String getClientSparkVersion() {
        return clientSparkVersion;
    }

    public void setClientSparkVersion(String clientSparkVersion) {
        this.clientSparkVersion = clientSparkVersion;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

//    public SparkProperties getSparkProperties() {
//        return sparkProperties;
//    }

//    public void setSparkProperties(SparkProperties sparkProperties) {
//        this.sparkProperties = sparkProperties;
//    }

    private String mainClass;

    private Map<String, String> environmentVariables;

    public Map<String, String> getSparkProperties() {
        return SparkProperties;
    }

    public void setSparkProperties(Map<String, String> sparkProperties) {
        SparkProperties = sparkProperties;
    }

    private Map<String, String> SparkProperties;

//    private SparkProperties sparkProperties;


//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    public static class SparkProperties {
//
//        @JsonProperty(value = "spark.jars")
//        private String jars;
//
//        @JsonProperty(value = "spark.app.name")
//        private String appName;
//
//        @JsonProperty(value = "spark.master")
//        private String master;
//
//        private Map<String, String> otherProperties = new HashMap<>();
//
//        public String getJars() {
//            return jars;
//        }
//
//        public void setJars(String jars) {
//            this.jars = jars;
//        }
//
//        public String getAppName() {
//            return appName;
//        }
//
//        public void setAppName(String appName) {
//            this.appName = appName;
//        }
//
//        public String getMaster() {
//            return master;
//        }
//
//        public void setMaster(String master) {
//            this.master = master;
//        }
//
//        public void setOtherProperties(Map<String, String> otherProperties) {
//            this.otherProperties = otherProperties;
//        }
//
//        public void setOtherProperties(String key, String value) {
//            this.otherProperties.put(key, value);
//        }
//
//        @JsonAnyGetter
//        Map<String, String> getOtherProperties() {
//            return this.otherProperties;
//        }
//
//    }
}

