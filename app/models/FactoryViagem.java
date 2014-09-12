package models;

public class FactoryViagem {

	public static Viagem getViagemFactory(Viagem viagem, String senha, String tipo){
        if (tipo.equals("ABERTA")) {
                return new ViagemAberta(viagem.getLocal(), viagem.getData(), viagem.getDescricao(), viagem.);
        }return new ViagemLimitada(viagem.getLocal(), viagem.getData(), viagem.getDescricao(), viagem.getAdmin(), senha);
	}

}
