package hongkhanh.on_tap1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String TABLE_NAME = "Database_OnThi";
    public static final String ID = "id";
    public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String GENDER = "gender";
        public static final String AGE = "age";
        public static final String IMAGE = "image";

    Context context;

    public DBManager(@Nullable Context context) {
        super(context, "OnThi", null, VERSION);
        Log.d("DBManager", "create database dphelper");
        this.context = context;
    }
    String SqlCreate = "CREATE TABLE " + TABLE_NAME + "(" +
            ID + " integer primary key, " +
            USERNAME + " text , " +
            PASSWORD + " text, " +
            GENDER + " text, " +
            AGE + " text, " +
            IMAGE + " text)";
    String Add = "insert into "+ TABLE_NAME + " values (" +
            " 'khanh', '123456', 'male', '21' , 'la')";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlCreate);
        Log.d("DBManager", "Add database  onCreate()");


    }

    public ArrayList<Model> GellAllMember(){
        String queryAll = "SELECT * FROM " + TABLE_NAME;
        ArrayList<Model> arrayList = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(queryAll,null);

        try {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String usn = cursor.getString(1);
                String pass = cursor.getString(2);
                String gender = cursor.getString(3);
                String age = cursor.getString(4);
                String image = cursor.getString(5);
                arrayList.add(new Model(id, usn, pass, gender, age, image));
            }while (cursor.moveToNext());
            database.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return arrayList;

    }

    public void AddMember(){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME,"khanh1");
        values.put(PASSWORD,"123456");
        values.put(GENDER,"female");
        values.put(AGE,"21");
        values.put(IMAGE,"la");
        database.insert(TABLE_NAME,null,values);
        Log.d("DBManager", "Add member  addmember()");
    }

    public  void UpdateMember(String id, String avatar, String age, String name){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME,name);
        values.put(AGE,age);
        values.put(IMAGE, avatar);
        database.update(TABLE_NAME, values, ID + "=?", new String[]{id});
    }

    public void DeleteMember(String Id){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_NAME, ID + "=?", new String[] {Id});
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
