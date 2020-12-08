package com.yourcompany.domain.material;

import com.yourcompany.exceptions.bom.NoSuchMaterialExist;

public interface MaterialRepository {

    Material find(int i);
    Material findLumber(int id, int width, int height, LumberType lumberType) throws NoSuchMaterialExist;

    public enum LumberType {
        TRYKIMP_BRÆDT, TRYKIMP_STOLPE, LÆGTE_UBH, REGLAR_UB, SPÆRTRÆ_UBH, TAG,

    }
}
