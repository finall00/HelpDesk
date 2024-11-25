
package br.com.helpDesk.filter;

import br.com.helpDesk.utils.SingleConnection;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns={"/*"})

public class FilterAutenticacao implements Filter{
    
    private static Connection conexao;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        conexao = SingleConnection.getConnection();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        }catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try{
            conexao.close();
        }catch(Exception ex){
            System.out.println("Erro :"+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
