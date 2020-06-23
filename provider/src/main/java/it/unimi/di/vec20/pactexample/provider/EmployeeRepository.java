package it.unimi.di.vec20.pactexample.provider;

import java.util.List;

public interface EmployeeRepository {
  List<Employee> findEmployeesBornOn(int month, int day);
}
