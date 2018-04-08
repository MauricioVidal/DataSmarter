package br.com.vidal.datasmarter.model;

/**
 * Created by mauricio on 4/8/18.
 */

public class Coordenada {

    private float lat, lng;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordenada cordenada = (Coordenada) o;

        if (Float.compare(cordenada.lat, lat) != 0) return false;
        return Float.compare(cordenada.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        int result = (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        result = 31 * result + (lng != +0.0f ? Float.floatToIntBits(lng) : 0);
        return result;
    }
}
