package br.edu.compositebit.douglasgiordano.model.entities;


public enum EnumState {
    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AP("AP", "Amap\u00E1"),
    AM("AM", "Amazonas"),
    BA("BA", "Bahia"),
    CE("CE", "Cear\u00E1"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Esp\u00EDrito Santo"),
    GO("GO", "Goi\u00E1s"),
    MA("MA", "Maranh\u00E3o"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Par\u00E1"),
    PB("PB", "Para\u00EDba"),
    PR("PR", "Paran\u00E1"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piau\u00ED"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rond\u00F4nia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SP("SP", "S\u00E3o Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");

    private String valor;
    private String descricao;

    private EnumState(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getValor() {
        return this.valor;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
