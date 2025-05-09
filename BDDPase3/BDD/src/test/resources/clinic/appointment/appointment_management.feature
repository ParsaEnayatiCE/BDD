# appointment_management.feature
Feature: Appointment Management
  As a clinic manager
  I want to manage patient appointments by checking insurance status, doctor specialization, and available times
  So that I can prevent scheduling conflicts and maximize patient satisfaction

  Background:
    Given the appointment system is ready

  Scenario: Regular patient with approved insurance
    Given a "regular" patient
    And the patient's insurance status is "approved"
    And doctor "Dr. Razavi" from "Cardiology" department is available until "2023-12-31"
    When an appointment is requested for "2023-10-10 10:00"
    Then the appointment status should be "confirmed"

  Scenario: Emergency patient with rejected insurance
    Given a "emergency" patient
    And the patient's insurance status is "rejected"
    And doctor "Dr. Hosseini" from "Pediatrics" department is available until "2023-11-15"
    When an appointment is requested for "2023-11-20 09:30"
    Then the appointment status should be "canceled"

  Scenario: Regular patient with rejected insurance
    Given a "regular" patient
    And the patient's insurance status is "rejected"
    And doctor "Dr. Mohammadi" from "Orthopedics" department is available until "2024-01-10"
    When an appointment is requested for "2023-12-25 14:00"
    Then the appointment status should be "canceled"

  Scenario Outline: Managing different appointment cases
    Given a "<patientType>" patient
    And the patient's insurance status is "<insuranceStatus>"
    And doctor "<doctorName>" from "<department>" department is available until "<availableUntil>"
    When an appointment is requested for "<appointmentTime>"
    Then the appointment status should be "<expectedResult>"

    Examples:
      | patientType | insuranceStatus | doctorName      | department   | availableUntil | appointmentTime   | expectedResult |
      | عادی        | تاییدشده        | دکتر رضوی       | قلب          | 2023-12-31     | 2023-10-10 10:00  | تایید          |
      | اورژانسی    | ردشده           | دکتر حسینی      | اطفال        | 2023-11-15     | 2023-11-20 09:30  | لغو            |
      | عادی        | ردشده           | دکتر محمدی      | ارتوپدی      | 2024-01-10     | 2023-12-25 14:00  | لغو            |
      | اورژانسی    | تاییدشده        | دکتر اکبری      | چشم          | 2023-10-30     | 2023-10-15 11:00  | تایید          |
      | عادی        | درحال‌بررسی     | دکتر جعفری      | پوست         | 2023-12-15     | 2023-11-15 16:30  | لغو            |
      | عادی        | تاییدشده        | دکتر صادقی      | داخلی        | 2023-09-30     | 2023-10-05 14:15  | لغو            |