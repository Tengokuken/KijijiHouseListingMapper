package controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.maps.errors.ApiException;

import assignment3webcrawler.KijijiCrawler;
import views.MapOutput;


@WebServlet("/result")
public class CrawlerController extends HttpServlet {

	private static final long serialVersionUID = 1L;
  	// Singleton Instance
	private static CrawlerController crawlerControllerInstance;
	
	public static CrawlerController returnCrawlerController() {
		/*
		 * Singleton constructor
		 */
		if (crawlerControllerInstance == null) {
			crawlerControllerInstance = new CrawlerController();
		}
		return crawlerControllerInstance;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		String seed = req.getParameter("seed");
		String limitString = req.getParameter("limit");

        // check if input data is valid
		if (limitString.matches("[0-9]+")) {
	        int limit = Integer.valueOf(limitString);
	        if (limit > 0 && seed.startsWith("https://www.kijiji.ca")) {
	            // execute crawler with the given url
	            KijijiCrawler crawler = KijijiCrawler.returnKijijiCrawlerInstance();
	            try {
	                crawler.crawlFromSeed(seed, limit);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	            // output to map
	            MapOutput mpo = new MapOutput();
	            try {
	                mpo.outputToView();
	            } catch (ApiException e) {
	                e.printStackTrace();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
	            resp.sendRedirect("result.jsp");
	        }
		  
		}

		else {
		  // redirect back to the same page if input data is not valid
		    resp.sendRedirect("/");
		}
	}

}
