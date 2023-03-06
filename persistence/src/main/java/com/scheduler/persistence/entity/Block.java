package com.scheduler.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;

@DynamoDBTable(tableName = "block")
@Getter
@Setter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
public class Block {

    @DynamoDBHashKey
    private String partitionKey; //UserId_date

    @DynamoDBRangeKey
    private int sortKey; //time rank

    @DynamoDBAttribute
    private int allocation;

    @DynamoDBVersionAttribute
    private Long version;
}
