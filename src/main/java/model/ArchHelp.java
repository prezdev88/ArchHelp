package model;

import java.util.ArrayList;
import java.util.List;

public class ArchHelp {
    private List<Comando> comandos;

    public ArchHelp() {
        comandos = new ArrayList<>();
    }

    public List<Comando> getComandos() {
        return comandos;
    }

    public void setComandos(List<Comando> comandos) {
        this.comandos = comandos;
    }
    
    public void add(Comando com){
        com.setId(comandos.size());
        comandos.add(com);
    }
}
