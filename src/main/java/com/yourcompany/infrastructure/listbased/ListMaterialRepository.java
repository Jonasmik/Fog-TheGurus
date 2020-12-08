package com.yourcompany.infrastructure.listbased;

import com.yourcompany.domain.material.Material;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;

public class ListMaterialRepository implements MaterialRepository {


    @Override
    public Material find(int i) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Material findLumber(int id, int width, int height, LumberType lumberType) throws NoSuchMaterialExist {
        Material material = new Material(id, width, height, "Material of type; " + lumberType);
        return material;
    }
}
