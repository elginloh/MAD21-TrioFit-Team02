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



    public static int DATABASE_VERSION = 14;

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

        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_CATEIMAGE_TABLE);
        db.execSQL(CREATE_CATEVIDEO_TABLE);

        String varName1 = ""
                + "INSERT INTO \"Category\" VALUES ('Workout','Biceps;Triceps;Abdominals;Upper Chest;Lower Chest;');";
        String varName2 = ""
                + "INSERT INTO \"Category\" VALUES ('Running tips','Hill Run;Recovery Routine;Running posture;Breathing Method;Marathon;');";
        String varName3 = ""
                + "INSERT INTO \"Category\" VALUES ('Yoga','Lower Back;Hip;Hamstring;Muscle Recovery;Deep Core;');";
        String varName4 = ""
                + "INSERT INTO \"CateImage\" VALUES ('biceps;triceps;abs;upperchest;lowerchest');";
        String varName5 = ""
                + "INSERT INTO \"CateImage\" VALUES ('hillrun;recoveryroutine;runningposture;breathingmethod;marathon');";
        String varName6 = ""
                + "INSERT INTO \"CateImage\" VALUES ('lowerback;hip;hamstring;musclerecovery;deepcore');";
        String varName7 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('UY6-JzdnHUM;KzBZ02EAJvE;zzD80vCLq0Y;EHR3Rl26-4A;BKyvTYuxWZE');";
        String varName8 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('bRjT1asH1kw;zbayPYgUWUw;_kGESn8Arr;MtvBzgeUrZ8;C7NnlF0-p60');";
        String varName9 = ""
                + "INSERT INTO \"CateVideo\" VALUES ('XeXz8fIZDCE;FhcZuQRC-mI;-Mirm7LKvKk;YLSUfYjpltU;BPobdbmzY9o');";

        db.execSQL(varName1);
        db.execSQL(varName2);
        db.execSQL(varName3);
        db.execSQL(varName4);
        db.execSQL(varName5);
        db.execSQL(varName6);
        db.execSQL(varName7);
        db.execSQL(varName8);
        db.execSQL(varName9);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + CATEIMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + CATEVIDEO);
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


}
