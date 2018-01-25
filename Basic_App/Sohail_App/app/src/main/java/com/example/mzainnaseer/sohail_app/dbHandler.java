package com.example.mzainnaseer.sohail_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by M.Zain Naseer on 1/25/2018.
 */

public class dbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME="basic_app.db";

    private static final String TABLE_USERS   ="users";

    private static final String COLUMN_USER_EMAIL                   ="email";
    private static final String COLUMN_USER_PHONE                   ="phone";
    private static final String COLUMN_PASSWORD                     ="password";

    private String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_USERS+" ("
            + COLUMN_USER_EMAIL  + " TEXT,"
            + COLUMN_USER_PHONE  + " TEXT,"
            + COLUMN_PASSWORD    +" TEXT" + ")";



    private String DROP_USERS_TABLE="DROP TABLE IF EXISTS "+TABLE_USERS;


    public boolean add_users_data(User _refUsers)
    {
        long k;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_EMAIL, _refUsers.getUserEmail());
        contentValues.put(COLUMN_USER_PHONE,_refUsers.getUserPhone());
        contentValues.put(COLUMN_PASSWORD, _refUsers.getUserPassword());
        k=db.insert(TABLE_USERS,null,contentValues);
        Log.i("k->",String.valueOf(k));
        db.close();
        if(k>0)
        {
            return true;
        }
        else
            return false;
    }



    public boolean getUserTableCount(String email,String password)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor c = db.rawQuery(query, null);

        if(c != null)
        {
            c.moveToFirst();
            if(c.getCount() > 0)
            {
                    int emailIndex = c.getColumnIndex(COLUMN_USER_EMAIL);
                    int loginStatusIndex = c.getColumnIndex(COLUMN_PASSWORD);
                    if(c.getString(emailIndex)==email)
                    {

                        if(c.getString(loginStatusIndex)==password)
                        {
                            return true;
                        }
                    }
                }

        }
        return false;

    }
    private String TRUNCATE_USER_TABLE="DELETE FROM "+TABLE_USERS;

    public void truncate_user_table()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USERS+"",null);
        if(cursor != null)
        {
            if(cursor.getCount() > 0)
            {
                db.execSQL(TRUNCATE_USER_TABLE);
                cursor.close();
            }
        }

        db.close();
    }
    public dbHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(CREATE_USERS_TABLE);
    }
}
