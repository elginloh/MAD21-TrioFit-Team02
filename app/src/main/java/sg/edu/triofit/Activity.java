package sg.edu.triofit;

public class Activity {
    private String Name;
    private String Image;
    private String Video;

    public Activity() {
    }

    public Activity(String name, String image, String video) {
        Name = name;
        Image = image;
        Video = video;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }
}
