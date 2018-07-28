@test
Feature: IdentityE2ETest
  Scenario: Test DVLA Website Functionlity
    Given Read Excel OR CSV Data From the Directory And Retreive File Contents
    Then Launch DLVA application  And Fill VRegDetails Then Validate



