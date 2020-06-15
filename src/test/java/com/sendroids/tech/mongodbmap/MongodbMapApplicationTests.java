package com.sendroids.tech.mongodbmap;

import com.sendroids.tech.mongodbmap.entiy.Area;
import com.sendroids.tech.mongodbmap.entiy.AreaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class MongodbMapApplicationTests {

    @Autowired
    AreaRepository areaRepository;

    @Test
    void contextLoads() {
        find();
    }

    @Test
    public void insertData(){
        insert();
    }

    private void find() {

        var point = new Point(3,1);
        var areas = areaRepository.findByGeometryContainsPoint(point);
        areas.forEach(System.out::println);
    }


    private void insert() {


        var points = IntStream.range(0, 5)
                .mapToObj((i) -> new Point(i + 0D, 0D))
                .collect(Collectors.toList());
        IntStream.range(0, 5)
                .forEach((i) -> points.add(new Point(5D, 0D + i)));
        points.add(new Point(0D, 0D));


        var geo = new GeoJsonPolygon(points);

        var area = new Area();
        area.setName("三角形");
        area.setGeometry(geo);

        areaRepository.save(area);
    }

}
