package com.scheduler.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@DynamoDBTable(tableName = "meeting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class Meeting {

    @DynamoDBHashKey
    private String partitionKey; //Meeting id or user id_date

    @DynamoDBRangeKey
    private String sortKey; //Meeting id or startTime_endTime of meeting

    @DynamoDBAttribute
    private String startTime;

    @DynamoDBAttribute
    private String endTime;

    @DynamoDBAttribute
    private String timeZone;

    @DynamoDBAttribute
    private List<String> participants;

    @DynamoDBVersionAttribute
    private Long version;
}
