package com.holmes.spring.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 64)
    private String name;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * 一对一关联
     */
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    /**
     * 多对一关联
     */
    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private Class clazz;

    /**
     * 一对多关联
     */
    @OneToMany
    @JoinColumn(name = "STUDENT_ID")
    private Set<Book> books = new HashSet<>();

    /**
     * 多对多关联
     * student_course: Student与Course关联的中间表
     */
    @ManyToMany
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"))
    private Set<Course> courses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", address=" + address +
                ", clazz=" + clazz +
                ", books=" + books +
                '}';
    }
}
