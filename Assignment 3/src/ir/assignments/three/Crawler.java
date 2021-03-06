package ir.assignments.three;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler extends WebCrawler{
	/**
	 * This method is for testing purposes only. It does not need to be used
	 * to answer any of the questions in the assignment. However, it must
	 * function as specified so that your crawler can be verified programatically.
	 * 
	 * This methods performs a crawl starting at the specified seed URL. Returns a
	 * collection containing all URLs visited during the crawl.
	 */
	public static HashMap<String, Integer> textMap = new HashMap<String, Integer>();
	public static List<String> textPages = new ArrayList<String>();
	public static List<String> urlList = new ArrayList<String>();
	
	//Filter for these data types so ignored during crawl
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" 
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" 
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	@Override
    public boolean shouldVisit(WebURL url) {
            String href = url.getURL().toLowerCase();
            return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/about");
    }
	
	//Adds data such as url in either a List or a hashmap
	@Override
    public void visit(Page page) { 
			
            String url = page.getWebURL().getURL();
            crawl(url);
            System.out.println("URL: " + url);

            if (page.getParseData() instanceof HtmlParseData) {
                    HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                    String text = htmlParseData.getText();
                    int wordCounter = Ranking.countText(text);
                    textMap.put(url, wordCounter);
                    textPages.add(text);
                    System.out.println("Text length: " + text.length());
            }
    }

	
	// Returns the list of urls visited
	public static Collection<String> crawl(String seedURL) {
		urlList.add(seedURL);
		return urlList;
		// TODO implement me
		
	}
}
