package br.edu.compositebit.douglasgiordano.dao.filter;

import br.edu.compositebit.douglasgiordano.model.entities.EnumState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.Map;

@Log
@NoArgsConstructor
@Data
public class CostumerFilter extends SuperFilter{
    private String name;
    private String birthDate;
    private EnumState state;
    private String city;
    private EnumSortCostumer sortBy;
    private EnumSortOrder sortOrder;

    public CostumerFilter(Map<String, String[]> params){
        this.name = this.getObjectParam(params.get("name"));
        this.birthDate = this.getObjectParam(params.get("birthDate"));
        this.state = this.getEnumParam(EnumState.class, params.get("state"));
        this.city = this.getObjectParam(params.get("city"));
        this.sortBy = this.getEnumParam(EnumSortCostumer.class, params.get("sortBy"));
        this.sortOrder = this.getEnumParam(EnumSortOrder.class, params.get("sortOrder"));
    }

    public String getSort(){
        if(this.sortBy == null){
            this.sortBy = EnumSortCostumer.CUSTOMER_NAME;
        }
        return sortBy.name().replaceFirst("_", ".").replace("_"," ");
    }

    public String getSortOrder(){
        if(this.sortOrder == null){
            this.sortOrder = EnumSortOrder.ASC;
        }
        return sortOrder.name();
    }

}
