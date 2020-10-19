package com.njust.hsicloud.core.assist.runc;

public class filterApplicationID {
    public String filterApplicationId(String line) {
        String applicationId = null;
        line = line.toLowerCase();

        // --deploy-mode client
        // 19/02/15 17:43:35 INFO impl.YarnClientImpl: Submitted application application_1548381669007_0051
        // 19/04/01 14:13:57 INFO impl.YarnClientImpl: Submitted application application_1548381669007_0781
        boolean isIndexSparkOwnLog = line.indexOf("INFO  StandaloneSchedulerBackend:54 - Connected to Spark cluster with app ID ".toLowerCase()) != -1;



        if (isIndexSparkOwnLog ) {

                int idx = line.indexOf("INFO  StandaloneSchedulerBackend:54 - Connected to Spark cluster with app ID ".toLowerCase());
                applicationId = line.substring(idx + "INFO  StandaloneSchedulerBackend:54 - Connected to Spark cluster with app ID ".length());

        }

        if (applicationId != null && applicationId.startsWith("app ID ")) {
            System.out.println("====================================Index of applicationId:" + applicationId);
            System.out.println("====================================Index of applicationId:Complete ...");
        }

        return applicationId;
    }
}

