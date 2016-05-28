package zdr.dto;

import java.time.LocalDate;

/**
 * Created by MuratovaO on 29.05.2016.
 */
public class VolumeDate {
    private long volume;
    private LocalDate date;

    public VolumeDate(long volume, LocalDate date) {
        this.date = date;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "VolumeDate{" +
                "volume=" + volume +
                ", date=" + date +
                '}';
    }
}
