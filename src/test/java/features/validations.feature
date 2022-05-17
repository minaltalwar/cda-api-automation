
Feature: Validating Sample Share

  @SampleShare
  Scenario Outline: Verify sampleshare API
    Given Sample share request payload for "<kpi>" "<technology>"
    When user calls "sampleshareAPI" API with POST http request
    Then API call success with status code 200



    Examples: 
      |          kpi          | technology | 
      | DL Traffic Volume-Sum |     2G     |
      | DL Traffic Volume-Sum |     3G     |
      | DL Traffic Volume-Sum |     4G     | 	 	 	