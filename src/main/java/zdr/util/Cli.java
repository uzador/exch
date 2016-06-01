package zdr.util;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yzadorozhnyy on 01.06.2016.
 */
public class Cli {
    private static final Logger log = LoggerFactory.getLogger(Cli.class);

    private String[] args;
    private Options options = new Options();

    private String config;
    private String mode;

    public Cli(String[] args) {
        this.args = args;

        options.addOption("c", "config", true, "config config");
        options.addOption("m", "mode", true, "mode");
    }

    public void parseCli() {
        log.info("Parsing command line");

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("c")) {
                config = cmd.getOptionValue("c");
                log.info("Using -c={}", config);
            } else {
                log.error("Missing c option");
                throw new ParseException("Missing c option");
            }

            if (cmd.hasOption("m")) {
                mode = cmd.getOptionValue("m");
                log.info("Using -m={}", mode);
            } else {
                log.error("Missing m option");
                throw new ParseException("Missing m option");
            }
        } catch (ParseException e) {
            log.error("Faild to parse command line");
            help();
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);
        System.exit(0);
    }

    public String getConfig() {
        return config;
    }

    public String getMode() {
        return mode;
    }
}
