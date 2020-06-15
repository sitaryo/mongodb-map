package com.sendroids.tech.mongodbmap.entiy;

import org.springframework.data.geo.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collection;

public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {

    // {"location" : {"$near" : [x,y], "$minDistance" : min, "$maxDistance" : max}}
    Collection<Hotel> findByPointNear(Point point, Distance min, Distance max);

    // {"location" : {"$near" : [x,y], "$maxDistance" : max}}
    Collection<Hotel> findByPointNear(Point point, Distance max);

    // {"location" : {"$near" : [x,y]}}
    GeoResults<Hotel> findByPointNear(Point point);

    Collection<Hotel> findByPointWithin(Circle circle);

    Collection<Hotel> findByPointWithin(Box box);

    Collection<Hotel> findByPointWithin(Polygon polygon);

//    GeoResults<Hotel> findByPointWithin(Box box);

}
