package com.theironyard.clt;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    static HashMap<String , User> users = new HashMap<>();


    public static void main(String[] args) throws IOException{
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    // get userName from session
                    String userName = session.attribute("userName");
                    // get user from users
                    users.get(userName);
                    // if the user is null, render login
                    // else render messages with user

                    if (userName == null) {
                        users.put(userName, new User(userName));
                        return new ModelAndView(userName, "login.html");
                    } else {

                        return new ModelAndView(userName, "messages.html");
                    }
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
                    Session session = request.session();
                    // get user from users
                    String userName = session.attribute("userName");
                    // if user is not null, add to users messages
                    // redirect to root
                    String message = request.queryParams("message");
                    users.get(userName).posts.add(message);
                    Integer delete = Integer.valueOf(request.queryParams("choice"));
                    users.get(userName).posts.remove(delete);
                    String edit = request.queryParams("edits");
                    Integer num = Integer.valueOf(request.queryParams("editsNumber"));
                    users.get(userName).posts.remove(num);
                    users.get(userName).posts.add(num, edit);
                    response.redirect("/");


                    return "";
                })

        );

        File f = new File("messages.txt");
        FileWriter fw = new FileWriter("messages.txt");

        fw.write(message);
    }
}
