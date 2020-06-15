package com.sendroids.tech.mongodbmap;

import com.sendroids.tech.mongodbmap.entiy.Hotel;
import com.sendroids.tech.mongodbmap.entiy.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;

@SpringBootTest
@Slf4j
public class PointTest {

    @Autowired
    HotelRepository hotelRepository;

    // docs https://docs.spring.io/spring-data/mongodb/docs/3.0.1.RELEASE/reference/html/#mongo.repositories
    @Test
    public void test() {
        var point = new Point(3, 3);
        var min = new Distance(1);
        var max = new Distance(10);

        this.nearWithDistance(point);
        this.near(point, max);
        this.near(point, min, max);


        var circle1 = new Circle(point, max);
        var circle2 = new Circle(point, min);

        this.withIn(circle1);
        this.withIn(circle2);

        var box = new Box(new Point(2, 2), new Point(5, 5));
        this.withIn(box);

        var polygon = new Polygon(
                new Point(0, 0),
                new Point(0, 9),
                new Point(9, 9),
                new Point(9, 0),
                new Point(0, 0)
        );

        this.withInPolygon(polygon);
    }

    @Test
    public void createTestData() {
        this.createHotel();
    }

    private void createHotel() {
        var hotel1 = new Hotel();
        hotel1.setName("test hotel");
        hotel1.setPoint(new Point(2, 2));

        hotelRepository.save(hotel1);

        var hotel2 = new Hotel();
        hotel2.setName("test hotel");
        hotel2.setPoint(new Point(1, 10));

        hotelRepository.save(hotel2);

        var hotel3 = new Hotel();
        hotel3.setName("test hotel");
        hotel3.setPoint(new Point(20, 7));

        hotelRepository.save(hotel3);
    }

    private void nearWithDistance(Point point) {

        log.info("the hotel near {}", point);
        hotelRepository.findByPointNear(point).forEach(hotel -> log.info(hotel + ""));
    }

    private void near(Point point, Distance max) {
        log.info("the hotel near {} and max distance {}", point, max);

        hotelRepository.findByPointNear(point, max).forEach(hotel -> log.info(hotel + ""));
    }

    private void near(Point point, Distance min, Distance max) {
        log.info("the hotel near {} and between distance {} - {}", point, min, max);
        hotelRepository.findByPointNear(point, min, max).forEach(hotel -> log.info(hotel + ""));
    }

    private void withIn(Circle circle) {
        log.info("the hotel with in {}", circle);
        hotelRepository.findByPointWithin(circle).forEach(hotel -> log.info(hotel + ""));
    }

    private void withIn(Box box) {
        log.info("the hotel with in {}", box);
        hotelRepository.findByPointWithin(box)
                .forEach(hotel -> log.info(hotel + ""));
    }

    private void withInPolygon(Polygon polygon){
        log.info("the hotel with in polygon {}",polygon);
        hotelRepository.findByPointWithin(polygon)
                .forEach(hotel -> log.info(hotel + ""));
    }

}
