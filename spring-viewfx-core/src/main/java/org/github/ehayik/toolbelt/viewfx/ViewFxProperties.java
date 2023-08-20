package org.github.ehayik.toolbelt.viewfx;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class ViewFxProperties {
    private String indexViewUrl;
    private String title;
    private int width;
    private int height;
}
