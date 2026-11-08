package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Instructor;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.model.exeptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab.repository.InstructorRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkshopServiceImpl implements WorkshopService {
    private final WorkshopRepository workshopRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<Workshop> listAll() {
        return workshopRepository.findAll();
    }

    @Override
    public List<Workshop> search(String text, Double maxPrice) {
        return workshopRepository.search(text, maxPrice);
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return workshopRepository.findById(id);
    }

    @Override
    public Workshop create(String title, String category, Double price, Integer capacity, Long instructorId) {
        validate(title, category, price, capacity);
        Instructor instructor = findInstructor(instructorId);

        Workshop workshop = new Workshop(title, category, price, capacity, instructor);
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop update(Long id, String title, String category, Double price, Integer capacity, Long instructorId) {
        validate(title, category, price, capacity);
        Instructor instructor = findInstructor(instructorId);

        Workshop workshop = workshopRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("Workshop not found"));

        workshop.setTitle(title);
        workshop.setCategory(category);
        workshop.setPrice(price);
        workshop.setCapacity(capacity);
        workshop.setInstructor(instructor);
        return workshopRepository.save(workshop);
    }


    @Override
    public void deleteById(Long id) {
        workshopRepository.deleteById(id);
    }

    private void validate (String title, String category, Double price, Integer capacity) {
        if (title == null || title.isEmpty()
                || category == null || category.isEmpty()
                || price == null || price < 0
                || capacity == null || capacity < 0) {
            throw new InvalidArgumentException("Invalid workshop data");
        }
    }

    private Instructor findInstructor(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("Instructor not found"));
    }
}
