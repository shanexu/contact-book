package io.shanexu.contactBook.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "person")
@Data
public class Person {

    @Id
    private String id;

    @Field(analyzer = "simple", type = FieldType.Text)
    private String firstName;

    @Field(analyzer = "simple", type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Keyword)
    private String mobile;

    @Field(type = FieldType.Keyword)
    private String homeEmail;

    @Field(type = FieldType.Keyword)
    private String officeEmail;

    @Field(type = FieldType.Keyword)
    private String homePage;

    @Field(analyzer = "simple", type = FieldType.Text)
    private String homeAddress;

    @Field(analyzer = "simple", type = FieldType.Text)
    private String officeAddress;

    @Field(analyzer = "simple", type = FieldType.Text)
    private String description;
}
