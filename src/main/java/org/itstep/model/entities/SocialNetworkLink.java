package org.itstep.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor(staticName = "of")
public class SocialNetworkLink {

    private SocialNetwork socialNetwork;
    private String link;

    @Override
    public String toString() {
        return socialNetwork.name().toLowerCase() + " : " + link;
    }
}
