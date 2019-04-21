package de.logic.data.members;

import de.logic.data.DataGame;
import java.io.Serializable;

public class ScienceOfficer extends CrewMember implements Serializable{

    public ScienceOfficer(DataGame dataGame) {
        super(dataGame, 1, 1);
    }

    public ScienceOfficer(DataGame dataGame, int color) {
        super(dataGame, 1, 1, color);
    }

    @Override
    public String getName() {
        return "ScienceOfficer";
    }
}
