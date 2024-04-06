package com.blog.blog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class blogPost {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Write the post title")
    private String title;

    @NotBlank(message = "Write the post body")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generatedDate;

    public blogPost() {
        this.generatedDate = new Date();
    }
}
