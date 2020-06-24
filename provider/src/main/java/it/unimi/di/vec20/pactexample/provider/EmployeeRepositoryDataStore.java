package it.unimi.di.vec20.pactexample.provider;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryDataStore implements EmployeeRepository {
    public static final EmployeeRepositoryDataStore INSTANCE = new EmployeeRepositoryDataStore();

    private EmployeeRepositoryDataStore () {}

    private final ArrayList<Employee> sampleList = new ArrayList<>(List.of(new Employee("doe", "john", 1985, 6, 23, "john.doe@foobar.com")));

    @Override
    public List<Employee> findEmployeesBornOn(int month, int day) {
        return sampleList;
    }

}
