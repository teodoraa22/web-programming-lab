package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.service.RegistrationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/servlet/registration")
public class RegistrationServlet extends HttpServlet {
    private final RegistrationService registrationService;
    private final SpringTemplateEngine springTemplateEngine;

    public RegistrationServlet (RegistrationService registrationService, SpringTemplateEngine springTemplateEngine) {
        this.registrationService = registrationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Registration> registrations = registrationService.listAll();

        context.setVariable("registrations", registrations);
        springTemplateEngine.process("listRegistrations.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workshopTitle = req.getParameter("workshopTitle");
        String participantName = req.getParameter("participantName");
        String participantEmail = req.getParameter("participantEmail");
        int numberOfSeats = parseSeats(req.getParameter("numSeats"));

        try {
            Registration registration = registrationService.register(workshopTitle, participantName, participantEmail, numberOfSeats);

            IWebExchange webExchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);
            WebContext context = new WebContext(webExchange);

            context.setVariable("registration", registration);
            context.setVariable("ipAddress", req.getRemoteAddr());
            context.setVariable("userAgent", req.getHeader("User-Agent"));

            springTemplateEngine.process("registrationConfirmation.html", context, resp.getWriter());
        } catch (IllegalArgumentException e) {
            String message = URLEncoder.encode(String.valueOf(e.getMessage()), StandardCharsets.UTF_8);
            resp.sendRedirect("/servlet/workshops?errorMessage=" + message);
        }
    }

    private int parseSeats(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
