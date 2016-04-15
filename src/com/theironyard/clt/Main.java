package com.theironyard.clt;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {
    static HashMap<String , User> user = new HashMap<>();

    static String name;
    public static void main(String[] args) {


        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("user", user);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    name = request.queryParams("loginName");
                    String attempt = request.queryParams("loginPassword");
                    User newUser = new User(name);
                    if (newUser.password.equals(attempt)) {
                        user.put(name ,newUser);
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
                    String message = request.queryParams("message");
                    user.get(name).posts.add(message);
                    response.redirect("/");
                    return "";
                })

        );

        Spark.post(
                "/message",
                ((request, response) -> {
                    Integer delete = Integer.valueOf(request.queryParams("choice"));
                    user.get(name).posts.remove(delete);
                    response.redirect("/");
                    return "";
                })
        );
    }
}
