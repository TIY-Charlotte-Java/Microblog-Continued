package com.theironyard.clt;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {
    static HashMap<String , User> users = new HashMap<>();


    public static void main(String[] args) {
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    // get userName from session
                    
                    // get user from users

                    // if the user is null, render login
                    // else render messages with user

//                    if (name == null) {
//                        return new ModelAndView(user, "login.html");
//                    } else {
//                        user.put(name, new User(name));
//                        return new ModelAndView(user, "messages.html");
//                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    Session session = request.session();

                    // set the current session's username to what we pass in.
                    String name = request.queryParams("loginName");
                    String password = request.queryParams("loginPassword");

                    // if the password is the user's password, then
                    // save user to session
                    User currentUser = users.containsKey(name) ?
                            users.get(name) :
                            new User(name);

                    if (password.equals(currentUser.password)) {
                        users.put(name, currentUser);
                        session.attribute("userName", name);
                        response.redirect("/");
                        return "";
                    } else {
                        return "invalid password ya git!";
                    }
                })
        );



        Spark.post(
                "/message",
                ((request, response) -> {
                    // get userName from session
                    // get user from users
                    // if user is not null, add to users messages
                    // redirect to root
                    String message = request.queryParams("message");
                    user.get(name).posts.add(message);
                    response.redirect("/");
                    return "";
                })

        );

//        Spark.post(
//                "/message",
//                ((request, response) -> {
//                    Integer delete = Integer.valueOf(request.queryParams("choice"));
//                    user.get(name).posts.remove(delete);
//                    response.redirect("/");
//                    return "";
//                })
//        );
    }
}
