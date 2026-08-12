package com.digitalclock.tests;

import com.digitalclock.utils.TimeZoneManager;
import com.digitalclock.models.TimeZoneData;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeZoneManagerTest {
    
    /**
     * Helper method to print test results to console
     */
    private void printResults(String testName, String message) {
        System.out.println("\n========================================");
        System.out.println("TEST: " + testName);
        System.out.println("========================================");
        System.out.println(message);
        System.out.println("========================================\n");
    }
    
    /**
     * Helper method to print TimeZoneData list
     */
    private void printTimeZoneList(java.util.List<TimeZoneData> zones, String title) {
        System.out.println(title + ":");
        for (TimeZoneData tz : zones) {
            System.out.println("  - " + tz.getTimezoneName() + " (" + tz.getOffset() + ")");
        }
    }
    
    @Test
    public void testGetDefaultTimeZones() {
        java.util.List<TimeZoneData> zones = TimeZoneManager.getDefaultTimeZones();
        assertNotNull(zones);
        assertTrue(zones.size() > 0);
        
        printTimeZoneList(zones, "Default Time Zones");
        printResults("testGetDefaultTimeZones", "✓ Successfully retrieved " + zones.size() + " default time zones");
    }
    
    @Test
    public void testGetTimeZone() {
        TimeZoneData tz = TimeZoneManager.getTimeZone("UTC");
        assertNotNull(tz);
        assertEquals("UTC", tz.getTimezoneName());
        
        String result = "Found TimeZone: " + tz.getTimezoneName() + " (" + tz.getOffset() + ")";
        printResults("testGetTimeZone", "✓ " + result);
    }
    
    @Test
    public void testGetNonExistentTimeZone() {
        TimeZoneData tz = TimeZoneManager.getTimeZone("NonExistent");
        assertNull(tz);
        
        printResults("testGetNonExistentTimeZone", "✓ Correctly returned null for non-existent timezone");
    }
    
    @Test
    public void testAddTimeZone() {
        java.util.List<TimeZoneData> initialList = TimeZoneManager.getDefaultTimeZones();
        int initialSize = initialList.size();
        
        TimeZoneData newTz = new TimeZoneData("Test Zone", "UTC");
        TimeZoneManager.addTimeZone(newTz);
        
        java.util.List<TimeZoneData> updatedList = TimeZoneManager.getDefaultTimeZones();
        assertEquals(initialSize + 1, updatedList.size());
        
        String result = "Successfully added 'Test Zone'. Size: " + initialSize + " → " + updatedList.size();
        printResults("testAddTimeZone", "✓ " + result);
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
        
        String result = "Successfully removed 'Remove Test'. Size: " + beforeSize + " → " + afterRemove.size();
        printResults("testRemoveTimeZone", "✓ " + result);
    }
    
    @Test
    public void testDefaultTimeZonesContainCommonZones() {
        java.util.List<TimeZoneData> zones = TimeZoneManager.getDefaultTimeZones();
        boolean hasUTC = zones.stream().anyMatch(tz -> tz.getTimezoneName().contains("UTC"));
        boolean hasNewYork = zones.stream().anyMatch(tz -> tz.getTimezoneName().contains("New York"));
        
        assertTrue(hasUTC);
        assertTrue(hasNewYork);
        
        String result = "Contains UTC: " + hasUTC + ", Contains New York: " + hasNewYork;
        printResults("testDefaultTimeZonesContainCommonZones", "✓ " + result);
    }
}
