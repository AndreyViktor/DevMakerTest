package br.com.andrey.devmakertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 26/12/2016.
 */
public class ContactDAO extends SQLiteOpenHelper{

    public ContactDAO(Context context) {
        super(context, "contacts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table contacts (id integer primary key, name text, telephone text, email text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Contact> getContacts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from contacts", null);
        List<Contact> contacts = new ArrayList<Contact>();
        while(c.moveToNext()){
            Contact contact = new Contact();

            contact.setId(c.getLong(c.getColumnIndex("id")));
            contact.setName(c.getString(c.getColumnIndex("nome")));
            contact.setEmail(c.getString(c.getColumnIndex("email")));
            contact.setTelephone(c.getString(c.getColumnIndex("telephone")));

            contacts.add(contact);
        }
        return contacts;
     }
}
