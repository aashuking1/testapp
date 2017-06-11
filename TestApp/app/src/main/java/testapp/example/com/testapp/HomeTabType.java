package testapp.example.com.testapp;

public enum HomeTabType {

    VIDEO_TAB(1, "video"),
    IMAGE_TAB(2, "image"),
    MILESTONE_TAB(3, "milestone");

    private int mIndex;
    private String mName;

    HomeTabType(int index, String name) {
        mIndex = index;
        mName = name;
    }

    public int getIndex() {
        return mIndex;
    }

    public String getName() {
        return mName;
    }

    public static HomeTabType fromPosition(int position) {
        for (HomeTabType homeTabType : HomeTabType.values()) {
            if (homeTabType.getIndex() == position) {
                return homeTabType;
            }
        }
        return VIDEO_TAB;
    }

    public static HomeTabType fromName(String name) {
        for (HomeTabType homeTabType : HomeTabType.values()) {
            if (homeTabType.getName().equalsIgnoreCase(name)) {
                return homeTabType;
            }
        }
        return VIDEO_TAB;
    }
}
