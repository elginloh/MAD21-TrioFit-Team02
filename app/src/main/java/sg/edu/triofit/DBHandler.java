package sg.edu.triofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


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
    public static String COLUMN_IMAGE = "Image";
    public static String COLUMN_VIDEO = "Video";



    public static int DATABASE_VERSION = 3;

    public  DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_DATE + " TEXT"+ ")";
        //CREATE TABLE Accounts(Username TEXT, Password TEXT, Email TEXT, Date TEXT)

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY + "(" + COLUMN_TITLE + "TEXT," + COLUMN_NAME + "TEXT"  + ")";



        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);

        String varName1 = ""
                + "INSERT INTO \"Category\" VALUES ('Workout','Biceps;Triceps;Abdominals;Upper Chest;Lower Chest;');";//",'https://www.muscleandfitness.com/wp-content/uploads/2014/09/Muscular-Bodybuilder-Doing-Biceps-Workout-With-A-Bicep-Curl.jpg?quality=86&strip=all;https://www.bodybuilding.com/images/2021/april/triceps-workout-for-beginners-header-830x467.jpg;https://image.freepik.com/free-photo/shirtless-man-working-out-home_186523-389.jpg;https://www.muscleandfitness.com/wp-content/uploads/2018/02/1109-feet-elevated-pushup.jpg?quality=86&strip=all;https://bodybuilding-wizard.com/wp-content/uploads/2015/11/decline-dumbbell-bench-press-guide-1-8.jpg;');";

        String varName2 = ""
                + "INSERT INTO \"Category\" VALUES ('Running tips','Hill Run;Recovery Routine;Running posture;Breathing Method;Marathon;');";//",'https://www.active.com/Assets/Running/460/Hill-Runner-Silhouette.jpg;https://www.ihrsa.org/uploads/Articles/Column-Width/equipment_triggerpoint-roller_column.jpg;https://image.freepik.com/free-photo/shirtless-man-working-out-home_186523-389.jpg;https://blog.mapmyrun.com/wp-content/uploads/2020/10/3-Cues-For-Perfect-Running-Posture.jpg;https://runnersconnect.net/wp-content/uploads/2011/08/THE-MOST-IMPORTANT-11.png;https://www.justrunlah.com/wp-content/uploads/2017/05/Running.jpg;');";

        String varName3 = ""
                + "INSERT INTO \"Category\" VALUES ('Yoga','Lower Back;Hip;Hamstring;Muscle Recovery;Deep Core;');";//",'https://images.everydayhealth.com/images/pain-management/back-pain/7-best-yoga-poses-to-soothe-back-pain-08-1440x810.jpg;https://media.self.com/photos/57d8a76846d0cb351c8c57a3/master/pass/hip-stretch-pigeon-arms-extended.jpg;https://www.verywellfit.com/thmb/bRbReEXmtt2gw56S2YjeEWcizdo=/4500x3000/filters:no_upscale():max_bytes(150000):strip_icc()/7-downdog-56f98e3d3df78c7841935724.jpg;https://www.nourishmovelove.com/wp-content/uploads/2019/07/A75I6537.jpg;https://trillium-health.ca/wp-content/uploads/2020/09/woman-doing-core-strengthening-exercise-on-a-mat-in-a-sunny-gym_t20_8djLVQ-796x445-1.jpg;');";

        db.execSQL(varName1);
        db.execSQL(varName2);
        db.execSQL(varName3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY);
        onCreate(db);
    }

    public void addUser(UserData userData)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, userData.getUsername());
        values.put(COLUMN_PASSWORD, userData.getPassword());
        values.put(COLUMN_EMAIL, userData.getEmail());
        values.put(COLUMN_DATE, userData.getDOB());

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
            queryData.setDOB(cursor.getString(3));
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
}
