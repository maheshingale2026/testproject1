package com.digitalclock.models;

public class TimeZoneData {
    private String timezoneName;
    private String offset;

    public TimeZoneData(String timezoneName, String offset) {
        this.timezoneName = timezoneName;
        this.offset = offset;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TimeZoneData that = (TimeZoneData) obj;
        return timezoneName != null && timezoneName.equals(that.timezoneName)
                && offset != null && offset.equals(that.offset);
    }

    @Override
    public int hashCode() {
        return (timezoneName != null ? timezoneName.hashCode() : 0) +
               (offset != null ? offset.hashCode() : 0);
    }
}
