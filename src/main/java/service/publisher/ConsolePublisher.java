package service.publisher;

import domain.SportEvent;

public class ConsolePublisher implements SportEventPublisher {
    @Override
    public void publish(SportEvent sportEvent) {
        System.out.printf("%s, %s %s%n",
                sportEvent.getLeague().getSport().getName(),
                sportEvent.getLeague().getRegion().getName(),
                sportEvent.getLeague().getName()
        );

        System.out.printf("\t%s, %s, %d%n",
                sportEvent.getName(),
                sportEvent.getKickoff().toString(),
                sportEvent.getId());

        sportEvent.getMarkets().forEach(market -> {
            System.out.printf("\t\t%s%n", market.getName());

            market.getRunners().forEach(runner ->
                System.out.printf("\t\t\t%s, %s, %d%n", runner.getName(), runner.getPrice(), runner.getId())
            );
        });
    }
}
