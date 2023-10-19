package com.example.loginandregistreinsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbactive  extends SQLiteOpenHelper {
   public static  final String DBNAME="dbavtive.db";
    public Dbactive(Context context) {
        super(context, "dbavtive.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDb) {
     MyDb.execSQL("create table users( username TEXT primary key , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDb, int i, int i1) {
      MyDb.execSQL(" Drop table if exists users");
    }

        public Boolean insertDBenUser(String username,String password ){
        SQLiteDatabase MyDb=this.getReadableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put("username",username);
            contentValues.put("password",password);
            Long result = MyDb.insert("users",null,contentValues);
            if (result==-1 ) return  false;
            else return  true ;
        }

        public  Boolean checkusername(String username, String password){
        SQLiteDatabase MyDb=this.getReadableDatabase();
            Cursor cursor = MyDb.rawQuery("select * from users where username=? and password=? ",new String[]{username,password});
        if ( cursor.getCount()>0)
             return  true ;
        else
             return  false;
    }
}
