package it.unimi.di.vec20.pactexample.provider;

public class Employee {
  String surname;
  String name;
  int year;
  int month;
  int day;
  String emailAddress;

  public Employee(String surname, String name, int y, int m, int d, String emailAddress) {
    this.surname = surname;
    this.name = name;
    this.year = y;
    this.month = m;
    this.day = d;
    this.emailAddress = emailAddress;
  }

  boolean checkIfBirthday(int month, int day) {
    return (month == this.month && day == this.day);
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name + " " + surname + " born on: " + year + " " + month + " " + day;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Employee)) return false;
    Employee e = (Employee) o;
    return (this.surname.equals(e.surname))
        && (this.name.equals(e.name))
        && (this.year == e.year)
        && (this.month == e.month)
        && (this.day == e.day)
        && (this.emailAddress.equals(e.emailAddress));
  }
}
