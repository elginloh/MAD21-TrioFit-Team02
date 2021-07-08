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

    public static String WORKOUT = "Workout";
    public static String RUN = "Run";
    public static String YOGA = "Yoga";
    public static String COLUMN_NAME = "Activity Name";
    public static String COLUMN_IMAGE = "Image";
    public static String COLUMN_VIDEO = "Video";



    public static int DATABASE_VERSION = 1;

    public  DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_DATE + " TEXT"+ ")";
        //CREATE TABLE Accounts(Username TEXT, Password TEXT, Email TEXT, Date TEXT)

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY + "(" + COLUMN_TITLE + "TEXT" + ")";

        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + WORKOUT + "(" + COLUMN_NAME + "TEXT" + COLUMN_IMAGE + "TEXT" + COLUMN_VIDEO + "TEXT" + ")";

        String CREATE_RUN_TABLE = "CREATE TABLE " + RUN + "(" + COLUMN_NAME + "TEXT" + COLUMN_IMAGE + "TEXT" + COLUMN_VIDEO + "TEXT" + ")";

        String CREATE_YOGA_TABLE = "CREATE TABLE " + YOGA + "(" + COLUMN_NAME + "TEXT" + COLUMN_IMAGE + "TEXT" + COLUMN_VIDEO + "TEXT" + ")";

        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_WORKOUT_TABLE);
        db.execSQL(CREATE_RUN_TABLE);
        db.execSQL(CREATE_YOGA_TABLE);

        ContentValues cate1 = new ContentValues();
        ContentValues cate2 = new ContentValues();
        ContentValues cate3 = new ContentValues();

        cate1.put(COLUMN_TITLE, "Workout");
        cate2.put(COLUMN_TITLE, "Running tips");
        cate3.put(COLUMN_TITLE, "Yoga");

        db.insert(CATEGORY, null, cate1);
        db.insert(CATEGORY, null, cate2);
        db.insert(CATEGORY, null, cate3);

        ContentValues wo1 = new ContentValues();
        ContentValues wo2 = new ContentValues();
        ContentValues wo3 = new ContentValues();
        ContentValues wo4 = new ContentValues();
        ContentValues wo5 = new ContentValues();

        wo1.put(COLUMN_NAME, "Biceps");
        wo1.put(COLUMN_IMAGE, "https://www.muscleandfitness.com/wp-content/uploads/2014/09/Muscular-Bodybuilder-Doing-Biceps-Workout-With-A-Bicep-Curl.jpg?quality=86&strip=all");
        wo1.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=UY6-JzdnHUM");
        wo2.put(COLUMN_NAME, "Triceps");
        wo2.put(COLUMN_IMAGE, "https://www.bodybuilding.com/images/2021/april/triceps-workout-for-beginners-header-830x467.jpg");
        wo2.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=KzBZ02EAJvE");
        wo3.put(COLUMN_NAME, "Abdominals");
        wo3.put(COLUMN_IMAGE, "https://image.freepik.com/free-photo/shirtless-man-working-out-home_186523-389.jpg");
        wo3.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=zzD80vCLq0Y");
        wo4.put(COLUMN_NAME, "Upper Chest");
        wo4.put(COLUMN_IMAGE, "https://www.muscleandfitness.com/wp-content/uploads/2018/02/1109-feet-elevated-pushup.jpg?quality=86&strip=all");
        wo4.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=EHR3Rl26-4A");
        wo5.put(COLUMN_NAME, "Lower Chest");
        wo5.put(COLUMN_IMAGE, "https://bodybuilding-wizard.com/wp-content/uploads/2015/11/decline-dumbbell-bench-press-guide-1-8.jpg");
        wo5.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=BKyvTYuxWZE");

        db.insert(CATEGORY, null, wo1);
        db.insert(CATEGORY, null, wo2);
        db.insert(CATEGORY, null, wo3);
        db.insert(CATEGORY, null, wo4);
        db.insert(CATEGORY, null, wo5);

        ContentValues run1 = new ContentValues();
        ContentValues run2 = new ContentValues();
        ContentValues run3 = new ContentValues();
        ContentValues run4 = new ContentValues();
        ContentValues run5 = new ContentValues();

        run1.put(COLUMN_NAME, "Hill Run");
        run1.put(COLUMN_IMAGE, "https://www.active.com/Assets/Running/460/Hill-Runner-Silhouette.jpg");
        run1.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=bRjT1asH1kw");
        run2.put(COLUMN_NAME, "Recovery Routine");
        run2.put(COLUMN_IMAGE, "https://www.ihrsa.org/uploads/Articles/Column-Width/equipment_triggerpoint-roller_column.jpg");
        run2.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=zbayPYgUWUw");
        run3.put(COLUMN_NAME, "Running posture");
        run3.put(COLUMN_IMAGE, "(https://blog.mapmyrun.com/wp-content/uploads/2020/10/3-Cues-For-Perfect-Running-Posture.jpg");
        run3.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=_kGESn8ArrU&ab_channel=GlobalTriathlonNetwork");
        run4.put(COLUMN_NAME, "Breathing Method");
        run4.put(COLUMN_IMAGE, "https://runnersconnect.net/wp-content/uploads/2011/08/THE-MOST-IMPORTANT-11.png");
        run4.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=MtvBzgeUrZ8&ab_channel=GlobalTriathlonNetwork");
        run5.put(COLUMN_NAME, "Marathon");
        run5.put(COLUMN_IMAGE, "https://www.justrunlah.com/wp-content/uploads/2017/05/Running.jpg");
        run5.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=C7NnlF0-p60&ab_channel=GlobalTriathlonNetwork");

        db.insert(CATEGORY, null, run1);
        db.insert(CATEGORY, null, run2);
        db.insert(CATEGORY, null, run3);
        db.insert(CATEGORY, null, run4);
        db.insert(CATEGORY, null, run5);

        ContentValues yoga1 = new ContentValues();
        ContentValues yoga2 = new ContentValues();
        ContentValues yoga3 = new ContentValues();
        ContentValues yoga4 = new ContentValues();
        ContentValues yoga5 = new ContentValues();

        yoga1.put(COLUMN_NAME, "Lower Back");
        yoga1.put(COLUMN_IMAGE, "https://images.everydayhealth.com/images/pain-management/back-pain/7-best-yoga-poses-to-soothe-back-pain-08-1440x810.jpg");
        yoga1.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=XeXz8fIZDCE&ab_channel=YogaWithAdriene");
        yoga2.put(COLUMN_NAME, "Hip");
        yoga2.put(COLUMN_IMAGE, "https://media.self.com/photos/57d8a76846d0cb351c8c57a3/master/pass/hip-stretch-pigeon-arms-extended.jpg");
        yoga2.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=FhcZuQRC-mI&ab_channel=YogawithKassandra");
        yoga3.put(COLUMN_NAME, "Hamstring");
        yoga3.put(COLUMN_IMAGE, "https://www.verywellfit.com/thmb/bRbReEXmtt2gw56S2YjeEWcizdo=/4500x3000/filters:no_upscale():max_bytes(150000):strip_icc()/7-downdog-56f98e3d3df78c7841935724.jpg");
        yoga3.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=-Mirm7LKvKk");
        yoga4.put(COLUMN_NAME, "Muscle Recovery");
        yoga4.put(COLUMN_IMAGE, "https://www.nourishmovelove.com/wp-content/uploads/2019/07/A75I6537.jpg");
        yoga4.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=YLSUfYjpltU&ab_channel=JessicaRichburg");
        yoga5.put(COLUMN_NAME, "Deep Core");
        yoga5.put(COLUMN_IMAGE, "https://trillium-health.ca/wp-content/uploads/2020/09/woman-doing-core-strengthening-exercise-on-a-mat-in-a-sunny-gym_t20_8djLVQ-796x445-1.jpg");
        yoga5.put(COLUMN_VIDEO, "https://www.youtube.com/watch?v=BPobdbmzY9o&ab_channel=YogaWithAdriene");

        db.insert(CATEGORY, null, yoga1);
        db.insert(CATEGORY, null, yoga2);
        db.insert(CATEGORY, null, yoga3);
        db.insert(CATEGORY, null, yoga4);
        db.insert(CATEGORY, null, yoga5);
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
//    public ArrayList<User> getUsers(){
//        SQLiteDatabase db = getWritableDatabase();
//        //Cursor cursor = db.rawQuery("select * from users where name = ""+ name + """, null);
//        Cursor cursor = db.rawQuery("select * from users", null);
//        User u = null;
//        ArrayList<User> list = new ArrayList<>();
//        while(cursor.moveToNext()){
//            u = new User();
//            u.setName(cursor.getString(0));
//            u.setDescription(cursor.getString(1));
//            u.setId(cursor.getInt(2));
//            if(cursor.getInt(3) == 1){
//                u.setFollowed(true);
//            }
//            if(cursor.getInt(3) == 0)
//            {
//                u.setFollowed(false);
//            }
//            list.add(u);
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }

    public ArrayList<Category> getAllTitle(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Category", null);
        ArrayList<Category> list = new ArrayList<>();
        while(cursor.moveToFirst()){
            Category c = new Category();
            c.setCategoryName(cursor.getString(0));
            list.add(c);
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Activity> getAllWO(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Workout", null);
        ArrayList<Activity> list = new ArrayList<>();
        while(cursor.moveToFirst()){
           Activity a = new Activity();
           a.setName(cursor.getString(0));
           a.setImage(cursor.getString(1));
           a.setVideo(cursor.getString(2));
           list.add(a);
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Activity> getAllRun(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Run", null);
        ArrayList<Activity> list = new ArrayList<>();
        while(cursor.moveToFirst()){
            Activity a = new Activity();
            a.setName(cursor.getString(0));
            a.setImage(cursor.getString(1));
            a.setVideo(cursor.getString(2));
            list.add(a);
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Activity> getAllYoga(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Yoga", null);
        ArrayList<Activity> list = new ArrayList<>();
        while(cursor.moveToFirst()){
            Activity a = new Activity();
            a.setName(cursor.getString(0));
            a.setImage(cursor.getString(1));
            a.setVideo(cursor.getString(2));
            list.add(a);
        }
        cursor.close();
        db.close();
        return list;
    }

}
