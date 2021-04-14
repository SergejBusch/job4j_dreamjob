package ru.job4j;

public class Recruiter extends User {

    public void post(String job) {
        System.out.println(job);
    }

    public void invite(Candidate candidate, String job) {
        System.out.println(candidate + " " + job);
    }
}
