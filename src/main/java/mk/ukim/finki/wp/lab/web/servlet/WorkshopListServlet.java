package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@Component
@WebServlet(name = "WorkshopServlet", urlPatterns = "/servlet/workshops")
public class WorkshopListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final WorkshopService workshopService;

    public WorkshopListServlet(SpringTemplateEngine springTemplateEngine, WorkshopService workshopService) {
        this.springTemplateEngine = springTemplateEngine;
        this.workshopService = workshopService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String text = req.getParameter("text");
        if (text != null && text.isBlank()) {
            text = null;
        }

        String maxPriceParam = req.getParameter("maxPrice");
        Double maxPrice = null;
        if (maxPriceParam != null && !maxPriceParam.isBlank()) {
            try {
                maxPrice = Double.parseDouble(maxPriceParam);
            } catch (NumberFormatException e) {
                maxPrice = null;
            }
        }

        List<Workshop> workshops;

        if (text != null || maxPrice != null) {
            workshops = workshopService.search(text, maxPrice);
        } else {
            workshops = workshopService.listAll();
        }

        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));
        springTemplateEngine.process("listWorkshops.html", context, resp.getWriter());
    }
}
