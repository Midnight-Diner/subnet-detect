package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "num", schema = "njuwlan")
public class Num {
    long time;
    Integer num;

    public Num(long time, Integer num) {
        this.time = time;
        this.num = num;
    }

    public Num() {

    }

    @Id
    @Column(name = "time")
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Num numEntity = (Num) o;
        return time == numEntity.time &&
                Objects.equals(num, numEntity.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, num);
    }
}
