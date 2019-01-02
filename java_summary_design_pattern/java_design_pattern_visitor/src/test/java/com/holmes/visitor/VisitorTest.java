package com.holmes.visitor;

import org.junit.Test;

public class VisitorTest {

    @Test
    public void visitorTest() {

        Visitor chairmanVisitor = new ChairmanVisitor();
        Visitor generalManagerVisitor = new GeneralManagerVisitor();
        Visitor employeeVisitor = new EmployeeVisitor();

        Chairman chairman = new Chairman(new GeneralManager(), new Employee());
        GeneralManager generalManager = new GeneralManager(new Employee());
        Employee employee = new Employee();

        chairman.accept(chairmanVisitor);
        System.out.println();

        chairman.accept(generalManagerVisitor);
        System.out.println();

        generalManager.accept(generalManagerVisitor);
        System.out.println();

        generalManager.accept(employeeVisitor);
        System.out.println();

        employee.accept(employeeVisitor);
    }
}
