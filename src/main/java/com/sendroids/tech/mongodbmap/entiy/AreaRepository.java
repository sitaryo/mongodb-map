package com.sendroids.tech.mongodbmap.entiy;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;

// 复杂查询参照：https://docs.mongodb.com/manual/reference/operator/query-geospatial/
public interface AreaRepository extends MongoRepository<Area, String> {

    @Query("{ " +
            "     geometry: { " +
            "       $geoIntersects: { " +
            "          $geometry: { " +
            "             type: \"Point\" , " +
            "             coordinates: ?0 " +
            "          } " +
            "       } " +
            "     } " +
            "   }")
    Collection<Area> findByGeometryContainsPoint(Point point);
}
