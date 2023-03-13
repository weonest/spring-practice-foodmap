package naver.map.domain;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Map {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", des='" + des + '\'' +
                ", star=" + star +
                ", sum='" + sum + '\'' +
                ", camp=" + camp +
                '}';
    }

    private String name;
    private double x;
    private double y;
    private String des;
    private double star;
    private String sum;
    private int camp;

}
