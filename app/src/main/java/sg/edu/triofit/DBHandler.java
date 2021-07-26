package sg.edu.triofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "accountsDB.db";
    public static String ACCOUNTS = "Accounts";
    public static String COLUMN_USERNAME = "Username";
    public static String COLUMN_PASSWORD = "Password";
    public static String COLUMN_EMAIL = "Email";
    public static String COLUMN_DATE = "DOB";

    public static String CATEGORY = "Category";
    public static String COLUMN_TITLE = "Category Title";
    public static String COLUMN_NAME = "Activity Name";

    public static String CATEIMAGE = "CateImage";
    public static String COLUMN_IMAGE = "Image";

    public static String CATEVIDEO = "CateVideo";
    public static String COLUMN_VIDEO = "Video";

    public static String CATEDESC = "CateDesc";
    public static String COLUMN_DESC = "Description";



    public static int DATABASE_VERSION = 29;

    public  DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_DATE + " TEXT"+ ")";
        //CREATE TABLE Accounts(Username TEXT, Password TEXT, Email TEXT, Date TEXT)

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY + "(" + COLUMN_TITLE + "TEXT," + COLUMN_NAME + "TEXT"  +  ")";

        String CREATE_CATEIMAGE_TABLE = "CREATE TABLE " + CATEIMAGE + "(" + COLUMN_IMAGE + "TEXT" + ")";

        String CREATE_CATEVIDEO_TABLE = "CREATE TABLE " + CATEVIDEO + "(" + COLUMN_VIDEO + "TEXT" + ")";

        String CREATE_CATEDESC_TABLE = "CREATE TABLE " + CATEDESC + "(" + COLUMN_DESC + "TEXT" + ")";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_CATEIMAGE_TABLE);
        db.execSQL(CREATE_CATEVIDEO_TABLE);
        db.execSQL(CREATE_CATEDESC_TABLE);

        String varName1 = ""
                + "INSERT INTO \"Category\" VALUES ('Workout Guide','Biceps;Triceps;Abdominals;Upper Chest;Lower Chest;Quadriceps;Back;Hamstrings;Calf;');";
        String varName2 = ""
                + "INSERT INTO \"Category\" VALUES ('Running Guide','Hill Run;Recovery Routine;Running posture;Breathing Method;Marathon;Hydration;Mental Tips;Run Schedule; Self Massage;');";
        String varName3 = ""
                + "INSERT INTO \"Category\" VALUES ('Yoga Guide','Beginner;Runner;Tired Legs;Neck & Shoulder;Knee;Lower Back;Hip;Hamstring;Muscle Recovery;Deep Core;');";
        String varName4 = ""
                + "INSERT INTO \"Category\" VALUES ('Nutrition Guide','Carbohydrates;Protein;Fats;Fluids;Salt;Fruits & Vegetables;');";

        String varName5 = ""
                + "INSERT INTO \"CateImage\" VALUES ('biceps;triceps;abs;upperchest;lowerchest;quadriceps;back;hamstrings;calf;');";
        String varName6 = ""
                + "INSERT INTO \"CateImage\" VALUES ('hillrun;recoveryroutine;runningposture;breathingmethod;marathon;hydration;mentaltips;runschedule;selfmassage;');";
        String varName7 = ""
                + "INSERT INTO \"CateImage\" VALUES ('beginner;runner;tiredlegs;neckshoulder;knee;lowerback;hip;hamstring;musclerecovery;deepcore');";
        String varName8 = ""
                + "INSERT INTO \"CateImage\" VALUES ('carbohydrates;protein;fats;fluids;salt;fruitvegetable;');";
        String varName9 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('KOCPftPnAF0;KzBZ02EAJvE;zzD80vCLq0Y;EHR3Rl26-4A;BKyvTYuxWZE;o5fXYaJ3owA;ClzTDsQDC0E;zOPTihGAito;eUxNn79WYkA');";
        String varName10 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('bRjT1asH1kw;zbayPYgUWUw;brFHyOtTwH4;MtvBzgeUrZ8;C7NnlF0-p60;4cOvJdy7CQ0;TVXcGi3St7g;thLQOqsVp30;mxHw9tYdyA4');";
        String varName11 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('v7AYKMP6rOE;plL13JF5BHA;2137wAXvufE;SedzswEwpPw;VfSlEgg4ApE;XeXz8fIZDCE;FhcZuQRC-mI;-Mirm7LKvKk;YLSUfYjpltU;BPobdbmzY9o');";
        String varName12 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('eoUC90KpSpQ;VSCpOuLzTmc;EEkfzDmD3-4;IPyYDaTUGt8;ILgvFqurKUM;G7e22--1wtk;');";

        String varName13 = ""
                + "INSERT INTO \"CateDesc\" VALUES ('This home workout video mainly focuses on biceps where only dumbbells are needed. It consists of 6 different sets of exercise: Biceps Curls, Hammer Curls, Drag Curls, Zottman Curls, Waiter’s Curls, Concentration Curls.; This home workout video mainly focuses on triceps where only dumbbells are needed. It consists of 5 different sets of exercise: Kickbacks, Overhead Extension, Tricep Press, Skull Crushers, Chest Press.;This home workout video mainly focuses on abdominals where mat is needed. It consists of 7 different sets of exercise:Ab Halos, Frog Crunches, Russian Rows, Thread the Needle, Black Widow Knee Slides, Frog Circles, Sliding Ab Tracks.;This home workout video mainly focuses on the upper chest where no gym equipment is needed. It consists of 5 different sets of exercise:Close Grip Push-Ups, Wide Reverse Grip Push-Ups, Dip Push-Ups, Inner Chest Push-Ups, Sliding Chest Flys.;This home workout video mainly focuses on the lower chest where dumbbells are required. It consists of 6 different sets of exercise:Decline Floor Press, Close Grip Decline Press, Decline Floor Flys, Reverse Decline Floor Press, Decline Flys x Close Grip Press, Close Floor Press.;This home workout video mainly focuses on the quadriceps muscles where dumbbells and mat are required. It consists of 3 different sets of exercise:Squats, Lunges, Planking.;This home workout video mainly focuses on the back muscles where dumbbells are required. It consists of 5 different sets of exercise:Renegade Rows, Bent Over Rows, Deadlift Bent Over Rows, Behind The Back Shrugs, Reverse Flyes.;This home workout video mainly focuses on the hamstrings muscles where dumbbells ,mat and bench are required. It consists of 6 different sets of exercise:Squats, Deadlifts, Lunges, Kneeling Glute Squeeze, Hip Thrust, Leg Thrust. Be fully warmed up before doing these exercises.;This home workout video mainly focuses on the calf muscles where jumping rope is required.;');";

        String varName14 = ""
                + "INSERT INTO \"CateDesc\" VALUES ('This video is about hill running where it teaches tips and techniques on hill running.;This video gives post-run stretches and exercises to help you reduce your chance of injury and feel better the next time you head out for a run. This simple routine is perfect for after a run and designed to help maximise your recovery and improve your running performance.;This video is for runners who want to learn about running posture, which plays a big part in running.Having correct running posture helps to run effectively.;This video is for runners who want to learn about breathing methods, which plays a big part in running.Having correct breathing methods helps to run effectively.;This video explains how a marathon works and gives examples of training for beginner runners that are preparing for their  first marathon.;This video gives hydration tips for runners to allow them to run more effectively.;This video gives 4 mental tips on long runs or races for runners as having the correct mindset can help runners to run more effectively.;The video teaches runners how to plan their schedule and running structure throughout their training week to gain the most from it.;This video is demonstration of a basic runner self massage (rolling) routine as a preparation for strengthening or running exercises.;');";

        String varName15 = ""
                + "INSERT INTO \"CateDesc\" VALUES ('This is a yoga workout video for beginners that helps to build their foundation of yoga practices. This practice can be carried out at home and it is a 20 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video for runners that support both mind and body for a healthy and sustainable running or jogging routine.This practice can be carried out at home and it is a 20 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video for people who have tired legs after standing the whole day or after a post-run.These yoga poses will relieve the tension in the leg muscle.This practice can be carried out at home and it is a 26 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video for people who feel any tension in their neck or shoulder. This video will guide them how to release the tensions through the practices.This practice can be carried out at home and it is under 20 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video for people who have sensitive knees.These yoga poses will build strength and stability for knees without putting any pressure on the knees.This practice can be carried out at home and it is a 30 minutes workout. It is optional to have a mat for this workout.;This is a yoga sequence video for people who have lower back pain.Doing this practice regularly will help to relieve the lower back pain.This practice can be carried out at home and it is a 15 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video that focuses on hip flexibility and mobility. It only consists of 5 poses which are easy for anyone to follow. This practice can be carried out at home and it is a 14 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video that focuses on hamstrings flexibility and creating a full body stretch experience.In this video, it can help to increase the flexibility and stability in the back of the legs and stretch the full body.This practice can be carried out at home and it is a 14 minutes workout. It is optional to have a mat and required to have a resistance band for this workout.;This is a yoga workout video that deeply stretches shoulders,hips,quads and hamstrings to relieve sore muscles and increase flexibility.This practice can be carried out at home and it is a 26 minutes workout. It is optional to have a mat for this workout.;This is a yoga workout video that focuses on the core area of the body, it builds strength as well as increasing flexibility and blood flow. This workout helps to tone the abdominal wall and increase strength in the deeper core muscles, helping to create that foundation. This practice can be carried out at home and it is a 21 minutes workout. It is optional to have a mat for this workout.;');";

        String varName16 = ""
                + "INSERT INTO \"CateDesc\" VALUES ('This video tells you how many carbs you should be eating per day to lose weight. Reducing the amount of carbohydrates you eat is one of the best ways to lose weight. It tends to reduce your hunger and cause automatic fat loss, without you having to count calories or control portions.;This video helps you in breaking down a simple calculation that you can use to help determine how much protein you really need to achieve your weight loss or fitness goals.;This video guides you on how much fat should someone eat on a keto diet? Well, it depends! It is not as easy as a certain percent or certain number of grams. This video will help you to understand easily.;This video tell you the amount of water your body needs and the factors like health, physical activity, environment, and more that affect it.;This video tells you how much sodium you should really eat per day, and facts about salt.;This video tells you how many fruits and vegetables to eat per day.;');";


        db.execSQL(varName1);
        db.execSQL(varName2);
        db.execSQL(varName3);
        db.execSQL(varName4);
        db.execSQL(varName5);
        db.execSQL(varName6);
        db.execSQL(varName7);
        db.execSQL(varName8);
        db.execSQL(varName9);
        db.execSQL(varName10);
        db.execSQL(varName11);
        db.execSQL(varName12);
        db.execSQL(varName13);
        db.execSQL(varName14);
        db.execSQL(varName15);
        db.execSQL(varName16);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + CATEIMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEVIDEO);
        db.execSQL("DROP TABLE IF EXISTS " + CATEDESC);
        onCreate(db);
    }

    public void addUser(UserData userData)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getUsername());
        values.put(COLUMN_PASSWORD, userData.getPassword());
        values.put(COLUMN_EMAIL, userData.getEmail());
        values.put(COLUMN_DATE, userData.getAge());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ACCOUNTS,  null, values);
        db.close();
    }

    public UserData findUser(String username, String email)
    {
        String query1 = "SELECT * FROM " + ACCOUNTS + " WHERE " + COLUMN_USERNAME +
                "=\"" + username + "\"" + " OR " + COLUMN_EMAIL + "=\"" + email + "\"";


        //SELECT * FROM Accounts WHERE username = " ??? "
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query1, null);
        UserData queryData = new UserData();
        if (cursor.moveToFirst()) // to check if user exists
        {
            queryData.setUsername(cursor.getString(0));
            queryData.setPassword(cursor.getString(1));
            queryData.setEmail(cursor.getString(2));
            queryData.setAge(cursor.getString(3));
            //extract information of user
            cursor.close();
        }
        else
        {
            queryData = null;
        }
        db.close();;
        return queryData;
    }
    //retrieve list of categories and activities name
    public ArrayList<Category> getAll() {
        String query = "SELECT * FROM Category";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Category> resultant = new ArrayList<>();

        while (cursor.moveToNext()) {
            Category queryData = new Category();
            queryData.setCategoryName(cursor.getString(0));

            String str = cursor.getString(1);
            String[] list = str.split(";");
            ArrayList<String> categoryList = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                categoryList.add(list[i]);
            }
            queryData.setCategory(categoryList);
            resultant.add(queryData);
        }
        cursor.close();
        db.close();
        return resultant;
    }
    // retrieve list of image
    public ArrayList<Activity> getImages() {
        String query = "SELECT * FROM CateImage";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Activity> resultant = new ArrayList<>();

        while (cursor.moveToNext()) {
            Activity queryData = new Activity();
            //queryData.setImage(cursor.getString(0));

            String str = cursor.getString(0);
            String[] list = str.split(";");
            ArrayList<Activity> imageList = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                Activity imageActivity = new Activity();
                imageActivity.setImage(list[i]);
                imageList.add(imageActivity);
            }
            queryData.setActivities(imageList);
            resultant.add(queryData);
        }
        cursor.close();
        db.close();
        return resultant;
    }
    //retrieve list of videos
    public ArrayList<Activity> getVideos() {
        String query = "SELECT * FROM CateVideo";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Activity> resultant = new ArrayList<>();

        while (cursor.moveToNext()) {
            Activity queryData = new Activity();
            //queryData.setImage(cursor.getString(0));

            String str = cursor.getString(0);
            String[] list = str.split(";");
            ArrayList<Activity> videoList = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                Activity videoActivity = new Activity();
                videoActivity.setVideo(list[i]);
                videoList.add(videoActivity);
            }
            queryData.setActivities(videoList);
            resultant.add(queryData);
        }
        cursor.close();
        db.close();
        return resultant;
    }
    //retrieve list of video description
    public ArrayList<Activity> getDescript() {
        String query = "SELECT * FROM CateDesc";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Activity> resultant = new ArrayList<>();

        while (cursor.moveToNext()) {
            Activity queryData = new Activity();
            //queryData.setImage(cursor.getString(0));

            String str = cursor.getString(0);
            String[] list = str.split(";");
            ArrayList<Activity> descList = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                Activity descActivity = new Activity();
                descActivity.setDesc(list[i]);
                descList.add(descActivity);
            }
            queryData.setActivities(descList);
            resultant.add(queryData);
        }
        cursor.close();
        db.close();
        return resultant;
    }

}
