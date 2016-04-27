package com.theironyard.clt;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {
//    static User user;

    static HashMap<String, User> users = new HashMap<>();



    public static void main(String[] args) {



        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {


                    Session context = request.session();
                    //String user = session.attribute("userName");
                    // User user = users.get(userName);

                    HashMap users = new HashMap();

                    if (context.attribute("userName") == null) {
                        return new ModelAndView(users, "login.html");
                    } else {
                        users.put("name", user);
                        return new ModelAndView(users, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User(name);
                        users.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("userName", name);

                    response.redirect("/");
                    return "";
                })

//                "/logout",
//                ((request, response) -> {
//                    Session session = request.session();
//                    session.invalidate();
//                    response.redirect("/");
//                    return "";
//                })

        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    String password = request.queryParams()
                    response.redirect("/");
                    return "";
                })
       );

        Spark.post(
                "/create-message",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName);

                    if (session.attribute("userName");
                    String messages = request.queryParams("messages");
                    users.messages.add(messages);
                    response.redirect("/");
                    return "";
                })

        );



    }
}

