package ru.job4j;

public class Candidate extends User {

    public void post(String resume) {
        System.out.println(resume);
    }

    public void respond(String job) {
        System.out.println(job);
    }
}
