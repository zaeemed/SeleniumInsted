Feature: Guest Flow feature

  @PatientGuestRequest @GuestFlow
  Scenario: Successful guest flow of the patient visit
    Given User is on welcome screen
    When User clicks on "I am a Patient" pill
    And User enters insurance "CCA-ICO"
    Then User is on address screen
    And User enters address "55 Fruit Street, Boston, MA, USA"
    Then User is on "Request A Visit" screen
    And User enters "patient" details with relation "none"
    And User clicks on Submit Button
    Then User is on Thank you screen

  @CaregiverGuestRequest @GuestFlow
  Scenario: Successful guest flow of the caregiver visit
    Given User is on welcome screen
    When User clicks on "I take care of a Patient" pill
    And User enters insurance "CCA-ICO"
    Then User is on address screen
    And User enters address "55 Fruit Street, Boston, MA, USA"
    Then User is on "Patient Information" screen
    And User enters "patient & caregiver" details with relation " Mother "
    And User clicks on Submit Button
    Then User is on Thank you screen