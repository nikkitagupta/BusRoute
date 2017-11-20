package in.nikita.busroute.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nikita Gupta on 17-11-2017.
 */

public class RouteModel implements Parcelable{
    String id;
    String name;
    String stops;
    String description;
    String accessible;
    String image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(stops);
        parcel.writeString(description);
        parcel.writeString(accessible);
        parcel.writeString(image);
    }

    public static final Parcelable.Creator<RouteModel> CREATOR = new Parcelable.Creator() {
        public RouteModel createFromParcel(Parcel in ) {
            return new RouteModel( in );
        }

        public RouteModel[] newArray(int size) {
            return new RouteModel[size];
        }
    };

    public RouteModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        stops = in.readString();
        description = in.readString();
        accessible = in.readString();
        image = in.readString();
    }

    public RouteModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
