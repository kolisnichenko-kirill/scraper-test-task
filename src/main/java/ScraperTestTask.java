import service.LeagueService;
import service.SportEventService;
import service.SportService;
import service.publisher.ConsolePublisher;
import service.publisher.SportEventPublisher;
import service.request.DefaultHttpRequestBuilder;
import service.request.DefaultHttpRequestSender;
import service.request.HttpRequestBuilder;
import service.request.HttpRequestSender;

public class ScraperTestTask {

    public static void main(String[] args) {
        HttpRequestBuilder httpRequestBuilder = new DefaultHttpRequestBuilder();
        HttpRequestSender httpRequestSender = new DefaultHttpRequestSender();

        SportService sportService = new SportService(httpRequestBuilder, httpRequestSender);
        LeagueService leagueService = new LeagueService(httpRequestBuilder, httpRequestSender);
        SportEventService sportEventService = new SportEventService(httpRequestBuilder, httpRequestSender);

        SportEventPublisher consolePublisher = new ConsolePublisher();

        LeonScraper leonScraper = new LeonScraper(sportService, leagueService, sportEventService, consolePublisher);

        leonScraper.scrape();
    }
}
