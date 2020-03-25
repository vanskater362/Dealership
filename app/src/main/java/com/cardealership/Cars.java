package com.cardealership;

public class Cars {
    public static final String TABLE_NAME = "Cars";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN__ID = "_id";
    public static final String COLUMN_MAKE = "make";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_HASSUNROOF = "hasSunRoof";
    public static final String COLUMN_ISFOURWHEELDRIVE = "isFourWheelDrive";
    public static final String COLUMN_HASLOWMILES = "hasLowMiles";
    public static final String COLUMN_HASPOWERWINDOWS = "hasPowerWindows";
    public static final String COLUMN_HASNAVIGATION = "hasNavigation";
    public static final String COLUMN_HASHEATEDSEATS = "hasHeatedSeats";

    private int id;
    private String _id;
    private String make;
    private String year;
    private String color;
    private String price;
    private int hasSunroof;
    private int isFourWheelDrive;
    private int hasLowMiles;
    private int hasPowerWindows;
    private int hasNavigation;
    private int hasHeatedSeats;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN__ID + " TEXT,"
                    + COLUMN_MAKE + " TEXT,"
                    + COLUMN_YEAR + " TEXT,"
                    + COLUMN_COLOR + " TEXT,"
                    + COLUMN_PRICE + " TEXT,"
                    + COLUMN_HASSUNROOF + " INTEGER,"
                    + COLUMN_ISFOURWHEELDRIVE + " INTEGER,"
                    + COLUMN_HASLOWMILES + " INTEGER,"
                    + COLUMN_HASPOWERWINDOWS + " INTEGER,"
                    + COLUMN_HASNAVIGATION + " INTEGER,"
                    + COLUMN_HASHEATEDSEATS + " INTEGER"
                    + ")";

    public Cars() {
    }

    public Cars(int id, String _id, String make, String year, String color, String price,
                int hasSunroof, int isFourWheelDrive, int hasPowerWindows, int hasNavigation,
                int hasHeatedSeats) {
        this.id = id;
        this._id = _id;
        this.make = make;
        this.year = year;
        this.color = color;
        this.price = price;
        this.hasSunroof = hasSunroof;
        this.isFourWheelDrive = isFourWheelDrive;
        this.hasPowerWindows = hasPowerWindows;
        this.hasNavigation = hasNavigation;
        this.hasHeatedSeats = hasHeatedSeats;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String get_Id() { return _id; }
    public void set_Id(String _id) { this._id = _id; }

    public String getMake() { return make; }
    public  void setMake(String make) { this.make = make; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public int getHasSunroof() { return hasSunroof; }
    public void setHasSunroof(int hasSunroof) { this.hasSunroof = hasSunroof; }

    public int getHasPowerWindows() { return hasPowerWindows; }
    public void setHasPowerWindows(int hasPowerWindows) { this.hasPowerWindows = hasPowerWindows; }

    public int getIsFourWheelDrive() { return isFourWheelDrive; }
    public void setIsFourWheelDrive(int isFourWheelDrive) { this.isFourWheelDrive = isFourWheelDrive; }

    public int getHasLowMiles() { return hasLowMiles; }
    public void setHasLowMiles(int hasLowMiles) { this.hasLowMiles = hasLowMiles; }

    public int getHasHeatedSeats() { return hasHeatedSeats; }
    public void setHasHeatedSeats(int hasHeatedSeats) { this.hasHeatedSeats = hasHeatedSeats; }

    public int getHasNavigation() { return hasNavigation; }
    public void setHasNavigation(int hasNavigation) { this.hasNavigation = hasNavigation; }
}
