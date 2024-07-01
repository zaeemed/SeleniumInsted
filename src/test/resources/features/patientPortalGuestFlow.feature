Feature: Guest Flow feature

  @GuestPatientPortalFlow
  Scenario Outline: Successful guest visit flow
    Given User is on welcome screen
    When User clicks on "<user_type>" pill
    And User enters insurance "<insurance>"
    Then User is on address screen
    And User enters address "<address>"
    Then User is on "<next_screen>" screen
    And User enters "<details>" details with relation " <relation> "
    And User clicks on Submit Button
    Then User is on Thank you screen

    Examples:
      | user_type               | insurance | address                          | next_screen           | details              | relation |
#      | I am a Patient          | CCA-ICO   | 55 Fruit Street, Boston, MA, USA | Request A Visit       | patient              | none     |
      | I take care of a Patient | CCA-ICO   | 55 Fruit Street, Boston, MA, USA | Patient Information   | patient & caregiver  | Mother   |