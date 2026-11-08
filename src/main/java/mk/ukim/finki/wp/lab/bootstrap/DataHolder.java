package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;
    public static List<Instructor> instructors = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>(10);
        registrations = new ArrayList<>();
        instructors = new ArrayList<>();

        instructors.add(new Instructor("Ana", "Petrovska", "anapetrovska@gmail.com", "Cooking"));
        instructors.add(new Instructor("Marko", "Trajkovski", "markotrajkovski@gmail.com", "Drinks"));
        instructors.add(new Instructor("Elena", "Stojanova", "elenastojanova@gmail.com", "Art"));

        workshops.add(new Workshop("Paint and Wine", "Art", 1200.0, 15, instructors.get(2)));
        workshops.add(new Workshop("Cookie Decoration", "Cooking", 800.0, 12, instructors.get(0)));
        workshops.add(new Workshop("Candle Making", "Crafts", 1500.0, 8, instructors.get(2)));
        workshops.add(new Workshop("Cocktail Making", "Drinks", 1300.0, 10, instructors.get(1)));
        workshops.add(new Workshop("Sushi Rolling", "Cooking", 1600.0, 2, instructors.get(0)));
        workshops.add(new Workshop("Latte Art", "Drinks", 1000.0, 0, instructors.get(1)));
        workshops.add(new Workshop("Flower Arrangement", "Crafts", 1100.0, 6, instructors.get(2)));
        workshops.add(new Workshop("Portrait Photography", "Photography", 2000.0, 9, instructors.get(2)));
        workshops.add(new Workshop("Pottery for Beginners", "Crafts", 900.0, 5, instructors.get(2)));
        workshops.add(new Workshop("Woodworking Basics", "Crafts", 500.0, 4, instructors.get(2)));
    }
}
