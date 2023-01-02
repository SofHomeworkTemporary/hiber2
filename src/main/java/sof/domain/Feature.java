package sof.domain;

import com.google.protobuf.StringValue;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");
    private final String value;

    public String getValue() {
        return this.value;
    }

    public static Feature getFeatureByValue(String value) {
        if (isNull(value) || value.isEmpty()) {
            return null;
        }
        for (Feature feature : Feature.values()) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }

}
