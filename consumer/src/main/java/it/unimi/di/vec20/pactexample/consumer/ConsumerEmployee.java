package it.unimi.di.vec20.pactexample.consumer;

public class ConsumerEmployee {
  String completeName;
  String emailAddress;

  public ConsumerEmployee(String surname, String name, String emailAddress) {
    this.completeName = name + " " + surname;
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getCompleteName() {
    return completeName;
  }

  @Override
  public String toString() {
    return completeName + " email: " + emailAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof ConsumerEmployee)) return false;
    ConsumerEmployee e = (ConsumerEmployee) o;
    return (this.completeName.equals(e.completeName)) && (this.emailAddress.equals(e.emailAddress));
  }
}
