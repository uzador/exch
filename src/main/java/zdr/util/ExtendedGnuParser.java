package zdr.util;

import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.ParseException;

import java.util.ListIterator;

/**
 * Created by yzadorozhnyy on 07.06.2016.
 */
public class ExtendedGnuParser extends GnuParser {
    private boolean ignoreUnrecognizedOption;

    public ExtendedGnuParser(final boolean ignoreUnrecognizedOption) {
        this.ignoreUnrecognizedOption = ignoreUnrecognizedOption;
    }

    @Override
    protected void processOption(final String arg, final ListIterator iter) throws ParseException {
        boolean hasOption = getOptions().hasOption(arg);

        if (hasOption || !ignoreUnrecognizedOption) {
            super.processOption(arg, iter);
        }
    }
}
