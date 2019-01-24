#Author: Jaydeep Basu

Feature: Login Functionality
	To test Login Functionality

@Login
@test1
Scenario: To validate whether user is able to login with correct username and password1.
Given The user opens the URL
When The user enters "Guest Username" in "Login" page
And The user enters "Guest Password" in "Login" page

@Login
@test2
Scenario: To validate whether user is able to login with correct username and password2.
Given The user opens the URL
When The user enters "Guest Username2" in "Login" page
And The user enters "Guest Password2" in "Login" page

@Login
@test3
Scenario: To validate whether user is able to login with correct username and password3.
Given The user opens the URL
When The user enters "Guest Username3" in "Login" page
And The user enters "Guest Password3" in "Login" page


@Smoke
Scenario: To validate whether user is able to login with correct username and password1.
Given The user has logged in to the portal as "GuestUser".
When The user clicks on "My Profile" link in "My Account" page.
Then The user should get "Personal Information" message in "My Account" section.
And The user logs out of the portal.
Then Verify the user will be redirected to "Login" page.

@Smoke
Scenario: To validate whether user is able to login with correct username and password2.
Given The user has logged in to the portal as "GuestUser".
When The user clicks on "My Profile" link in "My Account" page.
Then The user should get "Personal Information" message in "My Account" section.
And The user logs out of the portal.
Then Verify the user will be redirected to "Login" page.

#@CloseBrowser
#Scenario: To close the browser.
#Given The user closes the browser.