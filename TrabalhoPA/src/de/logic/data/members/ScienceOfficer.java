package de.logic.data.members;

import de.logic.data.DataGame;

public class ScienceOfficer extends CrewMember {

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
