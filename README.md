# Microblog Continued

Let's talk about continuing our Microblog solution.

But first, enjoy this gif of someone cooking what looks like salmon on a Pentium 4:

![salmonchip](http://i.giphy.com/HwU6KgJQvKi1W.gif)

Now, on to our assignment..

## Description

Add functionality to your microblog.

## Requirements

* Add a password field to the login form. If the user doesn't exist, create a new user and store the password in the `User` object. If the user does exists, check the password and, if it's wrong, don't let them log in (you can decide the details for yourself).
* Add multi-user support by storing your users in a `HashMap<String, User>` and putting your `ArrayList<Message>` inside the `User` object.
* In the `/create-user` route, save the username into the session. In the `/` route, get the username out of the session and subsequently get your `User` object.
* Show a logout button when the user is logged in. It should invalidate the session and refresh the page so you can log in again with a new user.
* Add a form in `messages.html` which lets you delete a message by entering its number.
* Add a form in `messages.html` which lets you edit a message by entering its number and the text you want to replace it with.
* Make the microblog persist data on the disk by encoding the data as JSON in each POST route, and decoding it when the web app first runs.

![screenshot1.png]
![screenshot2.png]

[screenshot1.png]: https://tiy-learn-content.s3.amazonaws.com/cbbb9584-screenshot1.png
[screenshot2.png]: https://tiy-learn-content.s3.amazonaws.com/3a4d211c-screenshot2.png
