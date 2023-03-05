package com.scheduler.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@DynamoDBTable(tableName = "meeting")
@Getter
@Setter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class Meeting {

    @DynamoDBHashKey
    private String partitionKey;

    @DynamoDBAttribute
    private String startTime;

    @DynamoDBAttribute
    private String endTime;

    @DynamoDBAttribute
    private List<String> participants;

    @DynamoDBVersionAttribute
    private Long version;
}
