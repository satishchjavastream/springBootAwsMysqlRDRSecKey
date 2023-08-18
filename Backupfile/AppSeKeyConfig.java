package com.springbootcrudexample.config;



import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.profiles.ProfileFile;
import software.amazon.awssdk.profiles.ProfileFileSupplier;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.*;

import javax.sql.DataSource;

@Configuration
public class AppSeKeyConfig {

    private Gson gson = new Gson();

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String awsAccessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String awsSecretKey;

    @Bean
    public DataSource dataSource() {
        AwsSecrets secrets = getSecret();
        return DataSourceBuilder
                .create()
                //  .driverClassName("com.mysql.cj.jdbc.driver")
                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/springbootcurd")
                .username(secrets.getUsername())
                .password(secrets.getPassword())
                .build();

    }

    private AwsSecrets getSecret(){

        String secretName = "curdMysqlseKey";
        Region region = Region.of("us-east-1");
        String secret;

        ProfileCredentialsProvider provider = ProfileCredentialsProvider
                .builder()
                .profileFile(ProfileFileSupplier.defaultSupplier())
                .build();

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(provider)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        GetSecretValueResponse getSecretValueResponse;
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
           throw e;
        }

        if (getSecretValueResponse.secretString() != null) {
            secret = getSecretValueResponse.secretString();
            System.out.println("secret :"+secret);
            return gson.fromJson(secret, AwsSecrets.class);
        }

        return null;
    }


}
