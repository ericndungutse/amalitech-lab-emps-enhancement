package org.ndungutse.ems;

import java.util.logging.Logger;
import org.ndungutse.ems.repository.EmployeeCollection;

public class AppContext {
    public static final Logger logger = Logger
            .getLogger(AppContext.class.getName());
    private final static EmployeeCollection<String> employeeCollection = new EmployeeCollection<>();

    public static EmployeeCollection<String> getEmployeeCollection() {
        return employeeCollection;
    }
}
