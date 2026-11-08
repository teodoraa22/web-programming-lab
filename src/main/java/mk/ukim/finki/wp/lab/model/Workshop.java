package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;

@Data
@AllArgsConstructor
public class Workshop {
    private Long id;
    public String title;
    public String category;
    public Double price;
    public Integer capacity;
    private Instructor instructor;

    public Workshop(String title, String category, Double price, Integer capacity, Instructor instructor) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.category = category;
        this.price = price;
        this.capacity = capacity;
        this.instructor = instructor;
    }
}
