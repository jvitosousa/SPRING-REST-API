package med.voll.api.domain.paciente;

public record DadosListagemPaciente(String nome, String cidade, String uf) {
    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEndereco().getUF(), paciente.getEndereco().getCidade());
    }

}
