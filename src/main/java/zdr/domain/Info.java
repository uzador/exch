package zdr.domain;

/**
 * Created by yzadorozhnyy on 04.05.2016.
 */
public class Info {
    private Charsetinfo charsetinfo;

    @Override
    public String toString() {
        return charsetinfo.toString();
    }

    private class Charsetinfo {
        private String name;

        @Override
        public String toString() {
            return "Charsetinfo{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
