package com.luucasor.goldenraspberryawards.builders;

import com.luucasor.goldenraspberryawards.dtos.AwardDTO;
import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class TimeGapDTOBuilder {

    public static TimeGapDTO getMinMax(){
        TimeGapDTO minMax = new TimeGapDTO();
        minMax.setMin(Arrays.asList(getJoelSilver()));
        minMax.setMax(Arrays.asList(getMatthewVaughn()));
        return minMax;
    }

    private static AwardDTO getMatthewVaughn() {
        AwardDTO matthewVaughn = new AwardDTO();
        matthewVaughn.setProducer("Matthew Vaughn");
        matthewVaughn.setInterval(13);
        matthewVaughn.setPreviousWin(2002);
        matthewVaughn.setFollowingWin(2015);
        return matthewVaughn;
    }

    private static AwardDTO getBuzzFeitshans() {
        AwardDTO buzzFeitshans = new AwardDTO();
        buzzFeitshans.setProducer("Buzz Feitshans");
        buzzFeitshans.setInterval(9);
        buzzFeitshans.setPreviousWin(1985);
        buzzFeitshans.setFollowingWin(1994);
        return buzzFeitshans;
    }

    private static AwardDTO getBoDerek() {
        AwardDTO boDerek = new AwardDTO();
        boDerek.setProducer("Bo Derek");
        boDerek.setInterval(6);
        boDerek.setPreviousWin(1984);
        boDerek.setFollowingWin(1990);
        return boDerek;
    }

    private static AwardDTO getJoelSilver() {
        AwardDTO joelSilver = new AwardDTO();
        joelSilver.setProducer("Joel Silver");
        joelSilver.setInterval(1);
        joelSilver.setPreviousWin(1990);
        joelSilver.setFollowingWin(1991);
        return joelSilver;
    }
}
