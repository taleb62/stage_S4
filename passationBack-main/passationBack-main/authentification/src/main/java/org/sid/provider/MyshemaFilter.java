package org.sid.provider;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

public class MyshemaFilter implements SchemaFilter {

    public  static  final  MyshemaFilter ISTANCE = new MyshemaFilter();
    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {
        if ( table.getName().toLowerCase().equals("appuser")) {
            return false;
        }
        else if ( table.getName().toLowerCase().equals("approle")) {
            return false;
        }
        else if ( table.getName().toLowerCase().equals("appuser_roles")) {
            return false;
        }
        else if ( table.getName().toLowerCase().equals("plan_anuell_achat")) {
            return false;
        }
        else if ( table.getName().toLowerCase().equals("mod_passation")) {
            return false;
        }
        else
            if ( table.getName().toLowerCase().equals("type_marche")) {
            return false;
        }
            else
            if ( table.getName().toLowerCase().equals("files")) {
                return false;
            }else
            if ( table.getName().toLowerCase().equals("dossier")) {
                return false;
            }else
            if ( table.getName().toLowerCase().equals("lettre")) {
                return false;
            }else
            if ( table.getName().toLowerCase().equals("ged_table")) {
                return false;
            }
        if ( table.getName().toLowerCase().equals("plis")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}
