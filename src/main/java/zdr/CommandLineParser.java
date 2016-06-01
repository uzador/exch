package zdr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zdr.util.AppConfig;
import zdr.util.Cli;
import zdr.util.SymbolConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by yzadorozhnyy on 05.05.2016.
 */
@Component
public class CommandLineParser implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineParser.class);
    private static final String CSV_DELIMITER = "\\|";

    @Override
    public void run(String... strings) throws Exception {
        Cli cli = new Cli(strings);
        cli.parseCli();

        Path path = Paths.get(cli.getConfig());
        if (Files.notExists(path)) {
            throw new RuntimeException("Config file for symbols missed");
        }

        SymbolConfig syConfig = SymbolConfig.getInstance();
        try (Stream<String> lines = Files.lines(path)) {
            lines
                    .skip(1)
                    .map(l -> l.split(CSV_DELIMITER))
                    .forEach(x -> syConfig.put(x[0], x[1]));
        } catch (IOException e) {
            log.error("Error while streaming file. Exception: {}", e);
            System.exit(0);
        }

        AppConfig appConfig = AppConfig.getInstance();
        if (cli.getMode() != null) {
            appConfig.put("mode", cli.getMode());
        }
    }
}
