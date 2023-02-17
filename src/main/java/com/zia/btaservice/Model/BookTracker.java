package com.zia.btaservice.Model;
import java.sql.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="bta-books")
@Getter
public class BookTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Setter
    private String author;
    @Setter
    @Column(columnDefinition = "date")
    private Date publish_date;
    @Setter
    @Column(columnDefinition = "date")
    private Date reading_start_date;
    @Setter
    @Column(columnDefinition = "date")
    private Date reading_end_date;
    @Setter
    private int total_pages;
    @Setter
    private String feedback; 

    public BookTracker(){}
    public BookTracker(String title, String author, Date publish_date, Date reading_start_date, Date reading_end_date, int total_pages, String feedback){
        this.title = title;
        this.author = author;
        this.publish_date = publish_date;
        this.reading_start_date = reading_start_date;
        this.reading_end_date = reading_end_date;
        this.total_pages = total_pages;
        this.feedback = feedback;
    }
    public BookTracker(Long id, String title, String author, Date publish_date, Date reading_start_date, Date reading_end_date, int total_pages, String feedback){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publish_date = publish_date;
        this.reading_start_date = reading_start_date;
        this.reading_end_date = reading_end_date;
        this.total_pages = total_pages;
        this.feedback = feedback;
    }
}
