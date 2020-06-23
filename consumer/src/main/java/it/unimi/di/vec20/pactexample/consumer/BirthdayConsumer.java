package it.unimi.di.vec20.pactexample.consumer;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BirthdayConsumer {

/*
    private EmployeeRepository employeeRepository;
    private EmailService emailService;

    public BirthdayService(EmployeeRepository employeeRepository, EmailService emailService) {
        this.emailService = emailService;
        this.employeeRepository = employeeRepository;
    }

    public void sendGreetings(LocalDate today) {
        List<Employee> borns =
                employeeRepository.findEmployeesBornOn(today.getMonthValue(), today.getDayOfMonth());

        if (!borns.isEmpty()) {
            for (Employee e : borns) {
                try {
                    emailService.sendEmail(
                            e.getEmailAddress(), "Happy birthday!", "Happy birthday, dear " + e.getName() + "!");
                } catch (Exception ex) {
                    System.err.println("Email not sent for employee:" + e.toString());
                }
            }
        }
    }
*/
    public static void main(String[] args) throws UnirestException {
        int day;
        int month;
        if (args.length > 1) {
            day = Integer.parseInt(args[0]);
            month = Integer.parseInt(args[1]);
        }
        else{
            day = LocalDateTime.now().getDayOfMonth();
            month = LocalDateTime.now().getMonthValue();
        }
        System.out.println(new Client("http://localhost:8080")
                .fetchAndProcessData(month, day));
    }

}
