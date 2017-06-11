package testapp.example.com.testapp;



public enum MediaType {
    NONE(0),
    VIDEO(1),
    IMAGE(2),
    MILESTONE(3);

    private int index;

    MediaType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public MediaType getMediaType(int position) {
        for (MediaType actionType : MediaType.values()) {
            if (actionType.getIndex() == position) {
                return actionType;
            }
        }
        return NONE;
    }
}
