package com.digitalclock.tests;

import com.digitalclock.utils.TimeZoneManager;
import com.digitalclock.models.TimeZoneData;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeZoneManagerTest {
    
    @Test
    public void testGetDefaultTimeZones() {
        java.util.List<TimeZoneData> zones = TimeZoneManager.getDefaultTimeZones();
        assertNotNull(zones);
        assertTrue(zones.size() > 0);
    }
    
    @Test
    public void testGetTimeZone() {
        TimeZoneData tz = TimeZoneManager.getTimeZone("UTC");
        assertNotNull(tz);
        assertEquals("UTC", tz.getTimezoneName());
    }
    
    @Test
    public void testGetNonExistentTimeZone() {
        TimeZoneData tz = TimeZoneManager.getTimeZone("NonExistent");
        assertNull(tz);
    }
    
    @Test
    public void testAddTimeZone() {
        java.util.List<TimeZoneData> initialList = TimeZoneManager.getDefaultTimeZones();
        int initialSize = initialList.size();
        
        TimeZoneData newTz = new TimeZoneData("Test Zone", "UTC");
        TimeZoneManager.addTimeZone(newTz);
        
        java.util.List<TimeZoneData> updatedList = TimeZoneManager.getDefaultTimeZones();
        assertEquals(initialSize + 1, updatedList.size());
    }
    
    @Test
    public void testRemoveTimeZone() {
        TimeZoneData tz = new TimeZoneData("Remove Test", "UTC");
        TimeZoneManager.addTimeZone(tz);
        
        java.util.List<TimeZoneData> beforeRemove = TimeZoneManager.getDefaultTimeZones();
        int beforeSize = beforeRemove.size();
        
        TimeZoneManager.removeTimeZone(tz);
        
        java.util.List<TimeZoneData> afterRemove = TimeZoneManager.getDefaultTimeZones();
        assertEquals(beforeSize - 1, afterRemove.size());
    }
    
    @Test
    public void testDefaultTimeZonesContainCommonZones() {
        java.util.List<TimeZoneData> zones = TimeZoneManager.getDefaultTimeZones();
        boolean hasUTC = zones.stream().anyMatch(tz -> tz.getTimezoneName().contains("UTC"));
        boolean hasNewYork = zones.stream().anyMatch(tz -> tz.getTimezoneName().contains("New York"));
        
        assertTrue(hasUTC);
        assertTrue(hasNewYork);
    }
}
