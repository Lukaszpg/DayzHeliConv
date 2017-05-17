package pl.husar.main;

import pl.husar.services.ProcessingService;

public class Main {

    public static void main(String[] args) {
        ProcessingService processingService = new ProcessingService();
        processingService.execute();
    }
}
