package main.java.servlets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Router {
    public static void routeTo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println(req.getRequestURI());
        
        //
        switch(req.getRequestURI()){
            case "/TourOfHeroes/hero.get" || "duhd/":
                Person.getHeroes(req, resp);
                break;
            case "/TourOfHeroes/hero.add":
                HeroController.addHero(req, resp);
            default:
                req.getRequestDispatcher( "index.html").forward(req, resp);

        }
    }
}
