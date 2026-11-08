package mk.ukim.finki.wp.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.repository.RegistrationRepository;
import mk.ukim.finki.wp.lab.repository.WorkshopRepository;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final WorkshopRepository workshopRepository;
    private final RegistrationRepository registrationRepository;

    @Override
    public Registration register(String workshopTitle, String participantName, String participantEmail, int numberOfSeats) {
        if (workshopTitle == null || workshopTitle.isEmpty() || participantName == null || participantName.isEmpty()
                || participantEmail == null || participantEmail.isEmpty()
                || numberOfSeats < 1) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        Workshop workshop = workshopRepository.findByTitle(workshopTitle)
                .orElseThrow(() -> new IllegalArgumentException("Workshop not found"));

        if (numberOfSeats > workshop.getCapacity()) {
            throw new IllegalArgumentException("Not enough free seats");
        }

        workshop.setCapacity(workshop.getCapacity() - numberOfSeats);

        Registration registration = new Registration(workshopTitle, participantName, participantEmail, numberOfSeats);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Registration> listAll() {
        return registrationRepository.findAll();
    }
}
