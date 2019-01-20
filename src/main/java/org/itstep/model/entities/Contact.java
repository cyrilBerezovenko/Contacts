package org.itstep.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class Contact {

    private String name;
    private String surname;
    private String skype;
    private HashSet<String> phone = new HashSet<>();
    private HashSet<String> email = new HashSet<>();
    private HashSet<SocialNetworkLink> socialNetworks = new HashSet<>();

    public static class ContactBuilder {
        private HashSet<String> phone = new HashSet<>();
        private HashSet<String> email = new HashSet<>();
        private HashSet<SocialNetworkLink> socialNetworks = new HashSet<>();
    }

    public Contact(String name) {
        this.name = name;
    }

    public String toString() {
        Field[] fields = getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        StringBuilder str = new StringBuilder("\nContact\n");
        try {
            for(Field field : fields) {
                if(field.get(this) == null ||
                        (field.getType().isInstance(new HashSet<>()) && ((Set)field.get(this)).isEmpty()))
                    continue;
                str.append(field.getName().toLowerCase() + " : " + field.get(this) + "\n");
            }
        } catch(IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        return str.toString();
    }
}
