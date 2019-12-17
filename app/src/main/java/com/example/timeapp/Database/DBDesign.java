package com.example.timeapp.Database;

public class DBDesign {

    private DBDesign(){}

    public static final String DATABASE_NAME = "TimeApp.db";

    public static final String USER_CREATE_TABLE = "CREATE TABLE "+UserDesign.USER_TABLE+" ("+
            UserDesign.USER_COLUMN1+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
            UserDesign.USER_COLUMN2+" VARCHAR(20) NOT NULL UNIQUE,"+
            UserDesign.USER_COLUMN3+" VARCHAR(20) NOT NULL ,"+
            UserDesign.USER_COLUMN4+" VARCHAR(40) NOT NULL UNIQUE)";

    public static final String ENTRY_CREATE_TABLE = "CREATE TABLE "+EntryDesign.ENTRY_TABLE+" ("+
            UserDesign.USER_COLUMN1+" INTEGER,"+
            EntryDesign.ENTRY_COLUMN1+" DATE NOT NULL,"+
            EntryDesign.ENTRY_COLUMN2+" TIME NOT NULL,"+
            EntryDesign.ENTRY_COLUMN3+" TIME,"+
            EntryDesign.ENTRY_COLUMN4+" TEXT,"+
            "FOREIGN KEY ("+UserDesign.USER_COLUMN1+") REFERENCES "+
            UserDesign.USER_TABLE+" ("+UserDesign.USER_COLUMN1+"))";

    public static final String SCHEDULE_CREATE_TABLE = "CREATE TABLE "+ScheduleDesign.SCHEDULE_TABLE+" ("+
            UserDesign.USER_COLUMN1+" INTEGER,"+
            ScheduleDesign.SCHEDULE_COLUMN1+" TIME NOT NULL,"+
            ScheduleDesign.SCHEDULE_COLUMN2+" TIME NOT NULL,"+
            "FOREIGN KEY ("+UserDesign.USER_COLUMN1+") REFERENCES "+
            UserDesign.USER_TABLE+" ("+UserDesign.USER_COLUMN1+"))";

    public static class UserDesign {

        public static final String USER_TABLE = "Users";
        //Columns
        public static final String USER_COLUMN1 = "User ID"; // PK
        public static final String USER_COLUMN2 = "Username"; // 20
        public static final String USER_COLUMN3 = "Password"; // 20
        public static final String USER_COLUMN4 = "Email Address"; // 40
    }

    public static class EntryDesign {

        public static final String ENTRY_TABLE = "Entries";
        //Columns
        public static final String ENTRY_COLUMN1 = "Entry Date"; // Date
        public static final String ENTRY_COLUMN2 = "Entry Time"; // Time
        public static final String ENTRY_COLUMN3 = "Leave time"; // text
        public static final String ENTRY_COLUMN4 = "Description"; // Time
    }

    public static class ScheduleDesign {
        public static final String SCHEDULE_TABLE = "Schedule";
        //Columns
        public static final String SCHEDULE_COLUMN1 = "Entry Hour"; // Time
        public static final String SCHEDULE_COLUMN2 = "Leave Hour"; // Time
    }
}