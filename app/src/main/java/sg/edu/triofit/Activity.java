package sg.edu.triofit;

public class Activity {

    private String imageURL;
    private String name;

    public Activity() {
    }

    public Activity(String imageURL, String name) {
        this.imageURL = imageURL;
        this.name = name;
    }

    public  String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
