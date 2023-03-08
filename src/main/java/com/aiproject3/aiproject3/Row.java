package com.aiproject3.aiproject3;

public class Row {

    private int count;
    private double probability;
    private int direction;

    public Row(int count, double probability, int direction) {
        this.count = count;
        this.probability = probability;
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Row{" +
                "count=" + count +
                ", probability=" + probability +
                ", direction=" + direction +
                '}';
    }

}
