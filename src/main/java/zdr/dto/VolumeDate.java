package zdr.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * Created by MuratovaO on 29.05.2016.
 */
public class VolumeDate {

    @JsonView(View.Summary.class)
    private long date;

    @JsonView(View.Summary.class)
    private long volume;

    public VolumeDate(long volume, LocalDate date) {
        this.volume = volume;
        this.date = date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public long getDate() {
        return date;
    }

    public long getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "VolumeDate{" +
                "date=" + date +
                ", volume=" + volume +
                '}';
    }
}
