Feature: Navigate to Google Website

  Scenario: User opens Chrome browser and navigates to Google
    Given User launches the Chrome browser
    When User navigates to "https://www.google.com"
    Then User should see the Google homepage
    And User should verify the page title contains "Google"
  And User closes the browser
