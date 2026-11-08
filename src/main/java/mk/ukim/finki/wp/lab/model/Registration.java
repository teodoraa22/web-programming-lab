package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Registration {
    public String workshopTitle;
    public String participantName;
    public String participantEmail;
    public Integer numberOfSeats;
}
