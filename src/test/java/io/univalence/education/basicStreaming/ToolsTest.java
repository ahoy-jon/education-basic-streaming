package io.univalence.education.basicStreaming;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {

    @org.junit.jupiter.api.Test
    void dryRun() throws Exception {

        final AtomicLong totalVolume = new AtomicLong(0);

        Tools.CSVParser.parse("APPL.csv", new Tools.StockPriceProcessor() {
            @Override
            public void process(String name, LocalDate date, BigDecimal Close, Long Volume, BigDecimal Open, BigDecimal High, BigDecimal Low) {
                totalVolume.addAndGet(Volume);


            }
        });

        System.out.println(totalVolume.get());
    }

}