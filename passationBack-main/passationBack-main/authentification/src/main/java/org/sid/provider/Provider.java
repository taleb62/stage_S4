package org.sid.provider;

import org.hibernate.tool.schema.spi.SchemaFilter;
import org.hibernate.tool.schema.spi.SchemaFilterProvider;

public class Provider implements SchemaFilterProvider {
    @Override
    public SchemaFilter getCreateFilter() {
        return MyshemaFilter.ISTANCE;
    }

    @Override
    public SchemaFilter getDropFilter() {
        return MyshemaFilter.ISTANCE;
    }

    @Override
    public SchemaFilter getMigrateFilter() {
        return MyshemaFilter.ISTANCE;
    }

    @Override
    public SchemaFilter getValidateFilter() {
        return MyshemaFilter.ISTANCE;
    }
}
