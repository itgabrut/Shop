package com.ilya.fiters;


import com.ilya.model.Client;
import com.ilya.model.enums_utils.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by ilya on 11.09.2016.
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("FromFilter");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession session = req.getSession(false);
        Client client;
        String par = req.getParameter("clientId");
        if(session == null)resp.sendRedirect("getitems");

        else if(req.getParameter("clientId") == null || (client =(Client) session.getAttribute("loggedClient"))==null){
            resp.sendRedirect("Register.jsp");
        }
        else if(req.getParameter("clientId").equals(String.valueOf(client.getId())) || client.getRoles().contains(Role.ROLE_ADMIN)){
            chain.doFilter(request,response);
        }
        else resp.sendRedirect("getitems");
    }

    @Override
    public void destroy() {

    }
}
