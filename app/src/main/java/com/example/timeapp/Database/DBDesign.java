package com.example.timeapp.Database;

public class DBDesign {

    private DBDesign(){}


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
        public static final String ENTRY_COLUMN3 = "Description"; // text
    }

    public static class Schedule {
        public static final String SCHEDULE_TABLE = "Schedule";
        //Columns
        public static final String SCHEDULE_COLUMN1 = "Entry Hour"; // Time
        public static final String SCHEDULE_COLUMN2 = "Leave Hour"; // Time
    }
}
