package sg.edu.triofit;

import java.util.ArrayList;

public class Activity {
    private String Name;
    private String Image;
    private String Video;
    private  ArrayList<Activity> activities = new ArrayList();

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public Activity() {
    }



    public Activity(String name, String image, String video, ArrayList<Activity> activities) {
        Name = name;
        Image = image;
        Video = video;
        this.activities = activities;
        for(int i=0; i < activities.size(); i ++)
        {
            this.activities.add(activities.get(i));
        }
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
