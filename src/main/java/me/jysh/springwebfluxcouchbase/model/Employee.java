package me.jysh.springwebfluxcouchbase.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    private String id;

    @Field
    @NotBlank
    @Size(max = 30)
    private String firstName;

    @Field
    @NotBlank
    @Size(max = 30)
    private String lastName;
}
