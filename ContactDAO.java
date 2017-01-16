package br.com.andrey.devmakertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 26/12/2016.
 */
public class ContactDAO extends SQLiteOpenHelper{

    private final Context context;

    public ContactDAO(Context context) {
        super(context, "contacts", null, 1);
        this.context = context;
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
            contact.setName(c.getString(c.getColumnIndex("name")));
            contact.setEmail(c.getString(c.getColumnIndex("email")));
            contact.setTelephone(c.getString(c.getColumnIndex("telephone")));

            contacts.add(contact);
        }
        return contacts;
     }

    public void updateContacts(List<Contact> contacts) {
        String sql = "delete from contacts";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        for(int i=0; i<contacts.size();i++) {
            Contact contact = contacts.get(i);
         //  Toast.makeText(context,contact.getName(),Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("id", contact.getId() );
            values.put("name",contact.getName());
            values.put("email",contact.getEmail());
            values.put("telephone",contact.getTelephone());

            db.insert("contacts", null, values );
        }
    }
}
