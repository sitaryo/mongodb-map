package com.sendroids.tech.mongodbmap.entiy;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Data
@Document("hotel")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {

    @Id
    String id;

    String name;

    // 当是地图时，替换为 GEO_2DSPHERE
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D)
    Point point;

}
