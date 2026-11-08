package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public String register(@RequestParam(required = false) String workshopTitle,
                           @RequestParam(required = false) String participantName,
                           @RequestParam(required = false) String participantEmail,
                           @RequestParam(required = false) Integer numSeats,
                           @RequestHeader(value = "User-Agent", required = false) String userAgent,
                           HttpServletRequest request,
                           Model model) {
        try {
            Registration registration = registrationService
                    .register(workshopTitle, participantName, participantEmail, numSeats == null ? 0 : numSeats);

            model.addAttribute("registration", registration);
            model.addAttribute("ipAddress", request.getRemoteAddr());
            model.addAttribute("userAgent", userAgent);
            return "registrationConfirmation";

        } catch (IllegalArgumentException e) {
            String message = URLEncoder.encode(String.valueOf(e.getMessage()), StandardCharsets.UTF_8);
            return "redirect:/workshops?errorMessage=" + message;
        }
    }

    @GetMapping("/registrations")
    public String getRegistrationsPage(Model model) {
        model.addAttribute("registrations", registrationService.listAll());
        return "listRegistrations";
    }
}
