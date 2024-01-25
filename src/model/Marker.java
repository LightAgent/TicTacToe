package model;

public class Marker {
    private MarkerType markerType;
    public Marker(){
        markerType = MarkerType.EMPTY;
    }
    public Marker(MarkerType markerType){
        this.markerType = markerType;
    }
    public MarkerType getMarkerType() {
        return markerType;
    }
    public void setMarkerType(MarkerType markerType) {
        this.markerType = markerType;
    }
    
}
