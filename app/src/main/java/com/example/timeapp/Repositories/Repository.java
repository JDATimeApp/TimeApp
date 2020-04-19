package com.example.timeapp.Repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.timeapp.Database.RoomConnection;
import com.example.timeapp.Dialogs.DepartmentExistsDialog;
import com.example.timeapp.R;
import com.example.timeapp.models.Entry;
import com.example.timeapp.models.Incidence;
import com.example.timeapp.models.Users;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Repository {

    //private Context context;
    private static Context context;
    //Creating a singleton for only one instance
    private static Repository srepository;

    // *** PostgreSQL Constants ***
    private static String driverLocation = "org.postgresql.Driver";
    private static String postgresConnection = "jdbc:postgresql://192.168.0.22:5432/";
    private static String postgresUsername = "hedy";
    private static String postgresPassword = "lamarr";
    // *** End of PostgreSQL String ***

    private Repository(Context context) {
        this.context = context;
    }

    public static Repository get(Context context) {

        if (srepository == null) {
            srepository = new Repository(context);
        }
        return srepository;
    }
    // Here is where we have to put the methods


    public static void registerNewUser(String email, String username, String passwd, Context context) {
        RoomConnection ro = RoomConnection.getRoomConnection(context);
        Users u = new Users();
        u.setUsername(username);
        u.setEmailAddress(email);
        u.setPassword(passwd);

        ro.userDao().insertUser(u);
        ro.destroyRoomConnection();
        ro.close();
    }

    public static boolean checkIfUserIsRegistered(String email,String username,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        Users u = ro.userDao().checkRegisteredUsername(username);
        Users u2 = ro.userDao().checkRegisteredEmail(email);
        if (u == null && u2 == null) {
            ro.destroyRoomConnection();
            ro.close();
            return false;
        }
        ro.destroyRoomConnection();
        ro.close();
        return true;
    }

    public static boolean checkLogin(String username,String password,Context c) {
        RoomConnection ro = RoomConnection.getRoomConnection(c);

        Users u =ro.userDao().checkLogin(username,password);

        if (u != null){
            ro.destroyRoomConnection();
            ro.close();
            return true;
        } else {
            ro.destroyRoomConnection();
            ro.close();
            return false;
        }
    }

    public static String getUserId(String username, Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        String id = String.valueOf(ro.userDao().getUserId(username));
        ro.destroyRoomConnection();
        ro.close();
        return id;
    }

    public static String getUsernameById(String userId,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        String username = ro.userDao().getUsernameById(userId);
        ro.destroyRoomConnection();
        ro.close();
        return username;
    }

    public static List<Users> getUsers (Context context)  {
        RoomConnection ro = RoomConnection.getRoomConnection(context);

        List<Users> userList = ro.userDao().getAllUsers();
        ro.destroyRoomConnection();
        ro.close();
        return userList;
    }

    public static Users getUserByUsername(String username){
        RoomConnection ro = RoomConnection.getRoomConnection(context);
        Users u = ro.userDao().getUserByUsername(username);

        ro.destroyRoomConnection();
        ro.close();

        return u;
    }

    public static boolean setEntryTime(String userId, Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        boolean output = false;
        Entry e = ro.entryDao().checkEntry(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Entry entry = new Entry();
            entry.setWorkerId(userId);
            entry.setEntryDate(getActualDay(getActualDateTime()));
            entry.setEntryTime(getActualHour(getActualDateTime()));
            ro.entryDao().insertEntry(entry);
            output = true;
            Log.i("Entry","No entry .. inserting new");
        } else {
            Log.i("Entry","Found an entry");
        }
        ro.destroyRoomConnection();
        ro.close();

        return output;
    }

    public static boolean setLeaveTime(String userId,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        boolean output = false;
        Entry e = ro.entryDao().checkEntryDate(userId,getActualDay(getActualDateTime()));
        if (e == null){
            Log.i("Entry","Something went wrong inserting leave time");
        } else {
            e.setLeaveTime(getActualHour(getActualDateTime()));
            ro.entryDao().updateLeaveTime(e);
            Log.i("Entry","Registered leave time");
            output = true;
        }
        ro.destroyRoomConnection();
        ro.close();

        return output;
    }

    public static List<Entry> getUserEntries(String userId,Context c){
        RoomConnection ro = RoomConnection.getRoomConnection(c);
        List<Entry> entryList = ro.entryDao().getUserEntries(userId);
        ro.destroyRoomConnection();
        ro.close();
        return entryList;
    }

    public static void reportIncidence(Incidence i){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child("Incidences");
        ref.push().setValue(i);
    }

    public static String getActualDateTime(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT +1"));
        Date d = new Date();
        String date = df.format(d);
        return date;
    }

    public static String getActualHour(String actualDateTime){
        String [] d = actualDateTime.split(" ");
        return d[1];
    }

    public static String getActualDay(String actualDateTime){
        String [] d = actualDateTime.split(" ");
        return d[0];
    }
    private static Connection connection = null;

    public static Connection openPostgresConnection(){
        try {
            Class.forName(driverLocation);

            connection = DriverManager.getConnection(postgresConnection,postgresUsername,
                    postgresPassword);

            if (connection != null){
                Log.d("PostgreSQL","Connected to the postgres server successfully");
            } else {
                Log.d("PostgreSQL","Failed to connect");
            }

        } catch (SQLException e) {
            Log.d("PostgreSQL","Error connecting to PostgreSQL server");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void addDepartment(String department, final FragmentManager fm){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dptRef = database.getReference().child("Departments").child(department);

        if (department.isEmpty()){
            Toast.makeText(context,"Department name must not be null!",Toast.LENGTH_SHORT).show();
        } else {
            dptRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()){
                      DepartmentExistsDialog de = new DepartmentExistsDialog();
                      de.show(fm,"Department already exists");
                  } else {
                      dptRef.setValue("");
                  }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("DepartmentError","Error ocurred while adding a new department");
                }
            });
        }
    }
    
    public static void registerUserInFirebase(Users u){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("Departments").child("NoDepartment").child(u.getUsername());
        ref.setValue(u);
    }

    // ****** MUSIC AND SOUND EFFECTS ******** //

    public static void playOofSound(){
        MediaPlayer sound = MediaPlayer.create(context, R.raw.oof);
        sound.start();
    }

    public static MediaPlayer sound;

    public static boolean playamorbillie() {
        //Cargo el fichero a reproducir

        if (sound == null) {
            sound = MediaPlayer.create(context, R.raw.amorbillie);
            sound.start();
            sound.setLooping(true);
            return true;
        } else {
            sound.stop();
            sound.release();
            sound = null;
            return false;
        }

    }

    public static void uploadUserProfileImage(Uri uri, String username){

        StorageReference ref = FirebaseStorage.getInstance().getReference().child(username);

        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("Profile","Image uploaded successfully");
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Profile","Error uploading profile image");
            }
        });
    }

}