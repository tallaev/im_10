package ru.vorotov.simulationslab10;

public class Team {
    private final String name;
    private final double lambda;

    public Team(String name, double lambda) {
        this.name = name;
        this.lambda = lambda;
    }

    public String getName() {
        return name;
    }

    public double getLambda() {
        return lambda;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", lambda=" + lambda +
                '}';
    }
}
