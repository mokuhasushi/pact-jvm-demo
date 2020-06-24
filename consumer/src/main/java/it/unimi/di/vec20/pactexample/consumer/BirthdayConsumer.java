package it.unimi.di.vec20.pactexample.consumer;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.time.LocalDateTime;

public class BirthdayConsumer {

  public static void main(String[] args) throws UnirestException {
    int day;
    int month;
    if (args.length > 1) {
      day = Integer.parseInt(args[0]);
      month = Integer.parseInt(args[1]);
    } else {
      day = LocalDateTime.now().getDayOfMonth();
      month = LocalDateTime.now().getMonthValue();
    }
     ConsumerEmployee e = new Client("http://localhost:8080").fetchAndProcessData(month, day).get(0);
    System.out.println("Happy birthday, dear " + e.getCompleteName() + "!");
  }
}
