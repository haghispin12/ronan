package com.example.ronan;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class PolygonUtils {
      double VerticesSize;
    //Method to check if a point is inside a polygon
    public static boolean isPointInPolygon(LatLng point, List<LatLng> vertices){
        int intersectCount = 0;

        for(int i = 0;i<vertices.size();i++){
            LatLng v1= vertices.get(i);
            LatLng v2 = vertices.get((i+1)%vertices.size());
            //check if the point is on the vertex
            if(point.equals(v1)){
                return true;
            }
            //check if the point is on the boundary
            if(isPointOnBoundary(point,v1,v2)){
                return true;
            }
            if(((v1.latitude > point.latitude) != (v2.latitude > point.latitude))
                    && (point.longitude<(v2.longitude-v1.longitude)*(point.latitude-v1.latitude)
                    /(v2.latitude-v1.latitude) +v1.longitude)){
                intersectCount++;
            }
        }

        return (intersectCount % 2 ) == 1;
    }
    private static boolean isPointOnBoundary(LatLng point, LatLng v1, LatLng v2){
        if(v1.latitude == v2.latitude && v1.latitude == point.latitude
                && point.longitude>Math.min(v1.longitude,v2.longitude)
                && point.longitude<Math.max(v1.longitude,v2.longitude)){
            return true;
        }
        if(v1.longitude == v2.longitude && v1.longitude == point.longitude
                && point.latitude>Math.min(v1.latitude,v2.latitude)
                && point.latitude<Math.max(v1.latitude,v2.latitude)){
            return true;
        }
        return false;
    }
}
